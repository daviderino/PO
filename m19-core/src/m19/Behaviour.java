package m19;

public abstract class Behaviour {
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