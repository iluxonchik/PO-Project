package poof.core;

public abstract class FileSystemEntitiy {
	protected String name;
	protected int size;
	protected User owner;
	protected PrivacyMode privacyMode;
	
	public FileSystemEntitiy (String name, User owner) {
		this.name = name;
		this.owner = owner;
		this.privacyMode = PrivacyMode.PRIVATE;
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
