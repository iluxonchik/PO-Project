package poof.core;

public class AccessDeniedCoreException extends FileSystemManagerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7910661931071810255L;
	
	private String username;
	
	public AccessDeniedCoreException(String username)
	{
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}

}
