package poof.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileSystem implements Serializable{
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -4205496443358977682L;
	
	String name;
	Directory rootDirectory;
	HashMap<String, User> users;
	
	public static final String ROOT_USERNAME = "root";
	public static final String ROOT_NAME = "Super User";
	public static final String ROOT_DIRECTORY = "/";
	private static final String HOME_DIRECTORY = "home";

	
	public FileSystem(String name) {
		this.name = name;
		
		users = new HashMap<String, User>();
		
		// Create a new root directory (has no owner [i.e. the owner is "null"])
		rootDirectory = new Directory(ROOT_DIRECTORY, null, null);
		
		// add "home" to roots children (has no owner [i.e. the owner is "null"])
		Directory homeDirectory = new Directory(HOME_DIRECTORY, null, rootDirectory);
		rootDirectory.addChild(homeDirectory);
		
		// Create root user and add him to users list
		User rootUser = new User(ROOT_USERNAME, ROOT_NAME, homeDirectory); 
		users.put(ROOT_USERNAME, rootUser);
		

	}
	
	public String getDirectoryPath(Directory dir) {
		// Get directory's path recursively
		
		if (dir.getName() == ROOT_DIRECTORY)
			return "/";
		
		return dir.getParent().getName() + "/" + name;
	}
	
	public void addUser(User user) {
		// TODO
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
	
	public List<String> listAllEntries(Directory dir) {
		// TODO
		return new ArrayList<String> ();
	}
	
	public String listEntry(FileSystemEntitiy entry) {
		// TODO

		return new String();
	}
	
	public List<String> listUsers() {
		// TODO
		return new ArrayList<String>();
	}
	
	public void removeEntry(FileSystemEntitiy entry) {
		// TODO
	}
	
	
	public void removeUser(String username) {
		// TODO
	}
	
	public String showFileData(File file) {
		// TODO
		return new String();
	}
	
	
	// Getters
	public User getUser(String username) {
		// Return a user based on username
		return users.get(username);
	}
	
}
