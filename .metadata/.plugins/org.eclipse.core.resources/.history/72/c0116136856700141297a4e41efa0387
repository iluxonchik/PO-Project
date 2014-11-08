/** @version $Id: MenuBuilder.java,v 1.1 2014/10/01 22:45:55 david Exp $ */
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
	public static void menuFor(FileSystemManager fsManager /*FIXME: receiver declaration*/) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new CreateUser(fsManager /*FIXME: receiver argument*/),
				new ListAllUsers(fsManager /*FIXME: receiver argument*/),
				});
		menu.open();
	}

}
