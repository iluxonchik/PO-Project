/** @version $Id: ChangeEntryPermissions.java,v 1.3 2014/11/12 08:56:37 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

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
public class ChangeEntryPermissions extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {
	/**
	 * @param receiver
	 */
	public ChangeEntryPermissions(FileSystemManager reciever /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.CHMOD, reciever /*FIXME: receiver argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		//FIXME: implement command
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
			// TODO Auto-generated catch block
			throw new EntryUnknownException(entryName);
		} catch (AccessDeniedCoreException e) {
			// TODO Auto-generated catch block
			String activeUsername = _receiver.getActiveUser().getName();
			throw new AccessDeniedException(activeUsername);
		}
	}

}
