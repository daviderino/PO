package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;

public class RuleUserNotSuspended extends Rule implements Serializable {
	public RuleUserNotSuspended(User user, Work work) {
		super(user, work);
	}

	@Override
	public boolean validate() throws RuleDeclinedException {
		if(getUser().getIsActive()) {
			return true;
		}
		else {
			throw new RuleDeclinedException(2);
		}
	}
}
