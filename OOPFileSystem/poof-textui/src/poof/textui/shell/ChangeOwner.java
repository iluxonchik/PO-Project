/** @version $Id: ChangeOwner.java,v 1.4 2014/12/01 07:54:05 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import poof.core.AccessDeniedCoreException;
import poof.core.EntryUnknownCoreException;
import poof.core.FileSystemManager;
import poof.core.UserUnknownCoreException;
import poof.textui.AccessDeniedException;
import poof.textui.EntryUnknownException;
import poof.textui.UserUnknownException;

/**
 * §2.2.11.
 */
public class ChangeOwner extends Command<FileSystemManager>{
	/**
	 * @param receiver
	 */
	public ChangeOwner(FileSystemManager reciever) {
		super(MenuEntry.CHOWN, reciever);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		String entryName, newOwnerUsername;
		entryName = IO.readString(Message.nameRequest());
		newOwnerUsername = IO.readString(Message.usernameRequest());
		
		try {
			_receiver.changeOwner(entryName, newOwnerUsername);
		} catch (EntryUnknownCoreException e) {
			throw new EntryUnknownException(entryName);
		} catch (AccessDeniedCoreException e) {
			throw new AccessDeniedException(newOwnerUsername);

		} catch (UserUnknownCoreException e) {
			throw new UserUnknownException(newOwnerUsername);
		}
	}

}
