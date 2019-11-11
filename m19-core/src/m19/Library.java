package m19;

import m19.exceptions.BadEntrySpecificationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201901101348L;
	private int _userId = 0;
	private int _workId = 0;

	private Map<String, User> _users = new HashMap<String, User>();
	private Map<Integer, Work> _works = new TreeMap<Integer, Work>();

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
		User user = new User(_userId++, fields[1], fields[2]);
		_users.put(user.getName(), user);
	}

	/**
	 * @param fields
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

	public Work getWork(int id){
		return _works.get(id);
	}

	public Map<Integer, Work> getAllWorks(){
		return _works;
	}
}
