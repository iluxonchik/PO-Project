/** @version $Id: MenuBuilder.java,v 1.4 2014/12/01 07:54:05 ist178134 Exp $ */
package poof.textui.user;

import ist.po.ui.Command;
import ist.po.ui.Menu;

// FIXME: import project-specific classes
import poof.core.FileSystemManager;

/**
 * Menu builder for search operations.
 */
public class MenuBuilder {

	/**
	 * @param receiver
	 */
	public static void menuFor(FileSystemManager fsManager) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new CreateUser(fsManager),
				new ListAllUsers(fsManager),
				});
		menu.open();
	}

}
