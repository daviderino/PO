package m19;

import m19.exceptions.RuleDeclinedException;

import java.io.Serializable;

public abstract class Rule implements Serializable {

	public abstract void validate(Work work, User user) throws RuleDeclinedException;
}
