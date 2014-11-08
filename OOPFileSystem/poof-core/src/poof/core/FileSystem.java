package poof.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileSystem {
	String name;
	Directory rootDirectory;
	HashMap<String, User> users;
	
	public FileSystem(String name) {
		this.name = name;
		// TDODO: Create root user, etc
	}
	
	public void addUser(User user) {
		// TODO
	}
	
	public void appendDataToFile(File file, String content) {
		// TODO
	}
	
	public void changeEntryPermissions(FileSystemEntity entry, PrivacyMode privacyMode) {
		// TODO
	}
	
	public void changeOwner(FileSystemEntity entry, User newOwner) {
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
	
	public String listEntry(FileSystemEntity entry) {
		// TODO

		return new String();
	}
	
	public List<String> listUsers() {
		// TODO
		return new ArrayList<String>();
	}
	
	public void removeEntry(FileSystemEntity entry) {
		// TODO
	}
	
	
	public void removeUser(String username) {
		// TODO
	}
	
	public String showFileData(File file) {
		// TODO
		return new String();
	}
	
	
}
