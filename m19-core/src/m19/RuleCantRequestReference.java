package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;

public class RuleCantRequestReference extends Rule implements Serializable {

	@Override
	public void validate(Work work, User user) throws RuleDeclinedException {
		if(!work.isRequestable()) {
			throw new RuleDeclinedException(5);
		}
	}
}
