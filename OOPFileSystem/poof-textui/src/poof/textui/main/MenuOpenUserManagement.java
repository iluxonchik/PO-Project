/** @version $Id: MenuOpenUserManagement.java,v 1.3 2014/11/08 23:59:41 ist178134 Exp $ */
package poof.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;

// FIXME: import project-specific classes


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
