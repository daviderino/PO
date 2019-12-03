package m19.app.works;

import java.util.List;

import m19.LibraryManager;
import m19.Work;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;

/**
 * 4.3.3. Perform search according to miscellaneous criteria.
 */
public class DoPerformSearch extends Command<LibraryManager> {

	Input<String> _searchTerm;

	/**
	 * @param m
	 */
	public DoPerformSearch(LibraryManager m) {
		super(Label.PERFORM_SEARCH, m);
		_searchTerm = _form.addStringInput(Message.requestSearchTerm());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		_form.parse();

		List<Work> foundWorks =  _receiver.searchWorks(_searchTerm.value());
		for(Work work: foundWorks){
			_display.addLine(work.toString());
		}
		_display.display();
	}
}
