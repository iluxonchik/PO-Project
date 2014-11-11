package poof.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class FileSystemManager {
	private String fsName;
	private User activeUser;
	private FileSystem activeFileSystem;
	private Directory activeDirectory;
	
	public FileSystemManager() {
		activeFileSystem = null;
		activeUser = null;
		activeDirectory = null;
	}
	
	
	public void appendDataToFile(String filename, String content) {
		// TODO
	}
	
	public void changeEntryPermissions(String entryName, PrivacyMode privacyMode) {
		// TODO
	}
	
	public void changeOwner(String entryName, String newOwnerUsername) {
		// TODO
	}
	
	public void  changeWorkingDirectory(String dirName) {
		// TODO
	}
	
	public void createFile(String filename) {
		// TODO
	}
	
	public void createDirectory(String name) throws AccessDeniedCoreException, EntryExistsCoreException{
		// TODO
		
		// first, check if user has permissions
		if(!hasPrivatePermissions(activeUser, activeDirectory))
			throw new AccessDeniedCoreException();
		
		if(activeDirectory.getChild(name)!=null)
			// directory with such name already exists
			throw new EntryExistsCoreException();
		
		// at this point it's safe to add a new directory
		activeDirectory.addChild(new Directory(name, activeUser, activeDirectory));
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
		
	}
	
	public List<String> listAllEntries(String directoryName) {
		// TODO
		return new ArrayList<String>();
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
	
	public void loadFileSystem(String fileName) {
		// TODO
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
	
	public void removeEntry(String entryName) {
		// TODO
	}
	
	public void removeUser(String username) {
		// TODO
	}
	
	public void saveFileSystem() {
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
	
	private void setActiveUser(User user) {
		// Two-in-one: change active user and active directory
		activeUser = user;
		activeDirectory = user.getMainDirectory();	
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
}
