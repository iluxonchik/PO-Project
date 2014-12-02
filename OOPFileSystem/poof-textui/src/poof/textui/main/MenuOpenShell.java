/** @version $Id: MenuOpenShell.java,v 1.5 2014/12/01 07:54:04 ist178134 Exp $ */
package poof.textui.main;

import ist.po.ui.Command;
import ist.po.ui.ValidityPredicate;

import poof.core.FileSystemManager;

/**
 * Open shell menu.
 */
public class MenuOpenShell extends Command<FileSystemManager> {

	/**
	 * @param receiver
	 */
	public MenuOpenShell(final FileSystemManager receiver) {
		super(MenuEntry.MENU_SHELL, receiver, new ValidityPredicate<FileSystemManager>(receiver) {

			@Override
			public boolean isValid() {
				return receiver.getActiveUser() != null; // only show if there is a logged in user
			}
			
		});
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() {
		poof.textui.shell.MenuBuilder.menuFor(_receiver);
	}

}
