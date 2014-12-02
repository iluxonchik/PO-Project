/** @version $Id: ShowWorkingDirectory.java,v 1.5 2014/12/01 07:54:05 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;

import poof.core.FileSystemManager;

/**
 * §2.2.7.
 */
public class ShowWorkingDirectory extends Command<FileSystemManager> {
	/**
	 * @param receiver
	 */
	public ShowWorkingDirectory(FileSystemManager receiver) {
		super(MenuEntry.PWD, receiver);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() {
		IO.println(_receiver.printWorkingDirectory());
	}

}
