package m19;

public class SearchByCreator implements Search {
	@Override
	public boolean explore(Work work, String term) {
		return work.getCreator().toLowerCase().contains(term.toLowerCase());
	}
}
