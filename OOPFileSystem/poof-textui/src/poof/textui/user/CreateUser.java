/** @version $Id: CreateUser.java,v 1.7 2014/11/11 10:16:45 ist178134 Exp $ */
package poof.textui.user;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;






// FIXME: import project-specific classes
import poof.core.FileSystemManager;
import poof.core.UserExistsCoreException;
import poof.core.AccessDeniedCoreException;
import poof.textui.AccessDeniedException;
import poof.textui.UserExistsException;
import poof.textui.user.Message;
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
		
		// request new users username and name
		username = IO.readString(Message.usernameRequest());
		name = IO.readString(Message.nameRequest());
		try {
			_receiver.createUser(username, name);
		} catch (AccessDeniedCoreException e) { throw new AccessDeniedException(username); }
		catch (UserExistsCoreException e) { throw new UserExistsException(username); }
		//NOTE: Only root user can create new users
	}
}
