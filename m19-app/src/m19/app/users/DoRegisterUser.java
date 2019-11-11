package m19.app.users;

// FIXME import core concepts
// FIXME import ui concepts

import m19.LibraryManager;
import m19.User;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * 4.2.1. Register new user.
 */
public class DoRegisterUser extends Command<LibraryManager> {

	private Input<String> _name;
	private Input<String> _email;

	/**
	 * @param receiver
	 */
	public DoRegisterUser(LibraryManager receiver) {
		super(Label.REGISTER_USER, receiver);
		_name = _form.addStringInput(Message.requestUserName());
		_email = _form.addStringInput(Message.requestUserEMail());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		int id =  _receiver.createUser(_name.value(), _email.value());
		_display.popup(Message.userRegistrationSuccessful(id));
	}

}
