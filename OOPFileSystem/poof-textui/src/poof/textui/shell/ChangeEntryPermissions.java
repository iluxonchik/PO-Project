/** @version $Id: ChangeEntryPermissions.java,v 1.4 2014/12/01 07:54:05 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;





import poof.core.AccessDeniedCoreException;
import poof.core.EntryUnknownCoreException;
// FIXME: import project-specific classes
import poof.core.FileSystemManager;
import poof.core.PrivacyMode;
import poof.textui.AccessDeniedException;
import poof.textui.EntryUnknownException;
import poof.textui.shell.MenuEntry;
import poof.textui.shell.Message;

/**
 * §2.2.10.
 */
public class ChangeEntryPermissions extends Command<FileSystemManager> {
	/**
	 * @param receiver
	 */
	public ChangeEntryPermissions(FileSystemManager reciever) {
		super(MenuEntry.CHMOD, reciever);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		PrivacyMode privacyMode;
		
		String entryName = IO.readString(Message.nameRequest());
		
		Boolean isPublic = IO.readBoolean(Message.publicAccess());
		
		if(isPublic)
			privacyMode = PrivacyMode.PUBLIC;
		else
			privacyMode = PrivacyMode.PRIVATE;
		
		try {
			_receiver.changeEntryPermissions(entryName, privacyMode);
		} catch (EntryUnknownCoreException e) {
			throw new EntryUnknownException(entryName);
		} catch (AccessDeniedCoreException e) {
			throw new AccessDeniedException(e.getUsername());
		}
	}

}
