/** @version $Id: MenuOpenUserManagement.java,v 1.4 2014/11/11 10:16:46 ist178134 Exp $ */
package poof.textui.main;

import ist.po.ui.Command;
import ist.po.ui.ValidityPredicate;
import poof.core.FileSystemManager;

/**
 * Open user management menu.
 */
public class MenuOpenUserManagement extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {

	/**
	 * @param receiver
	 */
	public MenuOpenUserManagement(final FileSystemManager receiver /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.MENU_USER_MGT, receiver /*FIXME: receiver argument*/ , new ValidityPredicate<FileSystemManager>(receiver) {

			@Override
			public boolean isValid() {
				// TODO Auto-generated method stub
				return receiver.getActiveUser() != null; // only show if there is an active User
			}
		} /*FIXME: validity argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() {
		poof.textui.user.MenuBuilder.menuFor(_receiver /*FIXME: receiver argument*/);
	}

}
