package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;

public class RuleCantRequestTwice extends Rule implements Serializable {
	public RuleCantRequestTwice(User user, Work work) {
		super(user, work);
	}

	@Override
	public boolean validate() throws RuleDeclinedException {
		for(Request request: getUser().getRequests()) {
			if(request.getWork() == getWork()) {
				throw new RuleDeclinedException(1);
			}
		}
		return true;
	}
}
