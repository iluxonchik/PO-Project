/** @version $Id: RemoveEntry.java,v 1.1 2014/10/01 22:45:54 david Exp $ */
package poof.textui.shell;

import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;



// FIXME: import project-specific classes
import poof.core.FileSystemManager;

/**
 * §2.2.3.
 */
public class RemoveEntry extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {
	/**
	 * @param receiver
	 */
	public RemoveEntry(FileSystemManager reciever /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.RM, reciever /*FIXME: receiver argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		//FIXME: implement command
	}
}
