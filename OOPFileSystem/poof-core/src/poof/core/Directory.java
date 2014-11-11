package poof.core;

import java.util.HashMap;

public class Directory extends FileSystemEntitiy {
	private final int ENTRY_COST = 8; // cost of the each entry inside the directory
	private final String PARENT_DIR_NAME = "..";
	private final String THIS_DIR_NAME = ".";
	
	private HashMap<String, FileSystemEntitiy> children;
	
	
	// Constants
	public static final String ROOT_DIRECTORY_NAME = "/";
	public static final String HOME_DIRECTORY_NAME = "home";
	public static final String DIR_SEPARATOR = "/";
	
	public Directory(String name, User owner, Directory parent) { 
		super(name, owner);
		children = new HashMap<String, FileSystemEntitiy>();
		
		if (parent == null)
			// if the parent dir is null, the directory is parent of itself
			// used for root directory
			parent = this;
		
		// Add the the current (.) and parent (..) dirs to children 
		addChild(THIS_DIR_NAME, this);
		addChild(PARENT_DIR_NAME, parent);
		
	}
	
	// Getters
	public Directory getParent() {
		// NOTE: DOWNCAST! DANGER!
		return (Directory) children.get(PARENT_DIR_NAME);
	}
	
	public Directory getChild(String name) {
		// NOTE: DOWNCAST! DANGER!
		return (Directory) children.get(name);
	}
	
	public String getAbsolutePath() {
		// Build directory path recursively 
		if (name == Directory.ROOT_DIRECTORY_NAME)
			return "";
		
		return getParent().getAbsolutePath() + Directory.DIR_SEPARATOR + name;
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
	
	
	
	public void addChild(FileSystemEntitiy entitiy) {
		children.put(entitiy.getName(), entitiy);
	}
	
	private void addChild(String name, Directory dir) {
		// Add a directory, but index it using the specified name,
		// instead of using the directory's name.
		// Used to add child and parent in Directory instantiation.
		children.put(name, dir);
	}
	
}
