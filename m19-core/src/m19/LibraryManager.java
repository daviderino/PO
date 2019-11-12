package m19;

import m19.exceptions.BadEntrySpecificationException;
import m19.exceptions.FailedToOpenFileException;
import m19.exceptions.ImportFileException;
import m19.exceptions.MissingFileAssociationException;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * The façade class.
 */
public class LibraryManager {

	private Library _library;
	private String _filename;

	/**
	 * Creates a new object to allow to import files
	 */
	public LibraryManager() {
		_library = new Library();
		_filename = null;
	}

	/**
	 * @throws MissingFileAssociationException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void save() throws MissingFileAssociationException, IOException, FileNotFoundException {
		if (_filename == null) {
			throw new MissingFileAssociationException();
		}

		ObjectOutputStream outputStream = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(_filename)));
		outputStream.writeObject(_library);
		outputStream.close();
	}

	/**
	 * @param filename
	 * @throws MissingFileAssociationException
	 * @throws IOException
	 */
	public void saveAs(String filename) throws MissingFileAssociationException, IOException {
		_filename = filename;
		save();
	}

	/**
	 * @param filename name of the file
	 * @throws FailedToOpenFileException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void load(String filename) throws FailedToOpenFileException, IOException, ClassNotFoundException {
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
			_library = (Library)inputStream.readObject();
			_filename = filename;
			inputStream.close();
		}
		catch(IOException ex) {
			throw new FailedToOpenFileException(filename);
		}
	}

	/**
	 * @param datafile
	 * @throws ImportFileException
	 */
	public void importFile(String datafile) throws ImportFileException {
		try {
			_library.importFile(datafile);
		}
		catch (IOException | BadEntrySpecificationException e) {
			throw new ImportFileException(e);
		}
	}

	/**
	 * Adds a user to the user aggregation in library
	 *
	 * @param name of the user
	 * @param email of the user
	 * @return the id of the user registered
	 */
	public int createUser(String name, String email) {
		return _library.createUser(name, email);
	}

	/**
	 * Returns a user with a given id
	 *
	 * @param id of the user to get
	 * @return the user
	 */
	public User getUser(int id) {
		return _library.getUser(id);
	}

	/**
	 * Calls getAllUsers() from library
	 *
	 * @return a sorted list containing the users
	 */
	public List<User> getAllUsers() {
		return _library.getAllUsers();
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return _filename;
	}

	/**
	 * Returns a work in the library
	 * @param id of the work to get
	 * @return the work
	 */
	public Work getWork(int id){
		return _library.getWork(id);
	}

	/**
	 * Returns all works in the library
	 * @return a  works in the library
	 */
	public Map<Integer, Work> getAllWorks(){
		return _library.getAllWorks();
	}

	/**
	 * @return the date
	 */
	public int getDate() {
		return _library.getDate();
  }

	/**
	 * Advances the date and updates users states
	 *
	 * @param n number of days to advance
	 */
	public void advanceDate(int n){
		_library.advanceDate(n);
	}
}
