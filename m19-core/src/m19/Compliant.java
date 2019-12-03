package m19;

import java.io.Serializable;

public class Compliant extends Behaviour implements Serializable {
	/**
	 * @param user
	 */
	public Compliant(User user) {
		super(user, 5, 5, 0);
	}

	@Override
	public void behavedProperly() {
		// do nothing
	}

	@Override
	public void behavedPoorly() {
		getUser().setBehaviour(new Normal(getUser(), 0, 1));
	}

	@Override
	public String toString() {
		return "CUMPRIDOR";
	}
}
