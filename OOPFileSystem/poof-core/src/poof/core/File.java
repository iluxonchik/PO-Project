package poof.core;

public class File extends FileSystemEntitiy {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1035258600217016351L;
	private String content;
	
	public File(String name, User owner, String content) { 
		super (name, owner); 
		
		if (content == null)
			this.content = null;
		else
			this.content = content + "\n";
		
		entitiyType = EntitiyType.FILE;
	}
	
	public File(String name, User owner) {
		this(name, owner, null);
	}
	
	public void appendData(String data) {
		if (content == null)
			content = data + "\n";
		else
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
	
	@Override
	public String toString() {
		return "- " + super.toString(name);
	}

}
