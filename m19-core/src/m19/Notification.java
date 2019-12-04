package m19;

import java.io.Serializable;

public class Notification implements Serializable{
    private Work _work;
    private String _type;

    public Notification(Work work, String type){
        _type = type;
        _work = work;
    }

    @Override
    public String toString(){
        return _type + ": " + _work;
    }
}