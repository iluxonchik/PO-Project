/** @version $Id: Shell.java,v 1.2 2014/11/08 20:24:15 ist178134 Exp $ */
package poof.textui;

import static ist.po.ui.Dialog.IO;


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

		String datafile = System.getProperty("import"); //$NON-NLS-1$
		if (datafile != null) {
			// FIXME: import text data file
		}
		
		// TODO: Create new FileSystemManager
		FileSystemManager fsManager = new FileSystemManager();
		
		poof.textui.main.MenuBuilder.menuFor(fsManager /*FIXME: core object (receiver) argument*/);
		IO.closeDown();
	}

}
