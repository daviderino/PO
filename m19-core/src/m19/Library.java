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

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201901101348L;
	private int _userId = 0;

	private Map<String, User> _users = new HashMap<String, User>();

	// FIXME define attributes

	// FIXME define contructor(s)

	// FIXME define methods

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
		int lineNumber = 0;

		while(!(l = reader.readLine()).equals(null)) {
			String line = new String(l.getBytes(), StandardCharsets.UTF_8);
			lineNumber++;

			String[] split = line.split(":");

			try {
				switch(split[0]) {
					case "USER":
						_users.put(split[2], new User(_userId++, split[1], split[2]));
						break;
					case "DVD":
						break;
					case "BOOK":
						break;
				}
			}
			catch(NumberFormatException ex) {
				throw new BadEntrySpecificationException(line, ex);
			}
		}
	}
}
