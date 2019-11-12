package m19.app.works;

import java.util.List;
import java.util.Map;

// FIXME import core concepts
// FIXME import ui concepts

import m19.LibraryManager;
import m19.Work;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;

/**
 * 4.3.2. Display all works.
 */
public class DoDisplayWorks extends Command<LibraryManager> {

  /**
   * @param receiver
   */
  public DoDisplayWorks(LibraryManager receiver) {
    super(Label.SHOW_WORKS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    Map<Integer, Work> works = _receiver.getAllWorks();
    for (Work w : works.values()){
      _display.popup(w.toString());
    }
  }
}
