/** @version $Id: MenuOpenUserManagement.java,v 1.5 2014/12/01 07:54:04 ist178134 Exp $ */
package poof.textui.main;

import ist.po.ui.Command;
import ist.po.ui.ValidityPredicate;
import poof.core.FileSystemManager;

/**
 * Open user management menu.
 */
public class MenuOpenUserManagement extends Command<FileSystemManager> {

	/**
	 * @param receiver
	 */
	public MenuOpenUserManagement(final FileSystemManager receiver) {
		super(MenuEntry.MENU_USER_MGT, receiver, new ValidityPredicate<FileSystemManager>(receiver) {

			@Override
			public boolean isValid() {
				return receiver.getActiveUser() != null; // only show if there is an active User
			}
		});
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() {
		poof.textui.user.MenuBuilder.menuFor(_receiver);
	}

}
