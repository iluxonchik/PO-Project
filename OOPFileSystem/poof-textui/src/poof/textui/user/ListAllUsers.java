/** @version $Id: ListAllUsers.java,v 1.4 2014/11/13 06:19:07 ist178134 Exp $ */
package poof.textui.user;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;
import java.util.Collection;

import poof.core.FileSystemManager;
import poof.core.User;

/**
 * §2.3.2.
 */
public class ListAllUsers extends Command<FileSystemManager> {
	/**
	 * @param receiver
	 */
	public ListAllUsers(FileSystemManager receiver) {
		super(MenuEntry.LIST_USERS, receiver);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException  {

		Collection<User> sortedUsers = _receiver.listUsers();
		
		for (User user : sortedUsers) 
			IO.println(user.toString());
	}
}
