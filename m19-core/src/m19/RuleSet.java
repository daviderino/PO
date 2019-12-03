package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RuleSet extends Rule implements Serializable {
	private List<Rule> _rules;

	public RuleSet(User user, Work work) {
		super(user, work);
		_rules = new ArrayList<Rule>();
	}

	void addRule(Rule rule) {
		_rules.add(rule);
	}

	@Override
	public void validate() throws RuleDeclinedException {
		for(Rule rule: _rules) {
			rule.validate();
		}

	}
}
