package poof.core;

import java.util.ArrayList;
import java.util.List;


public class Parser {
	private FileSystemManager fsm;

	private final String PACKAGE_PATH = "poof.core.";
	private final String DIRECTORY_SEPARATOR = "/";
	private final String IMPORT_ARGS_SEPARATOR = "\\|";
	private final String PUBLIC_LITERAL = "public";
	private final String FILENAME_SEPARATOR = "; ";
	
	public Parser (FileSystemManager fms) {
		this.fsm = fms;
	}
	
	
	public void parse(String strToParse) {
		String data[] = strToParse.split(IMPORT_ARGS_SEPARATOR);
		ArrayList<String> ctoargs = new ArrayList<String>(data.length-1);
		data[0] = formatClassName(data[0]);
		
		try {
			Class<?> type = Class.forName(PACKAGE_PATH + data[0]);

			
			for (int i = 1; i < data.length; i++){
				ctoargs.add(data[i]);
			}
			
			if(type == User.class){
				fsm.setNeedsSaving(true);
				createUser(ctoargs);
			}
			else if(type == Directory.class) {
				fsm.setNeedsSaving(true);
				createDirectory(ctoargs);
			}
			else if(type == File.class) {
				fsm.setNeedsSaving(true);
				createFile(ctoargs, fsm.getActiveUser());
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
		String[] dirParts = path.split(DIRECTORY_SEPARATOR);
		
		Directory newDir;
		for (int i = 1; i < dirParts.length; i++) {
			
			if(rootDir.getChild(dirParts[i]) ==null ) {
				// if sub directory doesn't exist, create it
				newDir = new Directory(dirParts[i], owner, rootDir);
				rootDir.addChild(newDir);
				
			}
			//TODO: DANGER: DOWNCAST! ADD ADITIONAL CHECKS!
			rootDir = (Directory) rootDir.getChild(dirParts[i]); // update parent of next directory
		
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
	
	private Directory createDirectory(ArrayList<String> ctoargs) {
		FileSystem fs = fsm.getActiveFileSystem();
		Directory dir = buildDirectory(ctoargs.get(0),fs.getRootDirectory(), fs.getUser(ctoargs.get(1)));
				
		if(ctoargs.get(2).equals(PUBLIC_LITERAL))
			// if directory is public, set it to public
			dir.setPrivacyMode(PrivacyMode.PUBLIC);
		
		return dir;
	}
	
	private void createFile(ArrayList<String> ctoargs, User owner) {
		Directory dir;
		String fileNames[] = ctoargs.get(ctoargs.size() - 1).split(FILENAME_SEPARATOR);
		
		// remove the file name(s) and pass it to directory creator
		ctoargs.remove(ctoargs.size() - 1); 
		dir = createDirectory(ctoargs);
		
		// add files to the directory
		for (String fileName : fileNames)
			dir.addChild(new File(fileName, owner));
	}

}
