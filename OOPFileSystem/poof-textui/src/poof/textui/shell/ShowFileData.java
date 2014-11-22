/** @version $Id: ShowFileData.java,v 1.3 2014/11/17 20:35:10 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;




import poof.core.EntryUnknownCoreException;
// FIXME: import project-specific classes
import poof.core.FileSystemManager;
import poof.core.IsNotFileCoreException;
import poof.textui.EntryUnknownException;
import poof.textui.IsNotFileException;
import poof.textui.shell.MenuEntry;
import poof.textui.shell.Message;

/**
 * §2.2.9.
 */
public class ShowFileData extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {
	/**
	 * @param receiver
	 */
	public ShowFileData(FileSystemManager receiver /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.CAT, receiver /*FIXME: receiver argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		
		String filename = IO.readString(Message.fileRequest());
		
		try {
			IO.println(_receiver.showFileData(filename));
		} catch (EntryUnknownCoreException e) { throw new EntryUnknownException(filename); } 
		  catch (IsNotFileCoreException e) { throw new IsNotFileException(filename); }
		
		
		
	}
}
