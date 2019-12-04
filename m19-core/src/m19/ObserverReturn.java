package m19;


public class ObserverReturn extends Observer{

    public ObserverReturn(Work work, User user){
        super(work, user);
    }

    @Override
    public void update(){
        getUser().addNotification(getWork(), "ENTREGA");
    }
}