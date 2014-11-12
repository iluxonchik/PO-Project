/** @version $Id: ListEntry.java,v 1.3 2014/11/11 10:16:45 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;


import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;

// FIXME: import project-specific classes
import poof.core.FileSystemManager;
import poof.core.EntryUnknownCoreException;
import poof.textui.EntryUnknownException;
import poof.textui.shell.Message;


/**
 * §2.2.2.
 */
public class ListEntry extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {
	/**
	 * @param receiver
	 */
	public ListEntry(FileSystemManager receiver /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.LS_ENTRY, receiver /*FIXME: receiver argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		//FIXME: implement command
		String dirNameToList = IO.readString(Message.nameRequest());
		
		try {
			IO.println(_receiver.listEntry(dirNameToList));
		} catch (EntryUnknownCoreException e) { throw new EntryUnknownException(dirNameToList); }
	}

}
