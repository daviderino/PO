package m19;

import m19.exceptions.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.List;

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
	 * Saves the current state of the library
	 *
	 * @throws MissingFileAssociationException if the variable containing the file name is null
	 * @throws IOException if there is a problem whilst the file
	 * @throws FileNotFoundException if there is a problem whilst saving the file
	 */
	public void save() throws MissingFileAssociationException, IOException, FileNotFoundException {
		if (_filename == null) {
			throw new MissingFileAssociationException();
		}

		if (!_library.getLibChanged()) {
			return;
		}

		ObjectOutputStream outputStream = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(_filename)));

		outputStream.writeObject(_library);
		outputStream.close();
		_library.setLibChanged(false);
	}

	/**
	 * Saves the current state of the library with filename as the file name
	 *
	 * @param filename of the file to save as
	 * @throws MissingFileAssociationException if the variable containing the name is empty
	 * @throws IOException if there is a problem when saving
	 */
	public void saveAs(String filename) throws MissingFileAssociationException, IOException {
		_filename = filename;
		save();
	}

	/**
	 * Loads a given file and changes the falg controlling whether or not the library has changed
	 *
	 * @param filename name of the file to load
	 * @throws FailedToOpenFileException if there is a problem when opening or processing the file
	 */
	public void load(String filename) throws FailedToOpenFileException, IOException, ClassNotFoundException {
		try {
			ObjectInputStream inputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(filename)));
			_library = (Library) inputStream.readObject();
			_filename = filename;
			inputStream.close();
			_library.setLibChanged(false);
		}
		catch (ClassNotFoundException | IOException ex) {
			throw new FailedToOpenFileException(filename);
		}
	}

	/**
	 * Calls importFile(datafile) in the library
	 * Throws exceptions if there are problems whilst processing the file
	 *
	 * @param datafile to import
	 * @throws ImportFileException if there is a IO problem or a wrong entry whilst processing the input file
	 */
	public void importFile(String datafile) throws ImportFileException {
		try {
			_library.importFile(datafile);
			_library.setLibChanged(true);
		}
		catch (IOException | BadEntrySpecificationException e) {
			throw new ImportFileException(e);
		}
	}

	/**
	 * Calls createUser(name, email) in the library
	 * Validates the input and throws an exception if parameters have bad values
	 *
	 * @param name  of the user
	 * @param email of the user
	 * @throws CreateUserFailedException if values are null or empty
	 * @return the id of the user registered
	 */
	public int createUser(String name, String email) throws CreateUserFailedException {
		return _library.createUser(name, email);
	}

	/**
	 * Calls getUser(id) from the library
	 *
	 * @param id of the user to get
	 * @return the user
	 */
	public User getUser(int id) throws GetUserFailedException {
		return _library.getUser(id);
	}

	/**
	 * Calls getAllUsers() from library
	 *
	 * @return a list containing the users
	 */
	public List<User> getAllUsers() {
		return _library.getAllUsers();
	}

	/**
	 * Calls getWork(id) from the library
	 *
	 * @param id of the work to get
	 * @return the work
	 */
	public Work getWork(int id) throws GetWorkFailedException {
		return _library.getWork(id);
	}

	/**
	 * Calls getAllWorks() from the library
	 *
	 * @return a  works in the library
	 */
	public List<Work> getAllWorks(){
		return _library.getAllWorks();
	}

	/**
	 * Calls advanceDate(n) from the library
	 *
	 * @param n number of days to advance
	 */
	public void advanceDate(int n){
		_library.advanceDate(n);
	}

	/**
	 * Calls getDate() from the library
	 *
	 * @return the date
	 */
	public int getDate() {
		return _library.getDate();
  }

	/**
	 * @param userId user to pay the fime
	 * @throws GetUserFailedException in case the user does not exist
	 * @throws ActiveUserException in case the user is active
	 */
    public void payFine(int userId) throws GetUserFailedException, ActiveUserException {
		_library.payFine(userId);
    }
}
