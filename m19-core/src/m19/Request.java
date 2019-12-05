package m19;

import java.io.Serializable;

public class Request implements Serializable {
	private int _returnDate;
	private boolean _onTime;

	public Request(int returnDate) {
		_returnDate = returnDate;
		_onTime = true;
	}

	public int getReturnDate() {
		return _returnDate;
	}

	public void setOnTime(boolean onTime) {
		_onTime = onTime;
	}

	public boolean getOnTime() {
		return _onTime;
	}
}
