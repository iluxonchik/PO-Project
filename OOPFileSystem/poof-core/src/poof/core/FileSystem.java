package poof.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class FileSystem implements Serializable{
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -4205496443358977682L;
	
	private String name;
	private Directory rootDirectory;
	private Directory homeDirectory; // convenient to have when creating new users
	private HashMap<String, User> users;
	
	
	public FileSystem(String name, User rootUser) {
		this.name = name;
		
		users = new HashMap<String, User>();
		
		// Create a new root directory (has no parent [i.e. it's the parent of itself])
		rootDirectory = new Directory(Directory.ROOT_DIRECTORY_NAME, rootUser, null);	

	}
	
	public String getDirectoryPath(Directory dir) {
		return dir.getAbsolutePath();
	}
	
	public void addUser(User user) {
		users.put(user.getUsername(), user);
	}
	
	public void appendDataToFile(File file, String content) {
		// TODO
	}
	
	public void changeEntryPermissions(FileSystemEntitiy entry, PrivacyMode privacyMode) {
		// TODO
	}
	
	public void changeOwner(FileSystemEntitiy entry, User newOwner) {
		if (!entry.getOwner().equals(newOwner)) {
			entry.setOwner(newOwner);
		}
	}
	
	// NOTE: Use visitor?
	public void checkPermissions(User user) {
		// TODO ?
	}
	
	public void createFile(String name, Directory dir, User owner) {
		// TODO
	}
	
	public Map<String, FileSystemEntitiy> listAllEntries(Directory dir) {
		
		Map<String, FileSystemEntitiy> sortedChildrenMap = new TreeMap<String, FileSystemEntitiy>(dir.getChildren());
		return sortedChildrenMap;
	}
	
	public String listEntry(FileSystemEntitiy entry) {
		// TODO

		return new String();
	}
	
	public Map<String, User> listUsers() {
		// build a map sorted by keys
		Map<String, User> sortedUserMap = new TreeMap<String, User>(users);
		return sortedUserMap;
	}
	
	public void removeEntry(Directory activeDir, String entryName) {
		activeDir.removeChild(entryName);
	}
	
	
	public void removeUser(String username) {
		// TODO
	}
	
	public String showFileData(File file) {
		// TODO
		return new String();
	}
	
	public boolean isAssociated() {
		return name != null;
	}
	
	
	// Getters
	public User getUser(String username) {
		// Return a user based on username
		return users.get(username);
	}
	
	public Directory getRootDirectory() {
		return rootDirectory;
	}
	
	public Directory getHomeDirectory() {
		return homeDirectory;
	}
	
	public String getName() {
		return name;
	}
	
	// Setters
	public void setHomeDirectory(Directory dir) {
		// Set home directory (i.e. the directory where main user directories are created).
		homeDirectory = dir;
	}
	
	public void setName (String name) {
		this.name = name;
	}
}
