package m19;

public class ObserverRequest extends Observer{

    public ObserverRequest(Work work, User user){
        super(work, user);
    }

    @Override
    public void update(){
        getUser().addNotification(getWork(), "REQUISIÇÃO");
    }
}