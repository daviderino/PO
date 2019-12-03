package m19;

public class RuleCantRequestTwice extends Rule {
	public RuleCantRequestTwice(User user, Work work) {
		super(user, work);
	}

	@Override
	public boolean validate() {
		for(Request request: getUser().getRequests()) {
			if(request.getWork() == getWork()) {
				return false;
			}
		}
		return true;
	}
}
