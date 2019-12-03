package m19;

import m19.exceptions.RuleDeclinedException;

import java.util.ArrayList;
import java.util.List;

public class RuleSet extends Rule {
	private List<Rule> _rules;

	public RuleSet(User user, Work work) {
		super(user, work);
		_rules = new ArrayList<Rule>();
	}

	void addRule(Rule rule) {
		_rules.add(rule);
	}

	@Override
	public boolean validate() throws RuleDeclinedException {
		boolean valid = true;

		for(Rule rule: _rules) {
			if(!rule.validate()) {

				valid = false;
				break;
			}
		}
		return valid;
	}
}
