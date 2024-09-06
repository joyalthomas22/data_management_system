package Data_Management_App;

/**
 * initialize variables unique ID, file name, file size, and price.
 * the nextid is  static
 */
public class DataFile implements Comparable<DataFile> {
    private int id;
    private static int nextid = 50;
    private String fileName;
    private int fileSize;
    private double price;

    /**
     * No-argument constructor
     */
    public DataFile() {
        this.id = nextid;
        nextid++;
    }

    /**
     * Constructor with arguments that assigns a unique ID and initializes the data file's attributes.
     *
     * @param fileName the name of the file
     * @param fileSize the size of the file in MB
     * @param price the price of the file
     */
    public DataFile(String fileName, int fileSize, double price) {
        this.id = nextid;
        nextid++;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.price = price;
    }

    /**
     * Gets the unique ID of the data file.
     *
     * @return the unique ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the file.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Gets the size of the file in MB.
     *
     * @return the file size
     */
    public int getFileSize() {
        return fileSize;
    }

    /**
     * Gets the price of the file.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the name of the file. The file name cannot be null, empty, or longer than 20 characters.
     *
     * @param fileName the new file name
     * @throws DatafileExeption if the file name is invalid
     */
    public void setFileName(String fileName) throws DatafileExeption {
        if (fileName != null && !fileName.isEmpty() && fileName.length() <= 20) {
            this.fileName = fileName;
        } else {
            throw new DatafileExeption("File name can't be empty and can't be more than 20 characters");
        }
    }

    /**
     * Sets the size of the file. The file size must be greater than 0 and less than or equal to 1000 MB.
     *
     * @param fileSize the new file size
     * @throws DatafileExeption if the file size is invalid
     */
    public void setFileSize(int fileSize) throws DatafileExeption {
        if (fileSize > 0 && fileSize <= 1000) {
            this.fileSize = fileSize;
        } else {
            throw new DatafileExeption("File size can't be less than 0 and can't be more than 1000 MB");
        }
    }

    /**
     * Sets the price of the file. The price must be greater than 0 and less than or equal to 100.
     *
     * @param price the new price
     * @throws DatafileExeption if the price is invalid
     */
    public void setPrice(double price) throws DatafileExeption {
        if (price > 0 && price <= 100) {
            this.price = price;
        } else {
            throw new DatafileExeption("Price can't be less than 1 and can't be more than 100");
        }
    }

    /**
     * Returns a string representation of the data file.
     *
     * @return a string representation of the data file
     */
    @Override
    public String toString() {
        return "DataFile{" + "id=" + id + ", fileName='" + fileName + '\'' + ", fileSize=" + fileSize +
                ", price=" + price + '}';
    }

    /**
     * Checks if this data file is equal to another object.
     *
     * @param obj the object to compare to
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

        DataFile df = (DataFile) obj;
        return this.fileName.equals(df.fileName) || this.fileSize == df.fileSize;
    }

    /**
     * Returns a hash code value for the data file.
     *
     * @return a hash code value for the data file
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + fileName.hashCode();
        result = 31 * result + fileSize;
        return result;
    }

    /**
     * Compares this data file to another data file by file name.
     *
     * @param dataFileName the data file to compare to
     * @return a negative integer, zero, or a positive integer as this data file is less than, equal to, or greater than the specified data file
     * @throws IllegalArgumentException if the specified object is not a DataFile
     */
    @Override
    public int compareTo(DataFile dataFileName) {
        if (!(dataFileName instanceof DataFile)) {
            throw new IllegalArgumentException("Enter a DataFile object");
        }
        return this.fileName.compareTo(dataFileName.fileName);
    }
}