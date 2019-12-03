package m19.exceptions;

public class RuleDeclinedException extends Exception {
	private int _id;

	public RuleDeclinedException(int id) {
		_id = id;
	}

	public int getFailedRuledId() {
		return _id;
	}
}
