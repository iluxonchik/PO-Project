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
public class Login extends Command<FileSystemManager> {

	/**
	 * @param receiver
	 */
	public Login(final FileSystemManager receiver) {
		
		super(MenuEntry.LOGIN, receiver, new ValidityPredicate<FileSystemManager>(receiver) {

			@Override
			public boolean isValid() {
				return receiver.getActiveFileSystem() != null; // only show if there is an active FileSystem
			}
			
		});
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		
		String userToLogin = IO.readString(Message.usernameRequest());
		
		try {
			_receiver.login(userToLogin);
		} catch (UserUnknownCoreException e) {
			throw new UserUnknownException(userToLogin);
		}

	}
}
