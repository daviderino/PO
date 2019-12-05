package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;

public class RuleCantHaveNRequests extends Rule implements Serializable {

	@Override
	public void validate(Work work, User user) throws RuleDeclinedException {
		if(user.getRequests().size() == user.getMaxRequests()) {
			throw new RuleDeclinedException(4);
		}
	}
}
