package poof.core;

public class File extends FileSystemEntitiy {
	String content;
	
	public File(String name, User owner) { super (name, owner); }

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
