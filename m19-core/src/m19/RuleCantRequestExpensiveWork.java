package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;

public class RuleCantRequestExpensiveWork extends Rule implements Serializable {

	@Override
	public void validate(Work work, User user) throws RuleDeclinedException {
		if(work.getPrice() > 25 ) {
			if(!user.canRequestExpensiveWorks())  {
				throw new RuleDeclinedException(6);
			}
		}
	}
}
