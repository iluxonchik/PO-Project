package poof.core;

public abstract class FileSystemEntitiy {
	protected String name;
	protected int size;
	protected User owner;
	protected PrivacyMode privacyMode;
	protected EntitiyType entitiyType;
	
	public FileSystemEntitiy (String name, User owner) {
		this.name = name;
		this.owner = owner;
		this.privacyMode = PrivacyMode.PRIVATE;
	}

	// Getters
	public String getName() {
		return name;
	}

	public abstract int getSize();

	public User getOwner() {
		return owner;
	}

	public PrivacyMode getPrivacyMode() {
		return privacyMode;
	}
	
	public EntitiyType getentitiyType() {
		return entitiyType;
	}
	
	// Setters
	public void setOwner (User newOwner) {
		this.owner = newOwner;
	}
	
	
	@Override
	public String toString() {
		String entitiyStr = "";
		if (privacyMode == PrivacyMode.PRIVATE)
			entitiyStr += "-";
		else
			entitiyStr += "w";
		
		entitiyStr += owner.getUsername() + " " + getSize() + " " + name;
			
		return entitiyStr;
	}

	
	
}
