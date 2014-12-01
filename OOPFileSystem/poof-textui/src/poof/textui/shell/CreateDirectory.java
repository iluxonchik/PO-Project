/** @version $Id: CreateDirectory.java,v 1.3 2014/11/11 10:16:45 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;

import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;

// FIXME: import project-specific classes
import poof.core.FileSystemManager;
import poof.textui.EntryExistsException;
import poof.textui.AccessDeniedException;

import poof.core.EntryExistsCoreException;
import poof.core.AccessDeniedCoreException;

/**
 * §2.2.6.
 */
public class CreateDirectory extends Command<FileSystemManager> {
	/**
	 * @param receiver
	 */
	public CreateDirectory(FileSystemManager receiver) {
		super(MenuEntry.MKDIR, receiver);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		
		String dirName = IO.readString(Message.directoryRequest());		
		try {
			_receiver.createDirectory(dirName);
		} catch(EntryExistsCoreException e) { throw new EntryExistsException(dirName); }
		catch (AccessDeniedCoreException e) { throw new AccessDeniedException(e.getUsername()); }
	}

}
