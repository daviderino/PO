package m19;

public class SearchByTitle implements Search {
	@Override
	public boolean explore(Work work, String term) {
		return work.getTitle().toLowerCase().contains(term.toLowerCase());
	}
}
