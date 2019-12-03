package m19.exceptions;

public class RuleDeclinedException extends Exception {
	private boolean _noCopies = false;

	public RuleDeclinedException(boolean noCopies) {
		_noCopies = noCopies;
	}

	public boolean getNoCopies() {
		return _noCopies;
	}
}
