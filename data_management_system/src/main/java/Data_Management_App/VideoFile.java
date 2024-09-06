package Data_Management_App;

/**
 * The VideoFile class represents a video file with a specific length in minutes.
 * It extends the DataFile class.
 */
public class VideoFile extends DataFile {
    // Creating variables
    private int lengthInMins;

    /**
     * No-argument constructor.
     * Assigns a unique ID.
     */
    public VideoFile() {
        super();
    }

    /**
     * Argument constructor.
     *
     * @param filename the name of the file
     * @param fileSize the size of the file
     * @param price the price of the file
     * @param lengthInMins the length of the video in minutes
     */
    public VideoFile(String filename, int fileSize, double price, int lengthInMins) {
        super(filename, fileSize, price);
        this.lengthInMins = lengthInMins;
    }

    /**
     * Gets the length of the video in minutes.
     *
     * @return the length of the video in minutes
     */
    public int getLengthInMins() {
        return lengthInMins;
    }

    /**
     * Sets the length of the video in minutes.
     * The duration must be greater than 0 and less than or equal to 180 minutes.
     *
     * @param lengthInMins the length of the video in minutes
     * @throws DatafileExeption if the duration is less than 0 or more than 180 minutes
     */
    public void setLengthInMins(int lengthInMins) throws DatafileExeption {
        if (lengthInMins > 0 && lengthInMins <= 180) {
            this.lengthInMins = lengthInMins;
        } else {
            throw new DatafileExeption("Duration can't be less than 0 and can't be more than 180 mins");
        }
    }

    /**
     * Returns a string representation of the VideoFile object.
     *
     * @return a string representation of the VideoFile object
     */
    @Override
    public String toString() {
        return "VideoFile{" + "id=" + getId() + ", fileName='" + getFileName() + '\'' + ", fileSize=" + getFileSize() + ", price=" + getPrice() + ", lengthInMins=" + lengthInMins + '}';
    }

    /**
     * Checks if this VideoFile is equal to another object.
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
        VideoFile vd = (VideoFile) obj;
        return lengthInMins == vd.lengthInMins;
    }

    /**
     * Returns the hash code value for this VideoFile.
     *
     * @return the hash code value for this VideoFile
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + lengthInMins;
        return result;
    }
}