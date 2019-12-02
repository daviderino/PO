package m19;

import java.io.Serializable;

public class Compliant extends Behaviour implements Serializable {
	/**
	 * @param user
	 */
	public Compliant(User user) {
		super(user, 5);
	}

	@Override
	public void returnOnTime() {

	}

	@Override
	public void returnLate() {

	}

	@Override
	public String toString() {
		return "CUMPRIDOR";
	}
}
