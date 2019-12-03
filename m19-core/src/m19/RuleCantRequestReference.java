package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;

public class RuleCantRequestReference extends Rule implements Serializable {
	public RuleCantRequestReference(User user, Work work) {
		super(user, work);
	}

	@Override
	public void validate() throws RuleDeclinedException {
		if(getWork().isRequestable()) {
		}
		else {
			throw new RuleDeclinedException(5);
		}
	}
}
