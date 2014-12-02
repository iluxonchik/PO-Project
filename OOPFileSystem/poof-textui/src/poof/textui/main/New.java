/** @version $Id: New.java,v 1.5 2014/12/01 07:54:04 ist178134 Exp $ */
package poof.textui.main;

import static ist.po.ui.Dialog.IO;

import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import poof.textui.main.MenuEntry;
import poof.textui.main.Message;
import poof.core.FileSystem;
import poof.core.FileSystemManager;

/**
 * Open a new file.
 */
public class New extends Command<FileSystemManager> {

	/**
	 * @param receiver
	 */
	public New(FileSystemManager receiver) {
		super(MenuEntry.NEW, receiver);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		
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
