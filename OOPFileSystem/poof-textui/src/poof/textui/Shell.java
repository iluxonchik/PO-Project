/** @version $Id: Shell.java,v 1.7 2014/12/01 07:54:05 ist178134 Exp $ */
package poof.textui;

import static ist.po.ui.Dialog.IO;

import poof.core.FileSystemManager;

/**
 * Class that starts the application's textual interface.
 */
public class Shell {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FileSystemManager fsManager = new FileSystemManager();
		
		String datafile = System.getProperty("import"); //$NON-NLS-1$
		if (datafile != null) {
			fsManager.initializeFromFile(datafile);
		}
	
		
		
		poof.textui.main.MenuBuilder.menuFor(fsManager);
		IO.closeDown();
	}

}
