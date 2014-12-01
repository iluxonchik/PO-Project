/** @version $Id: AppendDataToFile.java,v 1.3 2014/11/17 20:35:10 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;





import poof.core.AccessDeniedCoreException;
import poof.core.EntryUnknownCoreException;
import poof.core.FileSystemManager;
import poof.core.IsNotFileCoreException;
import poof.textui.AccessDeniedException;
import poof.textui.EntryUnknownException;
import poof.textui.IsNotFileException;
import poof.textui.shell.MenuEntry;
import poof.textui.shell.Message;

/**
 * §2.2.8.
 */
public class AppendDataToFile extends Command<FileSystemManager> {
	/**
	 * @param receiver
	 */
	public AppendDataToFile(FileSystemManager reciever) {
		super(MenuEntry.APPEND, reciever);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		
		// request user for file name and file contents
		String fileName = IO.readString(Message.fileRequest());
		String content = IO.readString(Message.textRequest());
		
		try {
			_receiver.appendDataToFile(fileName, content);
		} catch (EntryUnknownCoreException e) {
			throw new EntryUnknownException(fileName);
		} catch (IsNotFileCoreException e) {
			throw new IsNotFileException(fileName);
		} catch (AccessDeniedCoreException e) {
			throw new AccessDeniedException(fileName);
		}
	}

}
