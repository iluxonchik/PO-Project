/** @version $Id: MenuBuilder.java,v 1.1 2014/10/01 22:45:54 david Exp $ */
package poof.textui.shell;

import ist.po.ui.Command;
import ist.po.ui.Menu;

// FIXME: import project-specific classes

import poof.core.FileSystemManager;

/**
 * Menu builder for shell operations.
 */
public class MenuBuilder {

	/**
	 * @param receiver
	 */
	public static void menuFor(FileSystemManager fsManager /*FIXME: receiver declaration*/) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new ListAllEntries(fsManager /*FIXME: receiver argument*/),
				new ListEntry(fsManager /*FIXME: receiver argument*/),
				new RemoveEntry(fsManager /*FIXME: receiver argument*/),
				new ChangeWorkingDirectory(fsManager /*FIXME: receiver argument*/),
				new CreateFile(fsManager /*FIXME: receiver argument*/),
				new CreateDirectory(fsManager /*FIXME: receiver argument*/),
				new ShowWorkingDirectory(fsManager /*FIXME: receiver argument*/),
				new AppendDataToFile(fsManager /*FIXME: receiver argument*/),
				new ShowFileData(fsManager/*FIXME: receiver argument*/),
				new ChangeEntryPermissions(fsManager /*FIXME: receiver argument*/),
				new ChangeOwner(fsManager /*FIXME: receiver argument*/),
				});
		menu.open();
	}

}
