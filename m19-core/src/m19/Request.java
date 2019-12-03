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
		_onTime = true;
	}

	public int getUserId() {
		return _user.getId();
	}

	public int getWorkId() {
		return _work.getId();
	}

	public int getReturnDate() {
		return _returnDate;
	}

	public boolean getOnTime() {
		return _onTime;
	}

	public void setOnTime(boolean onTime) {
		_onTime = onTime;
	}
}
