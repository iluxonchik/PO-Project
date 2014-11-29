package poof.core;

public class File extends FileSystemEntitiy {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1035258600217016351L;
	String content;
	
	public File(String name, User owner, String content) { 
		super (name, owner); 
		this.content = content;
		
		entitiyType = EntitiyType.FILE;
	}
	
	public File(String name, User owner) {
		this(name, owner, null);
	}
	
	public void appendData(String data) {
		content += data + "\n";
	}

	public String getContent() {
		return content;
	}
	
	@Override
	public int getSize() {
		if (content != null)
			return content.length();
		return 0;
	}

}
