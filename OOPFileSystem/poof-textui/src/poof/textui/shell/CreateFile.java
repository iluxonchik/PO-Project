/** @version $Id: CreateFile.java,v 1.5 2014/12/01 07:54:05 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;

import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import poof.core.AccessDeniedCoreException;
import poof.core.EntryExistsCoreException;
import poof.core.FileSystemManager;
import poof.textui.EntryExistsException;
import poof.textui.shell.MenuEntry;
import poof.textui.shell.Message;
import poof.textui.AccessDeniedException;

/**
 * §2.2.5.
 */
public class CreateFile extends Command<FileSystemManager> {
	/**
	 * @param receiver
	 */
	public CreateFile(FileSystemManager reciever) {
		super(MenuEntry.TOUCH, reciever);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		
		String filename = IO.readString(Message.fileRequest());
		
		try {
			_receiver.createFile(filename);
		} catch (AccessDeniedCoreException e) {
			throw new AccessDeniedException(e.getUsername());
		} 
		catch (EntryExistsCoreException e) {
			throw new EntryExistsException(filename);
		}
	}

}
