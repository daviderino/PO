package m19;

import java.io.Serializable;

public abstract class Behaviour implements Serializable {
	private User _user;
	private int _maxRequests;
	private int _goodStreak;
	private int _badStreak;

	/**
	 * @param user
	 * @param maxRequests
	 */
	public Behaviour(User user, int maxRequests, int goodStreak, int badStreak) {
		_user = user;
		_maxRequests = maxRequests;
		_goodStreak = goodStreak;
		_badStreak = badStreak;
	}

	public User getUser() {
		return _user;
	}

	public int getMaxRequests() {
		return _maxRequests;
	}

	public int getGoodStreak() {
		return _goodStreak;
	}

	public int getBadStreak() {
		return _badStreak;
	}

	public void incrementGoodStreak() {
		_badStreak = 0;
		_goodStreak++;
	}

	public void incrementBadStreak() {
		_goodStreak = 0;
		_badStreak++;
	}

	public abstract void behavedProperly();
	public abstract void behavedPoorly();
}
