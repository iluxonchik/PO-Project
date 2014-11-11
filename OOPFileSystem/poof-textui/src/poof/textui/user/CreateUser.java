/** @version $Id: CreateUser.java,v 1.5 2014/11/10 20:54:47 ist178134 Exp $ */
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
import poof.textui.main.Message;
/**
 * §2.3.1.
 */
public class CreateUser extends Command <FileSystemManager> /* FIXME: select core type for receiver */ {
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
		String username, name;
		User activeUser = _receiver.getActiveUser();
		
		// request new users username and name
		username = IO.readString(poof.textui.user.Message.usernameRequest());
		name = IO.readString(poof.textui.user.Message.nameRequest());
		
		_receiver.createUser(username, name);

		//NOTE: Only root user can create new users
	}
}
