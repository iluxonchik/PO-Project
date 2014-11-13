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
		System.out.println(data.length);
		ArrayList<String> ctoargs = new ArrayList<String>(data.length-1);
		
		
		
		try {
			Class<?> type = Class.forName(PACKAGE_PATH + data[0]);

			
			for (int i = 1; i < data.length; i++){
				ctoargs.add(data[i]);
			}
			
			if(type == User.class){
				createUser(ctoargs);
			}
			else if(type == Directory.class) {
				createDirectory(ctoargs.toArray());
			}
			else if(type == File.class) {
				createFile(ctoargs.toArray());
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
	
	private void createDirectory(Object[] ctoargs) {
		
	}
	
	private void createFile(Object[] ctoargs) {
	
	}
	
	
	/*
	public Directory createDirectory(String[] data, FileSystem fs) {
			try {					
					String pathEntries[] = data[2].split("/");
					Directory curDir = fs.getRootDirectory();
					for (int ix = 1; ix < pathEntries.length; ix++){
						if (curDir.getChild(pathEntries[ix]) == null){
							curDir.addChild(new Directory(pathEntries[ix], curDir));
						}
						curDir = curDir.getChild(pathEntries[ix]);
					}
			}
				Constructor<?> ctor = type.getConstructor(String.class, User.class);
				return ctor.newInstance(name, fs.getHomeDirectory());
				

		   } catch (InstantiationException e) {e.printStackTrace(); return null; }
			catch (IllegalAccessException e) {e.printStackTrace(); return null; }
			catch (IllegalArgumentException e) {e.printStackTrace(); return null; }
			catch (InvocationTargetException e) {e.printStackTrace(); return null; }
			catch (NoSuchMethodException e) { e.printStackTrace(); return null; }
			
	}
	*/
}
