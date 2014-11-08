package poof.core;

import java.util.HashMap;

public class Directory extends FileSystemEntitiy {
	private final int ENTRY_COST = 8; // cost of the each entry inside the directory
	private final String PARENT_DIR_NAME = "..";
	private final String THIS_DIR_NAME = ".";
	
	private HashMap<String, FileSystemEntitiy> children;
	
	public Directory(String name, User owner, Directory parent) { 
		super(name, owner);
		children = new HashMap<String, FileSystemEntitiy>();
		
		if (parent == null)
			// the root is of the dir, is the dir itself
			parent = this;
		
		// Add the the current (.) and parent (..) dirs to children 
		addChild(THIS_DIR_NAME, this);
		addChild(PARENT_DIR_NAME, parent);
		
	}
	
	public Directory getParent() {
		// NOTE: DOWNCAST! DANGER!
		return (Directory) children.get(PARENT_DIR_NAME);
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
