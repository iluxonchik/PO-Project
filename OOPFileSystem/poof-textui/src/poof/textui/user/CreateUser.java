/** @version $Id: CreateUser.java,v 1.8 2014/11/30 22:05:19 ist178134 Exp $ */
package poof.textui.user;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import poof.core.FileSystemManager;
import poof.core.UserExistsCoreException;
import poof.core.AccessDeniedCoreException;
import poof.textui.AccessDeniedException;
import poof.textui.UserExistsException;
import poof.textui.user.Message;
/**
 * §2.3.1.
 */
public class CreateUser extends Command <FileSystemManager> {
	/**
	 * @param receiver
	 */
	public CreateUser(FileSystemManager reciever) {
		super(MenuEntry.CREATE_USER, reciever);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {

		String username, name;
		
		// request new users username and name
		username = IO.readString(Message.usernameRequest());
		name = IO.readString(Message.nameRequest());
		try {
			_receiver.createUser(username, name);
		} catch (AccessDeniedCoreException e) { throw new AccessDeniedException(e.getUsername()); }
		catch (UserExistsCoreException e) { throw new UserExistsException(username); }
	}
}
