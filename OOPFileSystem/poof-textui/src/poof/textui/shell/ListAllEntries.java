/** @version $Id: ListAllEntries.java,v 1.2 2014/11/08 20:24:14 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;

import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;

// FIXME: import project-specific classes
import poof.core.FileSystemManager;
/**
 * §2.2.1.
 */
public class ListAllEntries extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {
	/**
	 * @param receiver
	 */
	public ListAllEntries(FileSystemManager reciever /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.LS, reciever /*FIXME: receiver argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		//FIXME: implement command
	}

}
