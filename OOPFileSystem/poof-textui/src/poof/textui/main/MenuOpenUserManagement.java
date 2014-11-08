/** @version $Id: MenuOpenUserManagement.java,v 1.2 2014/11/08 20:24:14 ist178134 Exp $ */
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
	public MenuOpenUserManagement(FileSystemManager reciever /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.MENU_USER_MGT, reciever /*FIXME: receiver argument*/ , new ValidityPredicate<FileSystemManager>(reciever) {

			@Override
			public boolean isValid() {
				// TODO Auto-generated method stub
				return false;
			}
			
		} /*FIXME: validity argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() {
		poof.textui.user.MenuBuilder.menuFor(_receiver /*FIXME: receiver argument*/);
	}

}
