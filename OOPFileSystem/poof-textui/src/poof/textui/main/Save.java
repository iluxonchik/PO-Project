/** @version $Id: Save.java,v 1.3 2014/11/13 05:52:44 ist178134 Exp $ */
package poof.textui.main;

import static ist.po.ui.Dialog.IO;

import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;

// FIXME: import project-specific classes
import poof.core.FileSystemManager;
import poof.textui.main.MenuEntry;
import poof.textui.main.Message;
import poof.core.FileSystem;
/**
 * Save to file under current name (if unnamed, query for name).
 */
public class Save extends Command<FileSystemManager> {
	/**
	 * @param receiver
	 */
	public Save(FileSystemManager receiver) {
		super(MenuEntry.SAVE, receiver, new ValidityPredicate<FileSystemManager>(receiver) {

			@Override
			public boolean isValid() {
				return _receiver.getActiveFileSystem() != null;
			}
			
		});
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {

		if(_receiver.needsSaving()) {
			
			FileSystem activeFileSystem = _receiver.getActiveFileSystem();
			String name;
			if(activeFileSystem.isAssociated()) {
				// if the activeFileSystem is already associated to a file
				name = activeFileSystem.getName();
			}
			else {
				name = IO.readString(Message.newSaveAs());
			}
			_receiver.saveFileSystem(name);
		}
	}
}
