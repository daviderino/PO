package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RuleSet extends Rule implements Serializable {
	private List<Rule> _rules;

	public RuleSet() {
		super();
		_rules = new ArrayList<Rule>();
		_rules.add(new RuleCantHaveNRequests());
		_rules.add(new RuleCantRequestExpensiveWork());
		_rules.add(new RuleCantRequestReference());
		_rules.add(new RuleCantRequestTwice());
		_rules.add(new RuleNoCopiesAvailable());
		_rules.add(new RuleUserNotSuspended());
	}


	@Override
	public void validate(Work work, User user) throws RuleDeclinedException {
		for(Rule rule: _rules) {
			rule.validate(work, user);
		}

	}
}
