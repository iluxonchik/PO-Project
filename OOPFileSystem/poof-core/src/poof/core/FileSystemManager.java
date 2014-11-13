package poof.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class FileSystemManager {
	private String fsName;
	private User activeUser;
	private FileSystem activeFileSystem;
	private Directory activeDirectory;
	private boolean needsSaving; // dirty bit indicating whether the FS has changes that need
								// to be saved.
	private final String fileExtension; // extension of files when opening/saving FileSystem
	
	// Constants
	public final String PARENT_DIR_NAME = "..";
	public final String THIS_DIR_NAME = ".";
	

	
	public FileSystemManager() {
		activeFileSystem = null;
		activeUser = null;
		activeDirectory = null;
		needsSaving = true;
		fileExtension = ".raw";
	}
	
	
	public void appendDataToFile(String filename, String content) {
		// TODO
	}
	
	public void changeEntryPermissions(String entryName, PrivacyMode privacyMode) 
			throws EntryUnknownCoreException, AccessDeniedCoreException {
		
		if (!entryExists(entryName))
			throw new EntryUnknownCoreException();
		
		FileSystemEntitiy entitiy = activeDirectory.getChild(entryName);
		
		if (!hasPrivatePermissions(activeUser, entitiy))
			throw new AccessDeniedCoreException();
		
		entitiy.setPrivacyMode(privacyMode);
		needsSaving = true;
	}
	
	public void changeOwner(String entryName, String newOwnerUsername) {
		// TODO
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

	
	public void createFile(String filename) {
		// TODO
		needsSaving = true;
	}
	
	public void createDirectory(String name) throws AccessDeniedCoreException, EntryExistsCoreException{
		
		// first, check if user has permissions
		if(!hasPrivatePermissions(activeUser, activeDirectory))
			throw new AccessDeniedCoreException();
		
		if(activeDirectory.getChild(name)!=null)
			// directory with such name already exists
			throw new EntryExistsCoreException();
		
		// at this point it's safe to add a new directory
		activeDirectory.addChild(new Directory(name, activeDirectory));
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
	
	}
	
	public void createUser(String username, String name) throws AccessDeniedCoreException, UserExistsCoreException {
		if (!hasRootPermissions(activeUser))
			// Logged in user is not root
			throw new AccessDeniedCoreException();
		
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
		
		Directory child = activeDirectory.getChild(entryName);
		if (child == null)
			// no directory with such name found
			throw new EntryUnknownCoreException();
		return child.toString(); 
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
		
	}
	
	public void loadFileSystem(String fileName) throws FileNotFoundException {
		try {
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(fileName + fileExtension)));
			
			activeFileSystem = (FileSystem)in.readObject(); // restore FS
			activeUser = (User)in.readObject(); // restore active user
			activeDirectory = activeUser.getMainDirectory(); // set users dir to default
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
		needsSaving = true;
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
		
		if (!hasPrivatePermissions(activeUser, entitiy))
			throw new AccessDeniedCoreException();
		
		// removal of the specified directory is allowed
		activeFileSystem.removeEntry(activeDirectory, entryName);
		
		needsSaving = true;
		
	}
	
	public void removeUser(String username) {
		// TODO
	}
	
	public String showFileData(String fileName) {
		// TODO
		
		return new String();
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
				(user.equals(activeFileSystem.getUser(User.ROOT_USERNAME))) || // user is root
				(user.equals(entitiy.getOwner())); // user is owner
				
	}

	private boolean hasRootPermissions(User user) {
		if (user == null) 
			return false;
		
		return (user.equals(activeFileSystem.getUser(User.ROOT_USERNAME)));
	}
	
	private boolean isLegalDirectoryRemoval(String entryName) {
		return (!(entryName.equals(PARENT_DIR_NAME)) && !(entryName.equals(THIS_DIR_NAME)));
	}
	
	
}
