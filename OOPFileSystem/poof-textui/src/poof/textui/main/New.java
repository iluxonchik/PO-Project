/** @version $Id: New.java,v 1.4 2014/11/13 07:30:03 ist178134 Exp $ */
package poof.textui.main;

import static ist.po.ui.Dialog.IO;

import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;

// FIXME: import project-specific classes
import poof.textui.main.MenuEntry;
import poof.textui.main.Message;
import poof.core.FileSystem;
import poof.core.FileSystemManager;

/**
 * Open a new file.
 */
public class New extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {

	/**
	 * @param receiver
	 */
	public New(FileSystemManager receiver /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.NEW, receiver /*FIXME: receiver argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		//FIXME: implement command
		if(_receiver.needsSaving()) {
			
			if(IO.readBoolean(Message.saveBeforeExit())) {
				// if user wants to save the currently active FileSystem
				
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
		_receiver.createFileSystem();
	}

}
