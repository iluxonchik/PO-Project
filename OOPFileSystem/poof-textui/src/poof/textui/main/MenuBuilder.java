/** @version $Id: MenuBuilder.java,v 1.1 2014/10/01 22:45:52 david Exp $ */
package poof.textui.main;

import ist.po.ui.Command;
import ist.po.ui.Menu;

// FIXME: import project-specific classes
import poof.core.FileSystemManager;
/**
 * Menu builder.
 */
public abstract class MenuBuilder {

	/**
	 * @param receiver
	 */
	public static void menuFor(FileSystemManager fsManager /*FIXME: receiver declaration */) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new New(fsManager /*FIXME: receiver argument*/),
				new Open(fsManager /*FIXME: receiver argument*/),
				new Save(fsManager /*FIXME: receiver argument*/),
				new Login(fsManager /*FIXME: receiver argument*/),
				new MenuOpenShell(fsManager /*FIXME: receiver argument*/),
				new MenuOpenUserManagement(fsManager /*FIXME: receiver argument*/)
		});
		menu.open();
	}

}
