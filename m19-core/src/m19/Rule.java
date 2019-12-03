package m19;

import m19.exceptions.RuleDeclinedException;

public abstract class Rule {
	private User _user;
	private Work _work;

	public Rule(User user, Work work) {
		_user = user;
		_work = work;
	}

	protected User getUser() {
		return _user;
	}

	protected Work getWork() {
		return _work;
	}

	public abstract boolean validate() throws RuleDeclinedException;
}
