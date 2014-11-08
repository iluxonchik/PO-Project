package poof.core;

public class User {
	private String name;
	private String username;
	Directory mainDir; // user's home directory
	
	
	public User(String username, String name, Directory mainDirParent) {
		this.name = name;
		this.username = username;
		
		// set user's home dir to /home/USERNAME/
		mainDir = new Directory(this.getUsername(), this, mainDirParent);
		mainDirParent.addChild(mainDir); // add 'usename' dir to /home/
	}
	
	// Getters
	public String getName() { return name; }
	public String getUsername() { return username; }
	public Directory getMainDirectory() { return this.mainDir; }
	
	// TODO: equals
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass())
			return false; // different classes
		return this.username == ((User)obj).username;
	}
}
