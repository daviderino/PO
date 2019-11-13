package m19;

public class Normal extends Behaviour {
	/**
	 * @param user
	 */
	public Normal(User user) {
		super(user);
		_maxRequests = 3;
	}

	/**
	 *
	 */
	@Override
	public void update() {
		// if rules, change state
	}

	@Override
	public String toString() {
		return "NORMAL";
	}
}
