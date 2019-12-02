package m19;

import java.io.Serializable;

public class NonCompliant extends Behaviour implements Serializable {
	/**
	 * @param user
	 */
	public NonCompliant(User user) {
		super(user, 1);
	}

	@Override
	public void returnOnTime() {

	}

	@Override
	public void returnLate() {

	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "FALTOSO";
	}
}