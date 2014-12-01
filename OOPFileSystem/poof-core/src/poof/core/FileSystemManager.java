package poof.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;


public class FileSystemManager {
	private String fsName;
	private User activeUser;
	private FileSystem activeFileSystem;
	private Directory activeDirectory;
	private boolean needsSaving; // dirty bit indicating whether the FS has changes that need
								// to be saved.
	private final String fileExtension; // extension of files when opening/saving FileSystem
	
	
	public FileSystemManager() {
		activeFileSystem = null;
		activeUser = null;
		activeDirectory = null;
		needsSaving = false;
		fileExtension = ".raw";		
	}
	
	
	public void appendDataToFile(String filename, String content) 
			throws EntryUnknownCoreException,IsNotFileCoreException, AccessDeniedCoreException
	{
		File file = null;
		FileSystemEntitiy entity = activeDirectory.getChild(filename);
		
		if (entity == null)
			// entity does not exist
			throw new EntryUnknownCoreException();
		
		if(!hasPrivatePermissions(activeUser, entity))
			// logged in user has no permissions to alter the file
			throw new AccessDeniedCoreException(activeUser.getName());
		
		if (entity.isCdiable())
			// entity is a file
			throw new IsNotFileCoreException();
		else {
			if (isFile(entity))
				// now it's "safe" to cast to file
				file = (File)entity;
		}
		
		file.appendData(content);
				
		needsSaving = true;
	
	}
	
	public void changeEntryPermissions(String entryName, PrivacyMode privacyMode) 
			throws EntryUnknownCoreException, AccessDeniedCoreException {
		
		if (!entryExists(entryName))
			throw new EntryUnknownCoreException();
		
		FileSystemEntitiy entity = activeDirectory.getChild(entryName);
		
		if (!hasPrivatePermissions(activeUser, entity))
			throw new AccessDeniedCoreException(activeUser.getName());
		
		activeFileSystem.changeEntryPermissions(entity, privacyMode);
		needsSaving = true;
	}
	
	public void changeOwner(String entryName, String newOwnerUsername) throws EntryUnknownCoreException, AccessDeniedCoreException{
		FileSystemEntitiy entry = activeDirectory.getChild(entryName);
		
		if (entry == null)
			throw new EntryUnknownCoreException();
		
		if (!hasPrivatePermissions(activeUser, entry))
			throw new AccessDeniedCoreException(activeUser.getUsername());
		
		// everything is OK by now, change the permissions
		activeFileSystem.changeOwner(entry, activeUser);
	}
	
	public void  changeWorkingDirectory(String dirName) throws EntryUnknownCoreException, IsNotDirectoryCoreException {
		
		if (!entryExists(dirName)) 
			throw new EntryUnknownCoreException();
		
		FileSystemEntitiy entitiy = activeDirectory.getChild(dirName);
		
		if(!entitiy.isCdiable())
			throw new IsNotDirectoryCoreException();
		
		// Everything is ok, change the active directory
		activeDirectory = (Directory)entitiy;
		
	}
	
	public boolean entryExists(String entryName) {
		if(activeDirectory.getChild(entryName) == null)
			return false;
		return true;
	}

	
	public void createFile(String filename) throws EntryExistsCoreException, AccessDeniedCoreException{
		
		if (!hasPrivatePermissions(activeUser, activeDirectory))
			// user doesn't have permissions to create files here
			throw new AccessDeniedCoreException(activeUser.getUsername());
		
		if(activeDirectory.getChild(filename) != null)
			// entry with such name already exists
			throw new EntryExistsCoreException();
		
		File f = new File(filename, activeUser);
		
		// add file to children list
		activeDirectory.addChild(f);
		
		needsSaving = true;
	}
	
	public void createDirectory(String name) throws AccessDeniedCoreException, EntryExistsCoreException{
		
		// first, check if user has permissions
		if(!hasPrivatePermissions(activeUser, activeDirectory))
			throw new AccessDeniedCoreException(activeUser.getUsername());
		
		if(activeDirectory.getChild(name)!=null)
			// directory with such name already exists
			throw new EntryExistsCoreException();
		
		// at this point it's safe to add a new directory
		// NOTE: (new Directory(name, activeDirectory) was used before
		activeDirectory.addChild(new Directory(name, activeUser, activeDirectory));
		needsSaving = true;
	}
	
	public void createFileSystem() {
		// new FileSystems name is null (file system is not associated to any file)

		// create a temporary directory (to be home directory)
		Directory homeDirectory = new Directory(Directory.HOME_DIRECTORY_NAME, null, null);
		
		// Create root user and add him to users list
		User rootUser = new User(User.ROOT_USERNAME, User.ROOT_NAME, homeDirectory); 
		activeFileSystem = new FileSystem(null, rootUser); // new file system with rootUser as the owner of root dir
		activeFileSystem.addUser(rootUser); // add root user to FileSystems user list
		
		// Complete homeDirectory (build it)
		Directory rootDirectory = activeFileSystem.getRootDirectory();
		homeDirectory.setParent(rootDirectory); // set root directory as its parent
		homeDirectory.setOwner(rootUser); // set root user as its owner
		
		// add homeDirectory to the structure
		rootDirectory.addChild(homeDirectory);
		
		// set the FileSystem's home directory
		activeFileSystem.setHomeDirectory(homeDirectory);
		
		// login the root user
		setActiveUser(rootUser);

		needsSaving = true;
	}
	
	public void createUser(String username, String name) throws AccessDeniedCoreException, UserExistsCoreException {
		if (!hasRootPermissions(activeUser))
			// Logged in user is not root
			throw new AccessDeniedCoreException(activeUser.getUsername());
		
		if (activeFileSystem.getUser(username)!= null)
			// A user with such username already exists
			throw new UserExistsCoreException();
		
		// At this point the logged in user is root and the username is free,
		// so it's safe to create a new user
		activeFileSystem.addUser(new User(username, name, activeFileSystem.getHomeDirectory()));
		needsSaving = true;
		
	}
	
	public Map<String, FileSystemEntitiy> listAllEntries() {

		return activeFileSystem.listAllEntries(activeDirectory);
	}
	
	public String listEntry(String entryName) throws EntryUnknownCoreException {
		
		FileSystemEntitiy child = activeDirectory.getChild(entryName);
		if (child == null)
			// no directory with such name found
			throw new EntryUnknownCoreException();
		
		return activeFileSystem.listEntry(child); 
	}
	
	public Collection<User> listUsers() {
		
		Map<String, User> sortedUserMap = activeFileSystem.listUsers();
		return sortedUserMap.values();
		}
	
	public void saveFileSystem(String name) {
		activeFileSystem.setName(name); // associate activeFilesystem to a filename
	
		try {
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(name + fileExtension)));
			out.writeObject(activeFileSystem);
			out.writeObject(activeUser);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		needsSaving = false;
		
	}
	
	public void loadFileSystem(String fileName) throws FileNotFoundException {
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(fileName + fileExtension)));
			
			activeFileSystem = (FileSystem)in.readObject(); // restore FS
			activeUser = (User)in.readObject(); // restore active user
			activeDirectory = activeUser.getMainDirectory(); // set users dir to default
			needsSaving = false;
			in.close();
		}catch (FileNotFoundException e) { throw new FileNotFoundException(); } 
		catch (IOException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
	}
	
	public void login(String username) throws UserUnknownCoreException {
		
		User userToLogin = activeFileSystem.getUser(username);
		
		if(userToLogin ==  null)
			// If user doesn't exist, throw an exception
			throw new UserUnknownCoreException();
		setActiveUser(userToLogin);

	}
	
	public String printWorkingDirectory() {
		return activeFileSystem.getDirectoryPath(activeDirectory);
	}
	
	public void removeEntry(String entryName) throws EntryUnknownCoreException, AccessDeniedCoreException,
	IllegalRemovalCoreException{
		
		if (!entryExists(entryName))
			throw new EntryUnknownCoreException();
		
		if (!isLegalDirectoryRemoval(entryName)) 
			throw new IllegalRemovalCoreException();
		
		FileSystemEntitiy entitiy = activeDirectory.getChild(entryName);
		
		if (!hasPrivatePermissions(activeUser, entitiy) || !hasDirectoryPermissions(activeDirectory, activeUser))
			throw new AccessDeniedCoreException(activeUser.getUsername());
		
		// removal of the specified directory is allowed
		activeFileSystem.removeEntry(activeDirectory, entryName);
		
		needsSaving = true;
		
	}
	
	private boolean hasDirectoryPermissions(Directory activeDirectory,
			User activeUser) {
		return activeDirectory.getOwner() == activeUser || 
				hasRootPermissions(activeUser)||
				isPublic(activeDirectory);
	}


	public void removeUser(String username) {
		// TODO
		needsSaving = true;
	}
	
	public String showFileData(String fileName) throws EntryUnknownCoreException, IsNotFileCoreException {
		
		File file = null;
		FileSystemEntitiy fsEntity = activeDirectory.getChild(fileName);
	
		if (fsEntity == null)
			throw new EntryUnknownCoreException();
		
		if (!isFile(fsEntity))
			throw new IsNotFileCoreException();
		else
			file = (File)fsEntity;
		
		return file.getContent();
	}
	
	public void initializeFromFile(String datafile) {
		/* Initialize the FileSystem from an imported datafile */
		try {
			String line;
			
			BufferedReader br = new BufferedReader(new FileReader(datafile));
			createFileSystem(); // first, create a new FileSystem
			
			Parser p = new Parser(this);
			while((line = br.readLine() )!= null)
				p.parse(line);
			
			br.close();
		} catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
	}
	
	// Getters
	public String getFileSystemName () {
		// Is this one really needed?
		return fsName;
	}
	
	public User getActiveUser() {
		return activeUser;
	}
	
	public FileSystem getActiveFileSystem() {
		return activeFileSystem;
	}
	
	public boolean needsSaving() {
		return needsSaving;
	}
	
	private void setActiveUser(User user) {
		// Two-in-one: change active user and active directory
		activeUser = user;
		activeDirectory = user.getMainDirectory();	
		needsSaving = true;
	}
	
	private boolean hasPrivatePermissions(User user, FileSystemEntitiy entitiy) {
		if (user == null || entitiy == null)
			return false;
		
		return (entitiy.getPrivacyMode().equals(PrivacyMode.PUBLIC)) ||  // entitiy is public
				(hasRootPermissions(user)) || // user is root
				(user.equals(entitiy.getOwner())); // user is owner
				
	}

	private boolean hasRootPermissions(User user) {
		if (user == null) 
			return false;
		
		return (user.equals(activeFileSystem.getUser(User.ROOT_USERNAME)));
	}
	
	private boolean isLegalDirectoryRemoval(String entryName) {
		return (!(entryName.equals(Directory.PARENT_DIR_NAME)) && !(entryName.equals(Directory.THIS_DIR_NAME)));
	}
	
	public void setNeedsSaving(boolean bool) {
		needsSaving = bool;
	}
	
	public void addUser(User user) {
		activeFileSystem.addUser(user);
	}
	
	public Directory getRootDirectory() {
		return activeFileSystem.getRootDirectory();
	}
	
	public boolean isFile(FileSystemEntitiy entity) {
		return entity.getentitiyType() == EntitiyType.FILE;
	}
	
	public boolean isPublic(FileSystemEntitiy ent) {
		return ent.getPrivacyMode() == PrivacyMode.PUBLIC;
	}
}
