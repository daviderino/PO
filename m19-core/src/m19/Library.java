package m19;

import m19.exceptions.BadEntrySpecificationException;

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
	private int date = 0;

	private Map<Integer, User> _users = new HashMap<Integer, User>();
	private Map<Integer, Work> _works = new TreeMap<Integer, Work>();

	private int date = 0;

	/**
	 * @param fields with the work's info
	 */
	private void registerWork(String[] fields) {
		int id = _workId++;
		int price = Integer.parseInt(fields[3]);
		Category category = Category.valueOf(fields[4]);
		int count = Integer.parseInt(fields[6]);

		if(fields[0].equals("BOOK")) {
			Book book = new Book(id, fields[1], fields[2], price, category, fields[5], count);
			_works.put(id, book);
		}
		else if(fields[0].equals("DVD")) {
			DVD dvd = new DVD(id, fields[1], fields[2], price, category, fields[5], count);
			_works.put(id, dvd);
		}
	}

	/**
	 * @param fields with the user's info
	 */
	private void registerUser(String[] fields) {
		int id = _userId++;
		User user = new User(id, fields[1], fields[2]);
		_users.put(id, user);
	}

	/**
	 * @param fields to register
	 */
	private void registerFromFields(String[] fields) throws BadEntrySpecificationException {
		final Pattern patternWork = Pattern.compile("^(BOOK|DVD)");
		final Pattern patternUser = Pattern.compile("^(USER)");

		if(patternWork.matcher(fields[0]).matches()) {
			registerWork(fields);
		}
		else if(patternUser.matcher(fields[0]).matches()) {
			registerUser(fields);
		}
		else {
			throw new BadEntrySpecificationException(fields[0]);
		}
	}

	/**
	 * Read the text input file at the beginning of the program and populates the
	 * instances of the various possible types (books, DVDs, users).
	 *
	 * @param filename
	 *          name of the file to load
	 * @throws BadEntrySpecificationException
	 * @throws IOException
	 */
	void importFile(String filename) throws BadEntrySpecificationException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String l;

		while((l = reader.readLine()) != null) {
			String line = new String(l.getBytes(), StandardCharsets.UTF_8);
			String[] splitLine = line.split(":");
			registerFromFields(splitLine);
		}
	}

	/**
	 * Creates a user and returns its id
	 *
	 * @param name of the user
	 * @param email of the user
	 * @return the id of the user
	 */
	public int createUser(String name, String email) {
		User user = new User(_userId++, name, email);
		_users.put(user.getId(), user);
		return user.getId();
	}

	/**
	 * Gets a user with a given id
	 * @param id of the user
	 * @return the user
	 */
	public User getUser(int id) {
		return _users.get(id);
    }

	/**
	 * Gets a list of the users sorted by name or if the names are equal, by id
	 *
	 * @return a list containing the sorted users
	 */
	public List<User> getAllUsers() {
	    List<User> usersList = new LinkedList<>();
	    usersList.addAll(_users.values());
		Collections.sort(usersList);
		return usersList;
    }

	/**
	 * Gets a work with a given id
	 * @param id of the work
	 * @return the work
	 */
	public Work getWork(int id){
		return _works.get(id);
	}

	/**
	 * Gets all works in library
	 * @return a Map of all of the works
	 */
	public Map<Integer, Work> getAllWorks(){
		return _works;
	}

	/**
	 * Gets the date
	 * @return the date
	 */
	public int getDate() {
		return date;
  }
  
   /**
	 * Advances the date
	 * @param n number of days to advance
	 */
	public void advanceDate(int n){
		date = date + n;
		/*Verify Requests */
	}
}
