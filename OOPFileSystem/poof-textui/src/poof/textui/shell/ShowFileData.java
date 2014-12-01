/** @version $Id: ShowFileData.java,v 1.4 2014/11/30 22:03:02 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import poof.core.EntryUnknownCoreException;
import poof.core.FileSystemManager;
import poof.core.IsNotFileCoreException;
import poof.textui.EntryUnknownException;
import poof.textui.IsNotFileException;
import poof.textui.shell.MenuEntry;
import poof.textui.shell.Message;

/**
 * §2.2.9.
 */
public class ShowFileData extends Command<FileSystemManager> {
	/**
	 * @param receiver
	 */
	public ShowFileData(FileSystemManager receiver) {
		super(MenuEntry.CAT, receiver);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		
		String filename = IO.readString(Message.fileRequest());
		
		try {
			String fileContent = _receiver.showFileData(filename);
			
			if (fileContent != null)
				IO.println(fileContent);
			
		} catch (EntryUnknownCoreException e) { throw new EntryUnknownException(filename); } 
		  catch (IsNotFileCoreException e) { throw new IsNotFileException(filename); }
		
		
		
	}
}
