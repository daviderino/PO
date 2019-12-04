package m19;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject implements Serializable {
	private List<Observer> _requestObservers = new ArrayList<Observer>();
	private List<Observer> _returnObservers = new ArrayList<Observer>();
	
	public List<Observer> getRequestObservers(){
		return _requestObservers;
	}

	public List<Observer> getReturnObservers(){
		return _returnObservers;
	}

	public void registerRequestObserver(Observer o) { 
        _requestObservers.add(o); 
    }

    public void registerReturnObserver(Observer o) { 
        _returnObservers.add(o); 
    }

	public abstract void notifyRequestObservers();
	
    public abstract void notifyReturnObservers();
}
