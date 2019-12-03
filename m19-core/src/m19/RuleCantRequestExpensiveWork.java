package m19;

public class RuleCantRequestExpensiveWork extends Rule {
	public RuleCantRequestExpensiveWork(User user, Work work) {
		super(user, work);
	}

	@Override
	public boolean validate() {
		return getWork().getPrice() <= 25 || (getUser().getBehaviour() instanceof Compliant);
	}
}
