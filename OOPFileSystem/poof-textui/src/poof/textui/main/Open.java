/** @version $Id: Open.java,v 1.4 2014/12/01 07:54:04 ist178134 Exp $ */
package poof.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.FileNotFoundException;
import java.io.IOException;

import poof.core.FileSystemManager;
import poof.core.FileSystem;
import poof.textui.main.MenuEntry;
import poof.textui.main.Message;

/**
 * Open existing file.
 */
public class Open extends Command<FileSystemManager> {

	/**
	 * @param receiver
	 */
	public Open(FileSystemManager receiver) {
		super(MenuEntry.OPEN, receiver);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		
		// first check if activeFileSystem exists and needs saving
		FileSystem activeFileSystem = _receiver.getActiveFileSystem();
		
		if(activeFileSystem != null && _receiver.needsSaving()) {
			
			// prompt the user of he wants to save the current fileSystem
			if(IO.readBoolean(Message.saveBeforeExit())) {
				
				// user wants to save the currently active FileSystem
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
		
		// at this point it's safe to open a new File System
		String fileName = IO.readString(Message.openFile());
		
		try {
			_receiver.loadFileSystem(fileName);
		} catch (FileNotFoundException e) { IO.println(Message.fileNotFound()); }
	}

}
