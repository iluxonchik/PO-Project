package poof.core;

import java.io.Serializable;

public abstract class FileSystemEntitiy implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5454995429011788081L;
	
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
	
	
	public boolean isCdiable() {
		return entitiyType == EntitiyType.DIRECTORY;
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
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void setPrivacyMode(PrivacyMode privacyMode) {
		this.privacyMode = privacyMode;
	}
	
	
	@Override
	public String toString() {
		String entitiyStr = "";
		if (privacyMode == PrivacyMode.PRIVATE)
			entitiyStr += "- ";
		else
			entitiyStr += "w ";
		
		entitiyStr += owner.getUsername() + " " + getSize() + " " + name;
			
		return entitiyStr;
	}

	
	
}
