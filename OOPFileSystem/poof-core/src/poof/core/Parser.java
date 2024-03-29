package poof.core;

import java.util.ArrayList;


public class Parser {
	private FileSystemManager fsm;

	private final String PACKAGE_PATH = "poof.core.";
	private final String DIRECTORY_SEPARATOR = "/";
	private final String IMPORT_ARGS_SEPARATOR = "\\|";
	private final String PUBLIC_LITERAL = "public";
	
	public Parser (FileSystemManager fsm) {
		this.fsm = fsm;
	}
	
	
	public void parse(String strToParse) {
		String data[] = strToParse.split(IMPORT_ARGS_SEPARATOR);
		ArrayList<String> ctoargs = new ArrayList<String>(data.length-1);
		data[0] = formatClassName(data[0]);
		
		try {
			Class<?> type = Class.forName(PACKAGE_PATH + data[0]);

			
			for (int i = 1; i < data.length; i++){
				// build arguments list
				ctoargs.add(data[i]);
			}
			
			if(type == User.class){
				fsm.setNeedsSaving(true);
				createUser(ctoargs);
			}
			else if(type == Directory.class) {
				fsm.setNeedsSaving(true);
				createDirectory(ctoargs, false, true);
			}
			else if(type == File.class) {
				fsm.setNeedsSaving(true);
				createFile(ctoargs);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	
	}
	
	
	private void createUser(ArrayList<String> ctoargs) {
		
		User newUser = new User(ctoargs.get(0), ctoargs.get(1));
		
		// build users home directory
		String homeDirPath = ctoargs.get(2);
		Directory homeDir = buildDirectory(homeDirPath, fsm.getRootDirectory(), newUser);
		newUser.setMainDir(homeDir); // set users new home directory
		fsm.addUser(newUser);
		
	}
	
	private Directory buildDirectory(String path, Directory rootDir, User owner) {
		int i;
		String[] dirParts = path.split(DIRECTORY_SEPARATOR);
		
		Directory newDir;
		
		for (i = 1; i < dirParts.length-1; i++) {
			
			if(rootDir.getChild(dirParts[i]) == null ) {				
				// if sub directory doesn't exist, create it
				newDir = new Directory(dirParts[i], rootDir);
				rootDir.addChild(newDir);
			}
	
			rootDir = (Directory) rootDir.getChild(dirParts[i]); // update parent of next directory
		}
		

		// Code below performs actions specifically on the last entry		
		// force owner on the last directory
		if(rootDir.getChild(dirParts[i]) == null ) {				
			// if sub directory doesn't exist, create it
			
			if (owner != null) {
				newDir = new Directory(dirParts[i], owner, rootDir);
			}
			else
				// last directory's owner is not set, use parent as owner (useful for file creation)
				newDir = new Directory(dirParts[i], rootDir);
			
			rootDir.addChild(newDir);
		}

		rootDir = (Directory) rootDir.getChild(dirParts[i]); // update parent of next directory
		
		return rootDir;
	}
	
	private String formatClassName(String className) {
		// formats class name to the correct format
		// (i.e. first letter uppercase, the rest lowercase).
		className = className.toLowerCase();
		char firstChar = Character.toUpperCase(className.charAt(0));
		className = firstChar + className.substring(1);
		return className;
	}
	
	private Directory createDirectory(ArrayList<String> ctoargs, boolean useParentOwnerForAll, boolean changePrivacy) {
		FileSystem fs = fsm.getActiveFileSystem();
		User owner;
		
		if (useParentOwnerForAll) {
			owner = null;
		}
		else {
			owner = fs.getUser(ctoargs.get(1));
		}

		Directory dir = buildDirectory(ctoargs.get(0),fs.getRootDirectory(), owner);
		
		if(changePrivacy) {		
			if(ctoargs.get(2).equals(PUBLIC_LITERAL))
				// if directory is public, set it to public
				dir.setPrivacyMode(PrivacyMode.PUBLIC);
		}
		
		return dir;
	}
	
	private void createFile(ArrayList<String> ctoargs) {
		Directory dir;
		String dirPath = "";
		String fileContent = ctoargs.get(ctoargs.size() - 1);
		int i; // used in for cycle and after it
		
		if (fileContent == "")
			fileContent = null;
			
		String pathParts[]= ctoargs.get(0).split(DIRECTORY_SEPARATOR); 
		String fileName = pathParts[pathParts.length - 1]; // get the file name
		
		// build new directory path (remove the filename)
		for (i = 0; i < pathParts.length - 2; i++) {
			dirPath += pathParts[i] + "/";
		}
		dirPath += pathParts[i];
		
		ctoargs.set(0, dirPath);
		
		// remove the file name(s) and pass it to directory creator
		ctoargs.remove(ctoargs.size() - 1); 
		dir = createDirectory(ctoargs, true, false);
				
		// add files to the directory
		dir.addChild(new File(fileName, dir.getOwner(), fileContent));
		FileSystemEntitiy fsEnt = dir.getChild(fileName);
		
		User owner = fsm.getActiveFileSystem().getUser(ctoargs.get(1)); // get the specified owner
		fsEnt.setOwner(owner);
		
		if(ctoargs.get(2).equals(PUBLIC_LITERAL))
			// if directory is public, set it to public
			fsEnt.setPrivacyMode(PrivacyMode.PUBLIC);
	}

}