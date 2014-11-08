/** @version $Id: CreateFile.java,v 1.1 2014/10/01 22:45:54 david Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;

// FIXME: import project-specific classes
import poof.core.FileSystemManager;

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
		//FIXME: implement command
	}

}
