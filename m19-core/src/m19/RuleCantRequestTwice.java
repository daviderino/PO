package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;

public class RuleCantRequestTwice extends Rule implements Serializable {
	
	@Override
	public void validate(Work work, User user) throws RuleDeclinedException {
		for(Request request: user.getRequests()) {
			if(request.getWorkId() == work.getId()) {
				throw new RuleDeclinedException(1);
			}
		}
	}
}
