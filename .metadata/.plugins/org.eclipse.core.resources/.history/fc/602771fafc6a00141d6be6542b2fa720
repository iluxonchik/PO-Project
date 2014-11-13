/** @version $Id: ListAllUsers.java,v 1.3 2014/11/11 07:46:28 ist178134 Exp $ */
package poof.textui.user;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;
import java.util.Collection;



// FIXME: import project-specific classes
import poof.core.FileSystemManager;
import poof.core.User;

/**
 * §2.3.2.
 */
public class ListAllUsers extends Command<FileSystemManager> /* FIXME: select core type for receiver */ {
	/**
	 * @param receiver
	 */
	public ListAllUsers(FileSystemManager receiver /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.LIST_USERS, receiver /*FIXME: receiver argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException  {
		//FIXME: implement command
		Collection<User> sortedUsers = _receiver.listUsers();
		
		for (User user : sortedUsers) 
			IO.println(user.toString());
	}
}
