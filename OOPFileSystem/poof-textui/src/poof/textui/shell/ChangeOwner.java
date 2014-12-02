/** @version $Id: ChangeOwner.java,v 1.4 2014/12/01 07:54:05 ist178134 Exp $ */
package poof.textui.shell;

import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import poof.core.FileSystemManager;

/**
 * §2.2.11.
 */
public class ChangeOwner extends Command<FileSystemManager>{
	/**
	 * @param receiver
	 */
	public ChangeOwner(FileSystemManager reciever) {
		super(MenuEntry.CHOWN, reciever);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		
	}

}
