/** @version $Id: Shell.java,v 1.2 2014/11/08 20:24:15 ist178134 Exp $ */
package poof.textui;

import static ist.po.ui.Dialog.IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;




// FIXME: import project-specific classes
import poof.core.FileSystemManager;

/**
 * Class that starts the application's textual interface.
 */
public class Shell {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// FIXME: create and initialize core objects

		FileSystemManager fsManager = new FileSystemManager();

		
		String datafile = System.getProperty("import"); //$NON-NLS-1$
		if (datafile != null) {
			// FIXME: import text data file
			try {
				BufferedReader br = new BufferedReader(new FileReader(datafile));
				fsManager.createFileSystem(); // first, create a new FileSystem
				
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		poof.textui.main.MenuBuilder.menuFor(fsManager /*FIXME: core object (receiver) argument*/);
		IO.closeDown();
	}

}
