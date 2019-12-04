package m19;

public class ObserverReturn implements Observer {
	private Work _work;
	private User _user;

	public ObserverReturn(Work work, User user){
		_work = work;
		_user = user;
	}

	@Override
	public void update() {
		_user.addNotification(_work, "ENTREGA");
	}
}