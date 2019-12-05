package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;

public class RuleNoCopiesAvailable extends Rule implements Serializable {

	@Override
	public void validate(Work work, User user) throws RuleDeclinedException {
		if(work.getAvailableCopies() == 0) {
			throw new RuleDeclinedException(3);
		}
	}
}
