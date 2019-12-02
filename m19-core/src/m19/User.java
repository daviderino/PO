package m19;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the concept of user
 */
public class User implements Serializable, Comparable<User> {
	private int _id;
	private int _totalFines;
	private boolean _isActive;
	private String _name;
	private String _email;
	private Behaviour _behaviour;
	private List<Request> _requests;

	/**
	 *
	 * @param id of the user being created
	 * @param name of the user being created
	 * @param email of the user being created
	 */
	public User(int id, String name, String email) {
		_id = id;
		_name = name;
		_email = email;
		_totalFines = 0;
		_isActive = true;
		_behaviour = new Normal(this);
		_requests = new ArrayList<Request>();
	}

	/**
	 * @return the id of the user
	 */
	public int getId() {
		return _id;
	}

	/**
	 * @return the name of the user
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @return the email of the user
	 */
	public String getEmail() {
		return _email;
	}

	/**
	 * @return the corresponding string of the user's behaviour
	 */
	public String getBehaviour() {
		return _behaviour.toString();
	}

	/**
	 * @return the sum of the fines accumulated by the user
	 */
	public int getTotalFines() {
		return _totalFines;
	}

	/**
	 * @return the requests list
	 */
	public List<Request> getRequests() {
		return _requests;
	}

	/**
	 * @return the corresponding string of the user's state
	 */
	public String getIsActive() {
		if(_isActive) {
			return "ACTIVO";
		}
		else {
			return "SUSPENSO - EUR " + getTotalFines();
		}
	}

	/**
	 * Sets isActive of the user to state. True means user is active, false means user is inactive
	 * @param state to set the user to
	 */
	public void setIsActive(boolean state) {
		_isActive = state;
	}

	public void behavedProperly() {
		_behaviour.behavedProperly();
	}

	public void behavedPoorly() {
		_behaviour.behavedPoorly();
	}

	public void addFine(int fine) {
		_totalFines += fine;
	}

	/**
	 * Pays a fine
	 */
	public void payFine() {
		// TODO: Implement later. Terceira entrega
	}

	@Override
	public String toString() {
		return getId() + " - " + getName() + " - " + getEmail() + " - " + getBehaviour() + " - " + getIsActive();
	}

	/**
	 * Compares 2 users by name ignoring upper/lower case. Compares by ids if names are equal
	 *
	 * @param user to compare to
	 * @return value corresponding to the comparison
	 */
	@Override
	public int compareTo(User user) {
		int ret = this._name.compareToIgnoreCase(user.getName());

		if(ret == 0) {
			return this._id - user.getId();
		}
		else {
			return ret;
		}
	}
}