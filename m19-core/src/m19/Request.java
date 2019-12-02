package m19;

public class Request {
	private int _returnDate;
	private boolean _onTime;

	public Request() {

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
