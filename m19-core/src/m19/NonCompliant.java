package m19;

import java.io.Serializable;

public class NonCompliant extends Behaviour implements Serializable {
	/**
	 * @param user
	 */
	public NonCompliant(User user) {
		super(user);
		_maxRequests = 1;
	}

	/**
	 *
	 */
	@Override
	public void update() {
		// Same thing again
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "FALTOSO";
	}
}