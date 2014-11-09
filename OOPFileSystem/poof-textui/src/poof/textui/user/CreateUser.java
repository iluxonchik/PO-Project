/** @version $Id: CreateUser.java,v 1.3 2014/11/09 20:13:12 ist178134 Exp $ */
package poof.textui.user;

import static ist.po.ui.Dialog.IO;

import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;

// FIXME: import project-specific classes
import poof.core.FileSystemManager;
import poof.core.FileSystem; // For constants
import poof.core.User;
/**
 * §2.3.1.
 */
public class CreateUser extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {
	/**
	 * @param receiver
	 */
	public CreateUser(FileSystemManager reciever /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.CREATE_USER, reciever /*FIXME: receiver argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		//FIXME: implement command
		User activeUser = _receiver.getActiveUser();
		

		
		
		//NOTE: Only root user can create new users
	}
}
