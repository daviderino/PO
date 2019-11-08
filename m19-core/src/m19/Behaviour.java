package m19;

public enum Behaviour {
	COMPLIANT(5),
	NORMAL(3),
	NONCOMPLIANT(1);

	private final int _maxRequests;

	Behaviour(int maxRequests) {
		_maxRequests = maxRequests;
	}

	/**
	 * @return number of requests a user with a given behavour can perform
	 */
	public int maxRequests() {
		return _maxRequests;
	}
}
