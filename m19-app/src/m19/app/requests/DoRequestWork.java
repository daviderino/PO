package m19.app.requests;

import com.sun.org.apache.xpath.internal.operations.Bool;
import m19.LibraryManager;
import m19.app.exceptions.NoSuchUserException;
import m19.app.exceptions.NoSuchWorkException;
import m19.app.exceptions.RuleFailedException;
import m19.exceptions.GetUserFailedException;
import m19.exceptions.GetWorkFailedException;
import m19.exceptions.RuleDeclinedException;
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
			int returnDay = _receiver.requestWork(_userId.value(), _workId.value());
			_display.popup(Message.workReturnDay(_workId.value(), returnDay));
		}
		catch(RuleDeclinedException ex) {
			if(ex.getFailedRuledId() == 3) {
				_form.clear();
				Input<Boolean> notify = _form.addBooleanInput(Message.requestReturnNotificationPreference());
				_form.parse();
				_form.clear();
				_userId = _form.addIntegerInput(Message.requestUserId());
				_workId = _form.addIntegerInput(Message.requestWorkId());

				if(notify.value()) {
					_receiver.addReturnObserver(_userId.value(), _workId.value());
				}
			}
			else {
				throw new RuleFailedException(_userId.value(), _workId.value(), ex.getFailedRuledId());
			}
		}
		catch(GetUserFailedException ex) {
			throw new NoSuchUserException(_userId.value());
		}
		catch(GetWorkFailedException ex) {
			throw new NoSuchWorkException(_workId.value());
		}
	}

}
