/** @version $Id: Save.java,v 1.2 2014/11/08 20:24:14 ist178134 Exp $ */
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
public class Save extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {
	/**
	 * @param receiver
	 */
	public Save(FileSystemManager receiver /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.SAVE, receiver /*FIXME: receiver argument*/ , new ValidityPredicate<FileSystemManager>(receiver) {

			@Override
			public boolean isValid() {
				// TODO Auto-generated method stub
				return _receiver.getActiveFileSystem() != null;
			}
			
		} /*FIXME: validity argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		//FIXME: implement command

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
