/** @version $Id: Open.java,v 1.1 2014/10/01 22:45:53 david Exp $ */
package poof.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;
import ist.po.ui.ValidityPredicate;

import java.io.IOException;

// FIXME: import project-specific classes

/**
 * Open existing file.
 */
public class Open extends Command<FIXME> /* FIXME: select core type for receiver */ {

	/**
	 * @param receiver
	 */
	public Open(FIXME /*FIXME: add receiver declaration: type must agree with the above*/) {
		super(MenuEntry.OPEN, FIXME /*FIXME: receiver argument*/);
	}

	/** @see ist.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, IOException {
		//FIXME: implement command
	}

}
