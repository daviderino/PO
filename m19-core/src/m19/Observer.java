package m19;

import java.io.Serializable;

public abstract class Observer implements Serializable{
    private Work _work;
    private User _user;

    public Observer(Work work, User user){
        _work = work; 
        _user = user;
        _work.registerObserver(this);
    }

    public User getUser(){
        return _user;
    }

    public Work getWork(){
        return _work;
    }

    public void update(){ }
}