package m19.app.users;

// FIXME import core concepts
// FIXME import ui concepts

import m19.LibraryManager;
import m19.app.exceptions.NoSuchUserException;
import m19.exceptions.GetUserFailedException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

/**
 * 4.2.3. Show notifications of a specific user.
 */
public class DoShowUserNotifications extends Command<LibraryManager> {

  Input<Integer> _id;

  /**
   * @param receiver
   */
  public DoShowUserNotifications(LibraryManager receiver) {
    super(Label.SHOW_USER_NOTIFICATIONS, receiver);
    _id = _form.addIntegerInput(Message.requestUserId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    try{  
      _receiver.getUserNotifications(_id.value());
    }catch(GetUserFailedException ex){
      throw new NoSuchUserException(_id.value());
    }
  }

}
