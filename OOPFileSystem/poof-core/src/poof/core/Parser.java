package poof.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class Parser {
	private FileSystemManager fsm;

	private final String PACKAGE_PATH = "poof.core.";
	
	public Parser (FileSystemManager fms) {
		this.fsm = fms;
	}
	
	
	public void parse(String strToParse) {
		String data[] = strToParse.split("\\|");
		ArrayList<String> ctoargs = new ArrayList<String>(data.length-1);
		data[0] = formatClassName(data[0]);
		
		try {
			Class<?> type = Class.forName(PACKAGE_PATH + data[0]);

			
			for (int i = 1; i < data.length; i++){
				ctoargs.add(data[i]);
			}
			
			if(type == User.class){
				createUser(ctoargs);
			}
			else if(type == Directory.class) {
				createDirectory(ctoargs);
			}
			else if(type == File.class) {
				createFile(ctoargs);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	
	}
	
	
	private void createUser(ArrayList<String> ctoargs) {
		
		FileSystem fs = fsm.getActiveFileSystem();
		User newUser = new User(ctoargs.get(0), ctoargs.get(1));
		
		// build users home directory
		String homeDirPath = ctoargs.get(2);
		Directory homeDir = buildDirectory(homeDirPath, fs.getRootDirectory(), newUser);
		newUser.setMainDir(homeDir); // set users new home directory
		fs.addUser(newUser);
		
	}
	
	private Directory buildDirectory(String path, Directory rootDir, User owner) {
		String[] dirParts = path.split("/");
		
		Directory newDir;
		for (int i = 1; i < dirParts.length; i++) {
			
			if(rootDir.getChild(dirParts[i]) ==null ) {
				// if sub directory doesn't exist, create it
				newDir = new Directory(dirParts[i], owner, rootDir);
				rootDir.addChild(newDir);
				
			}
			rootDir = rootDir.getChild(dirParts[i]); // update parent of next directory
		
		}
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
	
	private void createDirectory(ArrayList<String> ctoargs) {
		FileSystem fs = fsm.getActiveFileSystem();
		Directory dir = buildDirectory(ctoargs.get(0),fs.getRootDirectory(), fs.getUser(ctoargs.get(1)));
		
		if(ctoargs.get(2) == "public")
			// if directory is public, set it to public
			dir.setPrivacyMode(PrivacyMode.PUBLIC);
	}
	
	private void createFile(ArrayList<String> ctoargs) {
		// TODO
	}

}
