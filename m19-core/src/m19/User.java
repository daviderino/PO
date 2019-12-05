package m19;

import m19.exceptions.ActiveUserException;
import m19.exceptions.RequestNonExistentException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that represents the concept of user
 */
public class User implements Serializable, Comparable<User>, Observer {
	private int _id;
	private int _totalFines;
	private boolean _isActive;
	private String _name;
	private String _email;
	private Behaviour _behaviour;
	private Map<Integer, Request> _requests = new HashMap<Integer, Request>();
	private List<Notification> _notifications = new ArrayList<Notification>();


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
		_behaviour = new Normal(this, 0, 0);
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
	 * @return the sum of the fines accumulated by the user
	 */
	public int getTotalFines() {
		return _totalFines;
	}

	/**
	 * @return the requests list
	 */
	public Map<Integer, Request> getRequests() {
		return _requests;
	}

	public Request getRequest(int workId) throws RequestNonExistentException{
		Request request = _requests.get(workId);
		if(request == null){
			throw new RequestNonExistentException();
		}
		return request;
	}

	public boolean hasRequest(int workId){
		return (_requests.get(workId) != null);
	}

	/**
	 * @param request to add
	 */
	public void addRequest(Request request, int workId) {
		_requests.put(workId, request);
	}

	/**
	 * @param workId of the request to remove
	 */
	public void removeRequest(int workId) {
		_requests.remove(workId);
	}

	/**
	 * @return true if the user is active, false otherwise
	 */
	public boolean getIsActive() {
		return _isActive;
	}

	public String behaviour() {
		return _behaviour.toString();
	}

	/**
	 * @return the corresponding string of the user's state
	 */
	public String isActive() {
		if(_isActive) {
			return "ACTIVO";
		}
		else {
			return "SUSPENSO - EUR " + getTotalFines();
		}
	}

	/**
	 * @return the number of requests a user can make
	 */
	public int getMaxRequests() {
		return _behaviour.getMaxRequests();
	}

	/**
	 * Sets isActive of the user to state. True means user is active, false means user is inactive
	 * @param state to set the user to
	 */
	public void setIsActive(boolean state) {
		_isActive = state;
	}

	/**
	 * @param behaviour state to change to
	 */
	public void setBehaviour(Behaviour behaviour) {
		_behaviour = behaviour;
	}

	/**
	 * 	Call state method
	 */
	public void behavedProperly() {
		_behaviour.behavedProperly();
	}

	/**
	 * @return if the user can request expensive works
	 */
	public boolean canRequestExpensiveWorks() {
		return _behaviour.getCanRequestExpensiveWorks();
	}

	public int getRequestDay(int i) {
		return _behaviour.getRequestDay(i);
	}

	/**
	 * Call state method
	 */
	public void behavedPoorly() {
		_behaviour.behavedPoorly();
	}

	/**
	 * @param fine to add
	 */
	public void setFine(int fine) {
		if(_isActive) {
			_isActive = false;
		}
		_totalFines = fine;
	}

	/**
	 * Pays a fine
	 */
	public void payFine() throws ActiveUserException {
		if(!_isActive && _totalFines >= 5) {
			_totalFines = 0;
			boolean onTime = true;

			for(Request request: _requests.values()) {
				if(!request.getOnTime()) {
					onTime = false;
					break;
				}
			}
			if(onTime) {
				_isActive = true;
			}
		}
		else {
			throw new ActiveUserException();
		}
	}

	public void update(Work work, String type){
		_notifications.add(new Notification(work, type));
	}

	public List<Notification> getNotifications(){
		List<Notification> copy = new ArrayList<Notification>();
		copy.addAll(_notifications);
		_notifications.clear();
		return copy;
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

	@Override
	public String toString() {
		String userString = getId() + " - " + getName() + " - " + getEmail() + " - " + behaviour() + " - " + isActive();
		for(Notification n: _notifications){
			userString += ('\n' + n.toString());
		}
		return userString;
	}
}