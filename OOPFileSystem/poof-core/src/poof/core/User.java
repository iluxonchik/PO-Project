package poof.core;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2407830354520585884L;
	
	private String name;
	private String username;
	Directory mainDir; // user's home directory
	
	// Constants
	public static final String ROOT_USERNAME = "root";
	public static final String ROOT_NAME = "Super User";
	
	
	public User(String username, String name, Directory mainDirParent) {
		this.name = name;
		this.username = username;
		
		if(mainDirParent != null)
			createHomeDirectory(mainDirParent); // set user's home dir to /home/USERNAME/
		else
			mainDir = null;
		
	}
	
	public User(String username, String name) {
		this(username, name, null);
	}
	
	private void createHomeDirectory(Directory mainDirParent) {
		// set user's home directory to /home/USERNAME/
		mainDir = new Directory(this.getUsername(), this, mainDirParent);
		mainDirParent.addChild(mainDir); // add 'username' dir to /home/
	}
	
	// Getters
	public String getName() { return name; }
	public String getUsername() { return username; }
	public Directory getMainDirectory() { return this.mainDir; }

	// Setters
	public void setMainDir(Directory dir) { mainDir = dir; }
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass())
			return false; // different classes
		return this.username.equals(((User)obj).username);
	}
	
	@Override
	public String toString() {
		return username + ":" + name + ":" + mainDir.getAbsolutePath();
	}
}
