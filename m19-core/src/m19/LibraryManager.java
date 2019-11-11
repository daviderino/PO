package m19;

import m19.exceptions.BadEntrySpecificationException;
import m19.exceptions.FailedToOpenFileException;
import m19.exceptions.ImportFileException;
import m19.exceptions.MissingFileAssociationException;

import java.io.*;

/**
 * The façade class.
 */
public class LibraryManager {

	private Library _library;
	private String _filename;

	/**
	 * @throws MissingFileAssociationException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void save() throws MissingFileAssociationException, IOException, FileNotFoundException {
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(_filename));
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
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename));
		_library = (Library)inputStream.readObject();
		_filename = filename;
		inputStream.close();
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
}
