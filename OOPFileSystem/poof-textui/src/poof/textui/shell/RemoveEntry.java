/** @version $Id: RemoveEntry.java,v 1.4 2014/11/30 12:37:55 ist178134 Exp $ */
package poof.textui.shell;

import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import static ist.po.ui.Dialog.IO;



import poof.core.AccessDeniedCoreException;
import poof.core.EntryUnknownCoreException;
// FIXME: import project-specific classes
import poof.core.FileSystemManager;
import poof.core.IllegalRemovalCoreException;
import poof.textui.AccessDeniedException;
import poof.textui.EntryUnknownException;
import poof.textui.IllegalRemovalException;
import poof.textui.shell.MenuEntry;
import poof.textui.shell.Message;

/**
 * §2.2.3.
 */
public class RemoveEntry extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {
	/**
	 * @param receiver
	 */
	public RemoveEntry(FileSystemManager reciever /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.RM, reciever /*FIXME: receiver argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		//FIXME: implement command
		
		String entryName = IO.readString(Message.nameRequest());
		try {
			_receiver.removeEntry(entryName);
		} catch (EntryUnknownCoreException e) {
			throw new EntryUnknownException(entryName);
		} catch (AccessDeniedCoreException e) {
			String activeUsername = _receiver.getActiveUser().getUsername();
			throw new AccessDeniedException(activeUsername);
		} catch (IllegalRemovalCoreException e) {
			throw new IllegalRemovalException();
		}
		
	}
}
