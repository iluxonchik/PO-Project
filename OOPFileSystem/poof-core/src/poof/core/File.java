package poof.core;

public class File extends FileSystemEntitiy {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1035258600217016351L;
	String content;
	
	public File(String name, User owner) { 
		super (name, owner); 
		
		entitiyType = EntitiyType.FILE;
	}
	
	public void appendData(String data) {
		content += data;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
