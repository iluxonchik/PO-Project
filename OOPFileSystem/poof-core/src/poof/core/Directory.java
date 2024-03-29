package poof.core;

import java.util.HashMap;

public class Directory extends FileSystemEntitiy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1968533951935404039L;
	
	private final int ENTRY_COST = 8; // cost of the each entry inside the directory

	
	private HashMap<String, FileSystemEntitiy> children;
	
	
	// Constants
	public static final String ROOT_DIRECTORY_NAME = "/";
	public static final String HOME_DIRECTORY_NAME = "home";
	public static final String PARENT_DIR_NAME = "..";
	public static final String THIS_DIR_NAME = ".";
	private static final String DIR_SEPARATOR = "/";
	
	public Directory(String name, User owner, Directory parent) { 
		super(name, owner);
		
		entitiyType = EntitiyType.DIRECTORY;
		
		children = new HashMap<String, FileSystemEntitiy>();
		
		if (parent == null)
			// if the parent dir is null, the directory is parent of itself
			// used for root directory
			parent = this;
		
		// Add the the current (.) and parent (..) dirs to children 
		addChild(THIS_DIR_NAME, this);
		addChild(PARENT_DIR_NAME, parent);
		
	}
	
	public Directory (String name, Directory parent) {
		this(name, parent.getOwner(), parent);
	}
	
	// Getters
	public Directory getParent() {
		FileSystemEntitiy entity = getChild(PARENT_DIR_NAME);
		
		if (entity.getentitiyType() == EntitiyType.DIRECTORY)
			return (Directory)entity;
		
		return null; // something went wrong | no parent directory?
	}
	
	public FileSystemEntitiy getChild(String name) {
		return children.get(name);
	}
	
	public String getAbsolutePath() {
		// Build directory path recursively 
		if (name.equals(Directory.ROOT_DIRECTORY_NAME))
			return "";
		
		return getParent().getAbsolutePath() + DIR_SEPARATOR + name;
	} 
	
	public HashMap<String, FileSystemEntitiy> getChildren() {
		return children;
	}
	
	// Setters
	public void setParent(Directory newParent) {
		// DANGER! Can break the entire structure, therefore will only change 
		// the parent directory in case the directory is parent of itself. 
		// This functions purpose is to give some more flexibility when designing 
		// the directory structure and allow to re-design the directory structure at runtime.
		// (i.e. it's possible to change the root of the directory structure dynamically).
		if (children.get(PARENT_DIR_NAME) == this)
			addChild(PARENT_DIR_NAME, newParent);			
			
		// TODO: else throw an exception?
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public void addChild(FileSystemEntitiy entitiy) {
		children.put(entitiy.getName(), entitiy);
	}
	
	private void addChild(String name, Directory dir) {
		// Add a directory, but index it using the specified name,
		// instead of using the directory's name.
		// Used to add child and parent in Directory instantiation.
		children.put(name, dir);
	}
	
	private void updateSize() {
		// dir size = own size + parent size
		size = ENTRY_COST * children.size();
	}
	
	public void removeChild(String entryName) {
		children.remove(entryName);
	}

	@Override
	public int getSize() {
		updateSize();
		return size;
	}
	
	@Override
	public String toString() {
		return toString(name);
	}
	
	@Override
	public String toString(String name) {
		updateSize();
		return "d " + super.toString(name);
	}
	
	
}
