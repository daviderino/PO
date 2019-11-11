package m19.app.users;

// FIXME import core concepts
// FIXME import ui concepts

import m19.LibraryManager;
import m19.User;
import m19.app.exceptions.NoSuchUserException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * 4.2.2. Show specific user.
 */
public class DoShowUser extends Command<LibraryManager> {

  Input<Integer> _id;

  /**
   * @param receiver
   */
  public DoShowUser(LibraryManager receiver) {
    super(Label.SHOW_USER, receiver);
    _id = _form.addIntegerInput(Message.requestUserId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    /*User user = _receiver.getUser(_id.value());
    if (user != null) {
      _display.popup(user.toString());
    }else{
     throw new m19.app.exceptions.NoSuchUserException(_id.value());
    }*/
  }

}
