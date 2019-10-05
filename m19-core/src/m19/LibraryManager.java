package m19;

// FIXME import system types
// FIXME import project (core) types

/**
 * The façade class.
 */
public class LibraryManager {

  private Library _library;  // FIXME initialize this attribute

  // FIXME define other attributes

  // FIXME define contructor(s)
  
  // FIXME define methods

  /**
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void save() throws MissingFileAssociationException, IOException {
    // FIXME implement method
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
   * @param filename
   * @throws FailedToOpenFileException
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public void load(String filename) throws FailedToOpenFileException, IOException, ClassNotFoundException {
    // FIXME implement method
  }

  /**
   * @param datafile
   * @throws ImportFileException
   */
  public void importFile(String datafile) throws ImportFileException {
    try {
      _library.importFile(datafile);
    } catch (IOException | BadEntrySpecificationException e) {
      throw new ImportFileException(e);
    }
  }

}
