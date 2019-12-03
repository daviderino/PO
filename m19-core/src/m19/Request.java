package m19;

import java.io.Serializable;

public class Request implements Serializable {
	private int _returnDate;
	private boolean _onTime;
	private User _user;
	private Work _work;

	public Request(User user, Work work, int returnDate) {
		_user = user;
		_work = work;
		_returnDate = returnDate;
	}

	public int getReturnDate() {
		return _returnDate;
	}

	public boolean getOnTime() {
		return _onTime;
	}

	public Work getWork() {
		return _work;
	}

	public void setOnTime(boolean onTime) {
		_onTime = onTime;
	}
}
