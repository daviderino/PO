package m19;

// FIXME import system types
// FIXME import project (core) types

import m19.exceptions.BadEntrySpecificationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
	 * Read the text input file at the beginning of the program and populates the
	 * instances of the various possible types (books, DVDs, users).
	 *
	 * @param filename
	 *          name of the file to load
	 * @throws BadEntrySpecificationException
	 * @throws IOException
	 */
	void importFile(String filename) throws BadEntrySpecificationException, IOException {
		final String STRING_USER = "USER";
		final String STRING_DVD = "DVD";
		final String STRING_BOOK = "BOOK";

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String l;

		while(!(l = reader.readLine()).equals(null)) {
			String line = new String(l.getBytes(), StandardCharsets.UTF_8);

			String[] split = line.split(":");

			try {
				if(split[0].equals(STRING_USER)) {
					_users.put(split[2], new User(_userId++, split[1], split[2]));
				}
				else{
					int price = Integer.parseInt(split[3]);
					Category category = Category.valueOf(split[4]);
					int count = Integer.parseInt(split[6]);

					if(split[0].equals(STRING_DVD)) {
						_works.put(_workId, new DVD(_workId++, split[1], split[2], price, category, split[5], count));
					}
					else if(split[0].equals(STRING_BOOK)) {
						_works.put(_workId, new Book(_workId++, split[1], split[2], price, category, split[5], count));
					}
				}
			}
			catch(NumberFormatException ex) {
				throw new BadEntrySpecificationException(line, ex);
			}
		}
	}
}
