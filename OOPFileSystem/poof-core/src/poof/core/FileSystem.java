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
	



	
	public FileSystem(String name, User rootUser) {
		this.name = name;
		
		users = new HashMap<String, User>();
		
		// Create a new root directory (has no owner [i.e. the owner is "null"])
		rootDirectory = new Directory(Directory.ROOT_DIRECTORY_NAME, rootUser, null);	

	}
	
	public String getDirectoryPath(Directory dir) {
		// Get directory's path recursively
		
		if (dir.getName() == Directory.ROOT_DIRECTORY_NAME)
			return "/";
		
		return dir.getParent().getName() + Directory.DIR_SEPARATOR + name;
	}
	
	public void addUser(User user) {
		users.put(user.getName(), user);
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
	
	public Directory getRootDirectory() {
		return rootDirectory;
	}
	
}
