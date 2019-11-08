package m19;

import java.io.Serializable;
import m19.Behaviour;

/**
 * Class that represents the concept of user
 */
public class User implements Serializable {
	private int _id;
	private String _name;
	private String _email;
	private boolean _state;
	private int _streak;
	private Behaviour _behaviour;
	private int _totalFines;

	/**
	 *
	 * @param id of the user being created
	 * @param name of the user being created
	 * @param email of the user being created
	 */
	public User(int id, String name, String email) {
		this._id = id;
		this._name = name;
		this._email = email;
		_state = true;
		_streak = 0;
		_behaviour = Behaviour.NORMAL;
		_totalFines = 0;
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
		return _behaviour.name();
	}

	/**
	 * @return the sum of the fines accumulated by the user
	 */
	public int getTotalFines() {
		return _totalFines;
	}

	/**
	 * @return the corresponding string of the user's state
	 */
	public String getState() {
		if(_state) {
			return "ACTIVO";
		}
		else {
			return "SUSPENSO - EUR " + getTotalFines();
		}
	}

	/**
	 * Increments the var containing the number of days the user has abidden by the rules
	 */
	public void incrementStreak() {
		if(_streak < 0) {
			_streak = 0;
			_streak++;
			updateBehaviour();
		}
	}

	/**
	 * Decrements the var containing the number of days the user has abidden by the rules
	 */
	public void decrementStreak() {
		if(_streak > 0) {
			_streak = 0;
			_streak--;
			updateBehaviour();
		}
	}

	/**
	 * Sets the state of the user to state. True means user is active, false means user is inactive
	 * @param state to set the user to
	 */
	public void updateState(boolean state) {
		_state = state;
	}

	/**
	 * Updates users behaviour accordingly
	 */
	private void updateBehaviour() {
		if(_behaviour != Behaviour.COMPLIANT && _streak >= 5) {
			_behaviour = Behaviour.COMPLIANT;
		}
		else if (_behaviour != Behaviour.NONCOMPLIANT && _streak <= -3) {
			_behaviour = Behaviour.NONCOMPLIANT;
		}
		else if(_behaviour == Behaviour.NONCOMPLIANT && _streak >= 3 && _streak < 5) {
			_behaviour = Behaviour.NORMAL;
		}
	}

	public void payFine() {
		// TODO: Implement later. Terceira entrega
	}

	@Override
	public String toString() {
		return getId() + " - " + getName() + " - " + getEmail() + " - " + getBehaviour() + " - " + getState();
	}
}