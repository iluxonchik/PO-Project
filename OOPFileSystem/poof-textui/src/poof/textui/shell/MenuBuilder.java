/** @version $Id: MenuBuilder.java,v 1.2 2014/11/08 20:24:14 ist178134 Exp $ */
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
	public static void menuFor(FileSystemManager fsManager) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new ListAllEntries(fsManager),
				new ListEntry(fsManager),
				new RemoveEntry(fsManager),
				new ChangeWorkingDirectory(fsManager),
				new CreateFile(fsManager),
				new CreateDirectory(fsManager),
				new ShowWorkingDirectory(fsManager),
				new AppendDataToFile(fsManager),
				new ShowFileData(fsManager),
				new ChangeEntryPermissions(fsManager),
				new ChangeOwner(fsManager),
				});
		menu.open();
	}

}
