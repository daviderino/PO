package m19;

import java.io.Serializable;

public class NonCompliant extends Behaviour implements Serializable {
	/**
	 * @param user
	 */
	public NonCompliant(User user) {
		super(user, 1, 0, 3, false, new int[] {2, 2, 2});
	}

	@Override
	public void behavedProperly() {
		incrementGoodStreak();
		if(getGoodStreak() == 3) {
			getUser().setBehaviour(new Normal(getUser(), 3, 0));
		}
	}

	@Override
	public void behavedPoorly() {
		// do nothing
	}

	@Override
	public String toString() {
		return "FALTOSO";
	}
}
