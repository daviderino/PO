package m19;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
	private List<Observer> _observers = new ArrayList<Observer>();

	public void attachObserver(Observer obs) {
		_observers.add(obs);
	}
	public void detachObserver(Observer obs) {
		_observers.remove(obs);
	}
	public abstract void notifyObservers(String type);

	public List<Observer> getObservers() {
		return _observers;
	}
}
