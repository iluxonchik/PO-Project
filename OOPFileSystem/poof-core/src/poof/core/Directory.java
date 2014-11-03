package poof.core;

import java.util.HashMap;

public class Directory extends FileSystemEntity {
	private final int ENTRY_COST = 8; // cost of the each entry inside the directory
	HashMap<String, FileSystemEntity> children;
	
	public Directory(String name, User owner) { 
		super(name, owner);
		children = new HashMap<String, FileSystemEntity>();
		
		// TODO: Add parent(..) and current (.) directory references
		
	}
	
	
}
