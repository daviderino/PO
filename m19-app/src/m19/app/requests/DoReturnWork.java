package m19.app.requests;

import m19.LibraryManager;
import m19.app.exceptions.NoSuchUserException;
import m19.app.exceptions.NoSuchWorkException;
import m19.app.exceptions.UserIsActiveException;
import m19.app.exceptions.WorkNotBorrowedByUserException;
import m19.exceptions.ActiveUserException;
import m19.exceptions.GetUserFailedException;
import m19.exceptions.GetWorkFailedException;
import m19.exceptions.RequestNonExistentException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Command<LibraryManager> {

	Input<Integer> _userId;
	Input<Integer> _workId;

	/**
	 * @param receiver
	 */
	public DoReturnWork(LibraryManager receiver) {
		super(Label.RETURN_WORK, receiver);
		_userId = _form.addIntegerInput(Message.requestUserId());
		_workId = _form.addIntegerInput(Message.requestWorkId());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		try {
			_form.parse();
			int fine = _receiver.returnWork(_userId.value(), _workId.value());

			if(fine != 0) {
				_display.popup(Message.showFine(_userId.value(), fine));
				_form.clear();
				Input<Boolean> pay = _form.addBooleanInput(Message.requestFinePaymentChoice());
				_form.parse();

				if(pay.value()) {
					_receiver.payFine(_userId.value(), fine);
				}

				_form.clear();
				_userId = _form.addIntegerInput(Message.requestUserId());
				_workId = _form.addIntegerInput(Message.requestWorkId());
			}
		}
		catch (GetWorkFailedException e) {
			throw new NoSuchWorkException(_workId.value());
		}
		catch (GetUserFailedException e) {
			throw new NoSuchUserException(_userId.value());
		}
		catch (RequestNonExistentException e) {
			throw new WorkNotBorrowedByUserException(_workId.value(), _userId.value());
		}
		catch (ActiveUserException e) {
			throw new UserIsActiveException(_userId.value());
		}
	}
}
