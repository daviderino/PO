package m19;

import m19.exceptions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {
	private static final long serialVersionUID = 201901101348L;
	private int _userId = 0;
	private int _workId = 0;
	private int _date = 0;
	private boolean _libChanged = true; // variable which controls whether the library has been changed or not

	private Map<Integer, User> _users = new HashMap<Integer, User>();
	private Map<Integer, Work> _works = new TreeMap<Integer, Work>();
	
	/**
	 * Gets value of the date
	 *
	 * @return the date
	 */
	public int getDate() {
		return _date;
    }

	/**
	 * @param changed state of the library to change to
	 */
	public void setLibChanged(boolean changed) {
		_libChanged = changed;
	}

	/**
	 * @return state of the library
	 */
	public boolean getLibChanged() {
		return _libChanged;
    }

	/**
	 * Registers a work from the given fields in the Map
	 *
	 * @param fields with the work's info
	 * @throws BadEntrySpecificationException if a problem occurs when registering (parse gone wrong or null pointer)
	 */
	private void registerWork(String[] fields) throws BadEntrySpecificationException {
		int id = _workId++;

		try {
			int price = Integer.parseInt(fields[3]);
			Category category = Category.valueOf(fields[4]);
			int count = Integer.parseInt(fields[6]);

			if(fields[1] == null || fields[2] == null ||  fields[5] == null) {
				throw new BadEntrySpecificationException(Arrays.toString(fields));
			}

			if(fields[0].equals("BOOK")) {
				if (fields[5].length() != 10) {
					throw new BadEntrySpecificationException(Arrays.toString(fields));
				}
				Book book = new Book(id, fields[1], fields[2], price, category, fields[5], count);
				_works.put(id, book);
			}
			else if(fields[0].equals("DVD")) {
				DVD dvd = new DVD(id, fields[1], fields[2], price, category, fields[5], count);
				_works.put(id, dvd);
			}
		}
		catch (IllegalArgumentException | NullPointerException e) {
			throw new BadEntrySpecificationException(Arrays.toString(fields), e);
		}
	}

	/**
	 * Registers a user from the given fields in the Map
	 *
	 * @param fields with the user's info
	 * @throws BadEntrySpecificationException if one of the fields is null or empty
	 */
	private void registerUser(String[] fields) throws BadEntrySpecificationException {
		int id = _userId++;
		if(fields[1] == null || fields[1].isEmpty() || fields[2] == null || fields[2].isEmpty()) {
			throw new BadEntrySpecificationException(Arrays.toString(fields));
		}
		User user = new User(id, fields[1], fields[2]);
		_users.put(id, user);
	}

	/**
	 * Registers a user or a work from the given fields, depending on the first field
	 * 
	 * @param fields of the entity ro register
	 * @throws BadEntrySpecificationException if the fields can't be assigned to a user or a work
	 */
	private void registerFromFields(String[] fields) throws BadEntrySpecificationException {
		final Pattern patternWork = Pattern.compile("^(BOOK|DVD)");
		final Pattern patternUser = Pattern.compile("^(USER)");

		if(patternWork.matcher(fields[0]).matches()) {
			registerWork(fields);
		}
		else if(patternUser.matcher(fields[0]).matches()) {
			registerUser(fields);
		}else{
			throw new BadEntrySpecificationException(Arrays.toString(fields));
		}
	}

	/**
	 * Read the text input file at the beginning of the program and populates the
	 * instances of the various possible types (books, DVDs, users).
	 *
	 * @param filename name of the file to load
	 * @throws BadEntrySpecificationException if there is a problem when registering the fields
	 * @throws IOException if a problem occurs when opening the file
	 */
	void importFile(String filename) throws BadEntrySpecificationException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String l;

		while((l = reader.readLine()) != null) {
			String line = new String(l.getBytes(), StandardCharsets.UTF_8);
			String[] splitLine = line.split(":");
			registerFromFields(splitLine);
		}

		reader.close();
	}

	/**
	 * Advances the date n days
	 *
	 * @param n number of days to advance
	 */
	public void advanceDate(int n){
		if(n > 0) {
			_date = _date + n;

			for(User user: _users.values()) {
				for(Request request: user.getRequests()) {
					if(request.getOnTime() && request.getReturnDate() < _date) {
						request.setOnTime(false);
						user.setIsActive(false);
						user.addFine(5);
						user.behavedPoorly();
					}
				}
			}
			_libChanged = true;
		}
	}

	/**
	 * Creates a user and returns its id
	 *
	 * @param name of the user to create
	 * @param email of the user to create
	 * @throws CreateUserFailedException if values are null or empty
	 * @return the id of the user created
	 */
	public int createUser(String name, String email) throws CreateUserFailedException {
		if(name == null || name.isEmpty() || email == null || email.isEmpty()) {
			throw new CreateUserFailedException();
		}
		User user = new User(_userId++, name, email);
		_users.put(user.getId(), user);
		_libChanged = true;
		return user.getId();
	}

	/**
	 * Gets a user with a given id
	 *
	 * @param id of the user to get
	 * @throws GetUserFailedException if it doesn't find the user
	 * @return the user
	 */
	public User getUser(int id) throws GetUserFailedException {
		User user = _users.get(id);
		if(user == null) {
			throw new GetUserFailedException();
		}
		return user;
    }

	/**
	 * Gets a List of the users registered in the library
	 *
	 * @return a List containing the users
	 */
	public List<User> getAllUsers() {
	    List<User> usersList = new LinkedList<>();
	    usersList.addAll(_users.values());
		return usersList;
    }

	/**
	 * Gets a work with a given id
	 * 
	 * @param id of the work
	 * @throws GetWorkFailedException if it doesn't find the work
	 * @return the work
	 */
	public Work getWork(int id) throws GetWorkFailedException {
		Work work = _works.get(id);
		if(work == null) {
			throw new GetWorkFailedException();
		}
		return work;
	}

	/**
	 * Gets a List of all the works in the library
	 * 
	 * @return a List of all of the works
	 */
	public List<Work> getAllWorks(){
		List<Work> worksList = new LinkedList<>();
		worksList.addAll(_works.values());
		return worksList;
	}

	/**
	 * @param userId of the user to pay the fine of
	 * @throws ActiveUserException if user has no fines to pay or is not suspended
	 */
	public void payFine(int userId) throws ActiveUserException {
		_users.get(userId).payFine();
		_libChanged = true;
	}

	/**
	 * Requests a work
	 *
	 * @param userId user that is requesting
	 * @param workId work that is being requested
	 * @throws RuleDeclinedException if a rule fails
	 */
	public void requestWork(int userId, int workId) throws RuleDeclinedException {
		User user = _users.get(userId);
		Work work = _works.get(workId);

		RuleSet rules = new RuleSet(user, work);
		rules.addRule(new RuleCantHaveNRequests(user, work));
		rules.addRule(new RuleCantRequestExpensiveWork(user, work));
		rules.addRule(new RuleCantRequestReference(user, work));
		rules.addRule(new RuleCantRequestTwice(user, work));
		rules.addRule(new RuleNoCopiesAvailable(user, work));
		rules.addRule(new RuleUserNotSuspended(user, work));

		if(user != null && work != null) {
			if(rules.validate()) {
				int returnDate = work.computeReturnDate(user);
				Request request = new Request(user, work, returnDate);
			}
			else {
				throw new RuleDeclinedException(false);
			}
		}
	}
}
