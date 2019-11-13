package m19;

public class Compliant extends Behaviour {
	/**
	 * @param user
	 */
	public Compliant(User user) {
		super(user);
		_maxRequests = 5;
	}

	@Override
	public void update() {
		// Same thing
	}

	/**
	 * @return
	 */
	@Override
	public String toString() {
		return "CUMPRIDOR";
	}
}
