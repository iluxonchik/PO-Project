/** @version $Id: ChangeWorkingDirectory.java,v 1.3 2014/11/12 08:56:37 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;




import poof.core.EntryUnknownCoreException;
// FIXME: import project-specific classes
import poof.core.FileSystemManager;
import poof.core.IsNotDirectoryCoreException;
import poof.textui.EntryUnknownException;
import poof.textui.IsNotDirectoryException;
import poof.textui.shell.MenuEntry;
import poof.textui.shell.Message;

/**
 * §2.2.4.
 */
public class ChangeWorkingDirectory extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {
	/**
	 * @param receiver
	 */
	public ChangeWorkingDirectory(FileSystemManager reciever /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.CD, reciever /*FIXME: receiver argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		//FIXME: implement command
		String dirName = IO.readString(Message.directoryRequest());

		try {
			_receiver.changeWorkingDirectory(dirName);
		} catch (EntryUnknownCoreException e) { throw new EntryUnknownException(dirName); } 
		catch (IsNotDirectoryCoreException e) {	throw new IsNotDirectoryException(dirName); }
	}

}
