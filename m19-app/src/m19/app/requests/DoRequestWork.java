package m19.app.requests;

import m19.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * 4.4.1. Request work.
 */
public class DoRequestWork extends Command<LibraryManager> {

	Input<Integer> _userId;
	Input<Integer> _workId;

	/**
	 * @param receiver
	 */
	public DoRequestWork(LibraryManager receiver) {
		super(Label.REQUEST_WORK, receiver);
		_userId = _form.addIntegerInput(Message.requestUserId());
		_workId = _form.addIntegerInput(Message.requestWorkId());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		try {
			_form.parse();
		}
		catch()
	}

}
