package m19;

public class RuleCantRequestReference extends Rule {
	public RuleCantRequestReference(User user, Work work) {
		super(user, work);
	}

	@Override
	public boolean validate() {
		return getWork().getCategory().equals(Category.REFERENCE);
	}
}
