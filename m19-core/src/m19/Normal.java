package m19;

import java.io.Serializable;

public class Normal extends Behaviour implements Serializable {
	/**
	 * @param user
	 */

    public Normal(User user, int goodStreak, int badStreak) {
        super(user, 3, goodStreak, badStreak);
	}

	@Override
	public void behavedProperly() {
		incrementGoodStreak();
		if(getGoodStreak()  == 5) {
			getUser().setBehaviour(new Compliant(getUser()));
		}
	}

	@Override
	public void behavedPoorly() {
		incrementBadStreak();
		if(getBadStreak()  == 3) {
			getUser().setBehaviour(new NonCompliant(getUser()));
		}
	}

	@Override
	public String toString() {
		return "NORMAL";
	}
}
