package poof.core;

public class User {
	private String name;
	private String username;
	Directory homeDir; // user's home directory
	
	public User(String username, String name) {
		this.name = name;
		this.username = username;
		// TODO: set user's home dir to /home/USERNAME/
	}
	
	// Getters
	public String getName() { return name; }
	public String getUsername() { return username; }
	public Directory getHomeDirectory() { return this.homeDir; }
	
	// TODO: toString, equals
	
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass())
			return false; // different classes
		return this.username == ((User)obj).username;
	}
}
