package m19;

import java.io.Serializable;

public abstract class Behaviour implements Serializable {
	private User _user;
	private int _maxRequests;

	/**
	 * @param user
	 * @param maxRequests
	 */
	public Behaviour(User user, int maxRequests) {
		_user = user;
		_maxRequests = maxRequests;
	}

	public abstract void behavedProperly();
	public abstract void behavedPoorly();
}