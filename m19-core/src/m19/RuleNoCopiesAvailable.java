package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;

public class RuleNoCopiesAvailable extends Rule implements Serializable {
	public RuleNoCopiesAvailable(User user, Work work) {
		super(user, work);
	}

	@Override
	public boolean validate() throws RuleDeclinedException {
		if(getWork().getAvailableCopies() > 0) {
			return true;
		}
		else {
			throw new RuleDeclinedException(3);
		}
	}
}
