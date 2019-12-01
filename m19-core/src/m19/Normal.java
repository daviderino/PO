package m19;

import java.io.Serializable;

public class Normal extends Behaviour implements Serializable {
	/**
	 * @param user
	 */
	public Normal(User user) {
		super(user, 3);
	}

	@Override
	public void update() {
		// if rules, change state
	}

	@Override
	public String toString() {
		return "NORMAL";
	}
}
