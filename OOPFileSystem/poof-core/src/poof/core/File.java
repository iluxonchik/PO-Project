package poof.core;

public class File extends FileSystemEntity {
	String content;
	
	public File(String name, User owner) { super (name, owner); }
}
