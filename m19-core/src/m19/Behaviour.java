package m19;

import java.io.Serializable;

public abstract class Behaviour implements Serializable {
	protected User _user;
	protected int _maxRequests;

	/**
	 * @param user
	 */
	public Behaviour(User user) {
		_user = user;
	}

	public abstract  void update();
}