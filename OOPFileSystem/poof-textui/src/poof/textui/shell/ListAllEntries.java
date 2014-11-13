/** @version $Id: ListAllEntries.java,v 1.3 2014/11/12 08:56:37 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

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
		Map<String, FileSystemEntitiy> children = _receiver.listAllEntries();
		
		for(Map.Entry<String, FileSystemEntitiy> entry: children.entrySet()) {
			if(entry.getKey() == _receiver.THIS_DIR_NAME)
				// print self reference as "."
				IO.println(entry.getValue().toString(_receiver.THIS_DIR_NAME));
			else if (entry.getKey() == _receiver.PARENT_DIR_NAME)
				// print parent reference as ".."
				IO.println(entry.getValue().toString(_receiver.PARENT_DIR_NAME));
			else
				// print other references with their default name
				IO.println(entry.getValue().toString());
				
		}
			
	}

}
