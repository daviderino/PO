package m19;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Behaviour implements Serializable {
	private User _user;
	private int _maxRequests;
	private int _goodStreak;
	private int _badStreak;
	private boolean _canRequestExpensivewWorks;
	private int[] _requestDays;

	/**
	 * @param user
	 * @param maxRequests
	 */
	public Behaviour(User user, int maxRequests, int goodStreak, int badStreak, boolean expensiveWorks, int[] days) {
		_user = user;
		_maxRequests = maxRequests;
		_goodStreak = goodStreak;
		_badStreak = badStreak;
		_canRequestExpensivewWorks = expensiveWorks;
		_requestDays = days;
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

	public boolean getCanRequestExpensiveWorks() {
		return _canRequestExpensivewWorks;
	}

	public int getRequestDay(int i) {
		if(i >= 0 && i < 3) {
			return _requestDays[i];
		}
		throw new ArrayIndexOutOfBoundsException();
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
