/** @version $Id: ChangeWorkingDirectory.java,v 1.3 2014/11/12 08:56:37 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;




import poof.core.EntryUnknownCoreException;
import poof.core.FileSystemManager;
import poof.core.IsNotDirectoryCoreException;
import poof.textui.EntryUnknownException;
import poof.textui.IsNotDirectoryException;
import poof.textui.shell.MenuEntry;
import poof.textui.shell.Message;

/**
 * §2.2.4.
 */
public class ChangeWorkingDirectory extends Command<FileSystemManager> {
	/**
	 * @param receiver
	 */
	public ChangeWorkingDirectory(FileSystemManager reciever) {
		super(MenuEntry.CD, reciever);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {

		String dirName = IO.readString(Message.directoryRequest());

		try {
			_receiver.changeWorkingDirectory(dirName);
		} catch (EntryUnknownCoreException e) { throw new EntryUnknownException(dirName); } 
		catch (IsNotDirectoryCoreException e) {	throw new IsNotDirectoryException(dirName); }
	}

}
