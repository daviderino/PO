package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;

public class RuleCantHaveNRequests extends Rule implements Serializable {
	public RuleCantHaveNRequests(User user, Work work) {
		super(user, work);
	}

	@Override
	public void validate() throws RuleDeclinedException {
		if(getUser().getRequests().size() < getUser().getMaxRequests()) {
		}
		else {
			throw new RuleDeclinedException(4);
		}
	}
}
