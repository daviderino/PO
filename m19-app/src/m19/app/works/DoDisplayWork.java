package m19.app.works;

import m19.LibraryManager;
import m19.Work;
import m19.app.exceptions.NoSuchWorkException;
import m19.exceptions.GetWorkFailedException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**import java.util.List;

 * 4.3.1. Display work.
 */
public class DoDisplayWork extends Command<LibraryManager> {
	Input<Integer> _id;

	/**
	 * @param receiver
	 */
	public DoDisplayWork(LibraryManager receiver) {
		super(Label.SHOW_WORK, receiver);
		_id = _form.addIntegerInput(Message.requestWorkId());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		
		try {
			Work work = _receiver.getWork(_id.value());
			_display.popup(work.toString());
		}
        catch(GetWorkFailedException ex) {
			throw new NoSuchWorkException(_id.value());
		}
	}
}
