/** @version $Id: ListAllEntries.java,v 1.3 2014/11/12 08:56:37 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;
import java.util.Collection;

// FIXME: import project-specific classes
import poof.core.FileSystemManager;
import poof.core.FileSystemEntitiy;

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
		Collection<FileSystemEntitiy> children = _receiver.listAllEntries();
		
		for(FileSystemEntitiy entry : children)
			IO.println(entry.toString());
	}

}
