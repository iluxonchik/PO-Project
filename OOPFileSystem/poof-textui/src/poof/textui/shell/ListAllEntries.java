/** @version $Id: ListAllEntries.java,v 1.8 2014/11/14 00:03:34 ist178134 Exp $ */
package poof.textui.shell;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;
import java.util.Map;

import poof.core.Directory;
import poof.core.FileSystemManager;
import poof.core.FileSystemEntitiy;

/**
 * §2.2.1.
 */
public class ListAllEntries extends Command<FileSystemManager> {
	/**
	 * @param receiver
	 */
	public ListAllEntries(FileSystemManager reciever) {
		super(MenuEntry.LS, reciever);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {

		Map<String, FileSystemEntitiy> children = _receiver.listAllEntries();
		
		for(Map.Entry<String, FileSystemEntitiy> entry: children.entrySet()) {
			if(entry.getKey().equals(Directory.THIS_DIR_NAME))
				// print self reference as "."
				IO.println(entry.getValue().toString(Directory.THIS_DIR_NAME));
			else if (entry.getKey().equals(Directory.PARENT_DIR_NAME))
				// print parent reference as ".."
				IO.println(entry.getValue().toString(Directory.PARENT_DIR_NAME));
			else
				// print other references with their default name
				IO.println(entry.getValue().toString());
		}
			
	}

}
