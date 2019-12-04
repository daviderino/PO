package m19;

public class ObserverRequest implements Observer {
	private Work _work;
	private User _user;

	public ObserverRequest(Work work, User user){
		_work = work;
		_user = user;
	}

	@Override
	public void update() {
		_user.addNotification(_work, "REQUISIÇÃO");
	}
}