package m19;

public class RuleNoCopiesAvailable extends Rule {
	public RuleNoCopiesAvailable(User user, Work work) {
		super(user, work);
	}

	@Override
	public boolean validate()  {
		return getWork().getAvailableCopies() > 0;
	}
}
