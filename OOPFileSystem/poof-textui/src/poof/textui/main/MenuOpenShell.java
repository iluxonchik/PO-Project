/** @version $Id: MenuOpenShell.java,v 1.4 2014/11/11 07:49:39 ist178134 Exp $ */
package poof.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;


// FIXME: import project-specific classes
import poof.core.FileSystemManager;

/**
 * Open shell menu.
 */
public class MenuOpenShell extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {

	/**
	 * @param receiver
	 */
	public MenuOpenShell(final FileSystemManager receiver /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.MENU_SHELL, receiver /*FIXME: receiver argument*/ , new ValidityPredicate<FileSystemManager>(receiver) {

			@Override
			public boolean isValid() {
				// TODO Auto-generated method stub
				return receiver.getActiveUser() != null; // only show if there is a logged in user
			}
			
		} /*FIXME: validity argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() {
		poof.textui.shell.MenuBuilder.menuFor(_receiver /*FIXME: receiver argument*/);
	}

}
