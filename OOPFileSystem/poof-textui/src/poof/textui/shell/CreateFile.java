/** @version $Id: CreateFile.java,v 1.3 2014/11/17 20:35:10 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;



import poof.core.AccessDeniedCoreException;
import poof.core.EntryExistsCoreException;
// FIXME: import project-specific classes
import poof.core.FileSystemManager;
import poof.textui.EntryExistsException;
import poof.textui.shell.MenuEntry;
import poof.textui.shell.Message;

/**
 * §2.2.5.
 */
public class CreateFile extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {
	/**
	 * @param receiver
	 */
	public CreateFile(FileSystemManager reciever /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.TOUCH, reciever /*FIXME: receiver argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		
		String filename = IO.readString(Message.fileRequest());
		
		try {
			_receiver.createFile(filename);
		} catch (EntryExistsCoreException e) {
			throw new EntryExistsException(filename);
		} catch (AccessDeniedCoreException e) {
			throw new EntryExistsException(filename);
		}
	}

}
