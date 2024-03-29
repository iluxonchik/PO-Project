package poof.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * File System Class
 */
public class FileSystem implements Serializable{
	
	/** Generated serialVersionUID. */
	private static final long serialVersionUID = -4205496443358977682L;

	
	/** FileSystem's name (indicates if it's associated to a file). */
	private String name;
	
	/** FileSystem's root directory. */
	private Directory rootDirectory;
	
	/** FileSystem's home directory. */
	private Directory homeDirectory; // convenient to have when creating new users
	
	/** Users map. */
	private HashMap<String, User> users;
	
	
	/**
	 * Instantiates a new File System.
	 *
	 * @param name File System's name. null can be passed to indicate 
	 * that the File System is not associated to any file.
	 * @param rootUser root user of the File System
	 */
	public FileSystem(String name, User rootUser) {
		this.name = name;
		
		users = new HashMap<String, User>();
		
		// Create a new root directory (has no parent [i.e. it's the parent of itself])
		rootDirectory = new Directory(Directory.ROOT_DIRECTORY_NAME, rootUser, null);	

	}
	
	/**
	 * Returns the absolute path to a directory.
	 *
	 * @param dir directory whose path to get
	 * @return directory's absolute path
	 */
	public String getDirectoryPath(Directory dir) {
		return dir.getAbsolutePath();
	}
	
	/**
	 * Adds a user to to the File System.
	 *
	 * @param user user to add to the File System
	 */
	public void addUser(User user) {
		users.put(user.getUsername(), user);
	}
	
	/**
	 * Appends data to a file.
	 *
	 * @param file file to which content should be appended
	 * @param content content to append to the file
	 */
	public void appendDataToFile(File file, String content) {
		file.appendData(content);
	}
	
	/**
	 * Changes an entity's permissions (to public or private).
	 *
	 * @param entity entity whose permissions should be changed
	 * @param privacyMode new privacy mode for the entity
	 */
	public void changeEntryPermissions(FileSystemEntitiy entity, PrivacyMode privacyMode) {
		entity.setPrivacyMode(privacyMode);
	}
	
	/**
	 * Change the owner of an entity.
	 *
	 * @param entry entity whose owner should be changed
	 * @param newOwner the new owner of the entity
	 */
	public void changeOwner(FileSystemEntitiy entry, User newOwner) {
		if (!entry.getOwner().equals(newOwner)) {
			entry.setOwner(newOwner);
		}
	}
	
	/**
	 * Creates a new file and adds it to a directory.
	 *
	 * @param name name of the file
	 * @param dir directory where the file should be added
	 * @param owner the owner of the file
	 */
	public void createFile(String filename, Directory dir, User owner) {
		
		File f = new File(filename, owner);
		// add file to children list
		dir.addChild(f);
	}
	
	/**
	 * List all entries of a directory.
	 *
	 * @param dir directory whose entries to list
	 * @return Map containing the entities sorted by name
	 */
	public Map<String, FileSystemEntitiy> listAllEntries(Directory dir) {
		
		Map<String, FileSystemEntitiy> sortedChildrenMap = new TreeMap<String, FileSystemEntitiy>(dir.getChildren());
		return sortedChildrenMap;
	}
	
	/**
	 * List an entry.
	 *
	 * @param entry entity to list
	 * @return entity's external representation
	 */
	public String listEntry(FileSystemEntitiy entry) {
		return entry.toString(); 
	}
	
	/**
	 * List users of a File System sorted by username.
	 *
	 * @return Map containing users sorted by username
	 */
	public Map<String, User> listUsers() {
		// build a map sorted by keys
		Map<String, User> sortedUserMap = new TreeMap<String, User>(users);
		return sortedUserMap;
	}
	
	/**
	 * Removes an entry.
	 *
	 * @param dir directory from where the child should be removed
	 * @param entryName child's name
	 */
	public void removeEntry(Directory dir, String entryName) {
		dir.removeChild(entryName);
	}
	
	
	/**
	 * Removes a user from a File System.
	 *
	 * @param username username of the user to remove
	 */
	public void removeUser(String username) {
		// TODO
	}
	
	/**
	 * Show contents of a file.
	 *
	 * @param file file whose contents will be shown
	 * @return file contents
	 */
	public String showFileData(File file) {

		return file.getContent();
	}
	
	/**
	 * Checks if a File System is associated to a file.
	 *
	 * @return true, if it's associated (i.e. has a name), false otherwise
	 */
	public boolean isAssociated() {
		return name != null;
	}
	
	
	// Getters
	
	/**
	 * Gets a user from FileSystem.
	 *
	 * @param username username of the user to return
	 * @return requested user
	 */
	public User getUser(String username) {
		// Return a user based on username
		return users.get(username);
	}
	
	/**
	 * Gets the root directory of a FileSystem.
	 *
	 * @return the root directory of a File System
	 */
	public Directory getRootDirectory() {
		return rootDirectory;
	}
	
	/**
	 * Gets the home directory of a FileSystem.
	 *
	 * @return the home directory of a FileSystem
	 */
	public Directory getHomeDirectory() {
		return homeDirectory;
	}
	
	/**
	 * Gets the name of a FileSystem.
	 *
	 * @return the name of a FileSystem
	 */
	public String getName() {
		return name;
	}
	
	// Setters
	
	/**
	 * Sets the home directory of a FileSystem.
	 *
	 * @param dir the new home directory for the File System
	 */
	public void setHomeDirectory(Directory dir) {
		// Set home directory (i.e. the directory where main user directories are created).
		homeDirectory = dir;
	}
	
	/**
	 * Sets the name of a FileSystem.
	 *
	 * @param name the new name of a FileSystem.
	 */
	public void setName (String name) {
		this.name = name;
	}
}
