package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;

public class RuleUserNotSuspended extends Rule implements Serializable {

	@Override
	public void validate(Work work, User user) throws RuleDeclinedException {
		if(!user.getIsActive()) {
			throw new RuleDeclinedException(2);
		}
	}
}
