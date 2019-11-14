package m19;

import m19.exceptions.*;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * The façade class.
 */
public class LibraryManager {

	private Library _library;
	private String _filename;
	private boolean _libChanged = true; // variable which controls whether the library has been changed or not

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

		if (!_libChanged) {
			return;
		}

		ObjectOutputStream outputStream = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(_filename)));

		outputStream.writeObject(_library);
		outputStream.close();
		_libChanged = false;
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
			ObjectInputStream inputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(filename)));
			_library = (Library) inputStream.readObject();
			_filename = filename;
			inputStream.close();
			_libChanged = false;
		} catch (ClassNotFoundException | IOException ex) {
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
			_libChanged = true;
		} catch (IOException | BadEntrySpecificationException e) {
			throw new ImportFileException(e);
		}
	}

	/**
	 * Adds a user to the user aggregation in library
	 *
	 * @param name  of the user
	 * @param email of the user
	 * @return the id of the user registered
	 */
	public int createUser(String name, String email) throws CreateUserFailedException {
		if(name.isEmpty() || email.isEmpty()) {
			throw new CreateUserFailedException();
		}

		int ret = _library.createUser(name, email);
		_libChanged = true;
		return ret;
	}

	/**
	 * Returns a user with a given id
	 *
	 * @param id of the user to get
	 * @return the user
	 */
	public User getUser(int id) throws GetUserFailedException {
		User user = _library.getUser(id);
		
		if(user == null) {
			throw new GetUserFailedException();
		}
		return user;
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
	public Work getWork(int id) throws GetWorkFailedException {
		Work work = _library.getWork(id);

		if(work == null) {
			throw new GetWorkFailedException();
		}

		return work;
	}

	/**
	 * Returns all works in the library
	 * @return a  works in the library
	 */
	public Map<Integer, Work> getAllWorks(){
		return _library.getAllWorks();
	}

	/**
	 * Advances the date and updates users states
	 *
	 * @param n number of days to advance
	 */
	public void advanceDate(int n){
		_library.advanceDate(n);
		_libChanged = true;
	}

	/**
	 * @return the date
	 */
	public int getDate() {
		return _library.getDate();
  }

	/**
	 * @return the state of the lib, if it has changed true, false otherwise
	 */
	public boolean getLibChanged() {
		return _libChanged;
	}
}
