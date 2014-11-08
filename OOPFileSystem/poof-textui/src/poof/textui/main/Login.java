/** @version $Id: Login.java,v 1.1 2014/10/01 22:45:52 david Exp $ */
package poof.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;

// FIXME: import project-specific classes
import poof.core.FileSystemManager;
/**
 * §2.1.2.
 */
public class Login extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {

	/**
	 * @param receiver
	 */
	public Login(FileSystemManager reciever /*FIXME: add receiver declaration: type must agree with the above*/) {
		
		super(MenuEntry.LOGIN, reciever /*FIXME: receiver argument*/, new ValidityPredicate<FileSystemManager>(reciever) {

			@Override
			public boolean isValid() {
				// TODO Auto-generated method stub
				return false;
			}
			
		}/*FIXME: validity argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		//FIXME: implement command
		
		/* TOFIX:
		* _validityPredicate = _receiver.getLoginValidityPrediacate(userNameHere);
		* --> Does it even make sense?? Encapsulation broken...
		*/
		
		if (this.isValid()){
			// The command is valid for the given reciever, execute
		}
		else {
			// Command not valid for the given reciever
		}
	}
}
