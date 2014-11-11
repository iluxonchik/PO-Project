/** @version $Id: Login.java,v 1.5 2014/11/11 09:26:49 ist178134 Exp $ */
package poof.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;


// FIXME: import project-specific classes
import poof.core.FileSystemManager;
import poof.core.UserUnknownCoreException;
import poof.textui.UserUnknownException;
import poof.textui.main.Message;

/**
 * §2.1.2.
 */
public class Login extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {

	/**
	 * @param receiver
	 */
	public Login(final FileSystemManager receiver /*FIXME: add receiver declaration: type must agree with the above*/) {
		
		super(MenuEntry.LOGIN, receiver /*FIXME: receiver argument*/, new ValidityPredicate<FileSystemManager>(receiver) {

			@Override
			public boolean isValid() {
				// TODO Auto-generated method stub
				return receiver.getActiveFileSystem() != null; // only show if there is an active FileSystem
			}
			
		}/*FIXME: validity argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		//FIXME: implement command
		
		String userToLogin = IO.readString(Message.usernameRequest());
		
		try {
			_receiver.login(userToLogin);
		} catch (UserUnknownCoreException e) {
			throw new UserUnknownException(userToLogin);
		}

	}
}
