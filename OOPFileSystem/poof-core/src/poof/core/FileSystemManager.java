package poof.core;

import ist.po.ui.ValidityPredicate;

import java.util.ArrayList;
import java.util.List;

import poof.textui.UserUnknownException;


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
	
	public void creteFile(String filename) {
		// TODO
	}
	
	public void createFileSystem() {
		activeFileSystem = new FileSystem("");
	}
	
	public void createUser(String username, String name) {
		// TODO
	}
	
	public List<String> listAllEntries(String directoryName) {
		// TODO
		return new ArrayList<String>();
	}
	
	public String listEntry(String entryName) {
		// TODO
		return new String();
	}
	
	public List<String> listUsers() {
		// TODO
		return new ArrayList<String>();
	}
	
	public void loadFileSystem(String fileName) {
		// TODO
	}
	
	public void login(String username) throws UserUnknownException {
		
		User userToLogin = activeFileSystem.getUser(username);
		
		if(userToLogin ==  null)
			// If user doesn't exist, throw an exception
			throw new UserUnknownException(username);
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
	
}
