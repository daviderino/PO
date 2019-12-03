package m19;

public class RuleCantHaveNRequests extends Rule {
	public RuleCantHaveNRequests(User user, Work work) {
		super(user, work);
	}

	@Override
	public boolean validate() {
		return getUser().getRequests().size() < getUser().getMaxRequests();
	}
}
