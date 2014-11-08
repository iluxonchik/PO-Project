package poof.core;

public abstract class FileSystemEntitiy {
	private String name;
	private int size;
	private User owner;
	private PrivacyMode privacyMode;
	
	public FileSystemEntitiy (String name, User owner) {
		this.name = name;
		this.owner = owner;
	}

	// Getters
	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public User getOwner() {
		return owner;
	}

	public PrivacyMode getPrivacyMode() {
		return privacyMode;
	}
	
	// Setters
	public void setOwner (User newOwner) {
		this.owner = newOwner;
	}
	
	
}
