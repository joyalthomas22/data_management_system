package Data_Management_App;

/**
 * The EBook class represents an electronic book with a title, author, and number of pages.
 * It extends the DataFile class.
 */
public class EBook extends DataFile {
    // Initializing the variables
    private String title;
    private String author;
    private int numPages;

    /**
     * No-argument constructor.
     * Assigns a unique ID.
     */
    public EBook() {
        super();
    }

    /**
     * Argument constructor.
     *
     * @param fileName the name of the file
     * @param fileSize the size of the file
     * @param price the price of the file
     * @param title the title of the eBook
     * @param author the author of the eBook
     * @param numPages the number of pages in the eBook
     */
    public EBook(String fileName, int fileSize, double price, String title, String author, int numPages) {
        super(fileName, fileSize, price);
        this.title = title;
        this.author = author;
        this.numPages = numPages;
    }

    /**
     * Gets the title of the eBook.
     *
     * @return the title of the eBook
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the author of the eBook.
     *
     * @return the author of the eBook
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the number of pages in the eBook.
     *
     * @return the number of pages in the eBook
     */
    public int getNumPages() {
        return numPages;
    }

    /**
     * Sets the title of the eBook.
     * The title must not be null, empty, or longer than 50 characters.
     *
     * @param title the title of the eBook
     * @throws DatafileExeption if the title is invalid
     */
    public void setTitle(String title) throws DatafileExeption {
        if (title != null && !title.isEmpty() && title.length() <= 50) {
            this.title = title;
        } else {
            throw new DatafileExeption("Title can't be empty and can't be more than 50 characters");
        }
    }

    /**
     * Sets the author of the eBook.
     * The author must not be null, empty, or longer than 50 characters.
     *
     * @param author the author of the eBook
     * @throws DatafileExeption if the author is invalid
     */
    public void setAuthor(String author) throws DatafileExeption {
        if (author != null && !author.isEmpty() && author.length() <= 50) {
            this.author = author;
        } else {
            throw new DatafileExeption("Author can't be empty and can't be more than 50 characters");
        }
    }

    /**
     * Sets the number of pages in the eBook.
     * The number of pages must be greater than 1.
     *
     * @param numPages the number of pages in the eBook
     * @throws DatafileExeption if the number of pages is invalid
     */
    public void setNumPages(int numPages) throws DatafileExeption {
        if (numPages > 1) {
            this.numPages = numPages;
        } else {
            throw new DatafileExeption("Number of pages can't be less than 1");
        }
    }

    /**
     * Returns a string representation of the EBook object.
     *
     * @return a string representation of the EBook object
     */
    @Override
    public String toString() {
        return "EBook{" + "id=" + getId() + ", fileName='" + getFileName() + '\'' + ", fileSize=" + getFileSize() + ", price=" + getPrice() + ", title='" + title + '\'' + ", author='" + author + '\'' + ", numPages=" + numPages + '}';
    }

    /**
     * Checks if this EBook is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        EBook eb = (EBook) obj;
        if (this.title.equals(eb.title)) {
            return true;
        }
        if (this.author.equals(eb.author)) {
            return true;
        }
        return this.numPages == eb.numPages;
    }

    /**
     * Returns the hash code value for this EBook.
     *
     * @return the hash code value for this EBook
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + numPages;
        return result;
    }
}