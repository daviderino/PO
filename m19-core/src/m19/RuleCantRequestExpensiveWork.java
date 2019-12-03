package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;

public class RuleCantRequestExpensiveWork extends Rule implements Serializable {
	public RuleCantRequestExpensiveWork(User user, Work work) {
		super(user, work);
	}

	@Override
	public void validate() throws RuleDeclinedException {
		if(getWork().getPrice() <= 25 || (getUser().canRequestExpensiveWorks())) {
		}
		else {
			throw new RuleDeclinedException(6);
		}
	}
}
