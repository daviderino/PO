package m19;

public class RuleUserNotSuspended extends Rule {
	public RuleUserNotSuspended(User user, Work work) {
		super(user, work);
	}

	@Override
	public boolean validate() {
		return getUser().getIsActive();
	}
}
