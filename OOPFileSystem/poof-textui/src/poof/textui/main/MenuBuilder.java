/** @version $Id: MenuBuilder.java,v 1.3 2014/12/01 07:54:04 ist178134 Exp $ */
package poof.textui.main;

import ist.po.ui.Command;
import ist.po.ui.Menu;

import poof.core.FileSystemManager;

/**
 * Menu builder.
 */
public abstract class MenuBuilder {

	/**
	 * @param receiver
	 */
	public static void menuFor(FileSystemManager fsManager) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new New(fsManager),
				new Open(fsManager),
				new Save(fsManager),
				new Login(fsManager),
				new MenuOpenShell(fsManager),
				new MenuOpenUserManagement(fsManager)
		});
		menu.open();
	}

}
