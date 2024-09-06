package Data_Management_App;
import java.io.*;
import java.util.*;
import java.util.Map;
/**
 * The Stock class manages a collection of files, including DataFiles, VideoFiles, and EBooks.
 */
public class Stock {
    private List<Object> myFiles;

    /**
     * Constructs a new Stock object with an empty list of files.
     */
    public Stock() {
        myFiles = new ArrayList<>();
    }

    /**
     * Loads files from a specified file and adds them to the list. Invalid entries are written to "invalid.txt".
     *
     * @param filename the name of the file to load
     */
    public void loadFiles(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename));
             BufferedWriter invalidWriter = new BufferedWriter(new FileWriter("data_management_system\\src\\main\\java\\Data_Management_App\\invalid.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Object file = parseLine(line);
                    if (file != null) {
                        myFiles.add(file);
                    } else {
                        invalidWriter.write(line);
                        invalidWriter.newLine();
                    }
                } catch (Exception e) {
                    invalidWriter.write(line);
                    invalidWriter.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses a line of text and creates a corresponding file object.
     *
     * @param line the line of text to parse
     * @return the created file object, or null if the line is invalid
     */
    private Object parseLine(String line) {
        // divides them into parts for parsing
        String[] parts = line.split(",");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0].trim();
        String fileName = parts[1].trim();
        int fileSize;
        double price;
        int numPages;

        try {
            // inited here to catch errors
            // removes trailing whitespace
            fileSize = Integer.parseInt(parts[2].trim());
            // removes trailing whitespace
            price = Double.parseDouble(parts[3].trim());
            //if filsizew is less than 0 or price is less than 0
            if (fileSize < 0 || price < 0) {
                return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }

        switch (type) {
            // Checking DataFile entry is valid or not
            case "DataFile":
                if (parts.length < 4) {
                    return null;
                }
                return new DataFile(fileName, fileSize, price);

            // Checking VideoFile entry is valid or not
            case "VideoFile":
                if (parts.length < 5) {
                    return null;
                }
                int lengthInMins;
                try {
                    // removes trailing whitespace
                    lengthInMins = Integer.parseInt(parts[4].trim());
                } catch (NumberFormatException e) {
                    return null;
                }
                //if length is less than 0
                if(lengthInMins < 0){
                    return null;
                }
                return new VideoFile(fileName, fileSize, price, lengthInMins);

            // Checking EBook entry is valid or not by checking if parts length is less than 7 and entry is valid or not
            case "EBook":
                if (parts.length < 7) {
                    return null;
                }
                String title = parts[4].trim();
                String author = parts[5].trim();

                try {
                    // removes trailing whitespace
                    numPages = Integer.parseInt(parts[6].trim());
                } catch (NumberFormatException e) {
                    return null;
                }
                //if number of pages is less than 0 
                if(numPages < 0){
                    return null;
                }
                return new EBook(fileName, fileSize, price, title, author, numPages);

            // If the entry is invalid
            default:
                return null;
        }
    }

    /**
     * Adds a file to the list of files.
     *
     * @param file the file to add
     */
    public void addFile(Object file) {
        myFiles.add(file);
    }

    /**
     * Returns the list of files.
     *
     * @return the list of files
     */
    public List<Object> getMyFiles() {
        return myFiles;
    }

    /**
     * Displays the files sorted alphabetically by file name.
     */
    public void displayAlpha() {
        // Sort the files alphabetically by file name
        myFiles.sort((file1, file2) -> {
            String name1 = getFileName(file1);
            String name2 = getFileName(file2);
            return name1.compareToIgnoreCase(name2);
        });

        // Display the sorted files
        for (Object file : myFiles) {
            System.out.println(file);
        }
    }

    /**
     * Displays the video files sorted by price and length.
     */
    public void displayVideo() {
        // Filter and sort video files by price and length
        
        myFiles.stream()
                .filter(file -> file instanceof VideoFile)
                .sorted(Comparator.comparingDouble((Object file) -> ((VideoFile) file).getPrice())
                        .thenComparingInt(file -> ((VideoFile) file).getLengthInMins()))
                .forEach(System.out::println);
    }

    /**
     * Edits the number of pages of an eBook identified by its title.
     *
     * @param bookTitle the title of the eBook
     * @param numberOfPages the new number of pages
     */
    public void editPages(String bookTitle, int numberOfPages) {
        // Find the eBook by title and edit the number of pages
        try {
            for (Object file : myFiles) {
                if (file instanceof EBook && ((EBook) file).getTitle().equalsIgnoreCase(bookTitle)) {
                    ((EBook) file).setNumPages(numberOfPages);
                    System.out.println("Updated pages for book: " + bookTitle);
                    return;
                }
            }
        } catch (DatafileExeption e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Book not found: " + bookTitle);
    }

    /**
     * Returns the file name of a given file object.
     *
     * @param file the file object
     * @return the file name
     */
    private String getFileName(Object file) {
        if (file instanceof DataFile) {
            return ((DataFile) file).getFileName();
        } else if (file instanceof VideoFile) {
            return ((VideoFile) file).getFileName();
        } else if (file instanceof EBook) {
            return ((EBook) file).getFileName();
        }
        return "";
    }

    /**
     * Creates a map of authors to eBooks.
     *
     * @return the map of authors to eBooks
     */

    public Map<String, List<EBook>> createAuthorMap() {
        Map<String, List<EBook>> authorMap = new HashMap<>();
        for (Object file : myFiles) {
            if (file instanceof EBook) {
                EBook book = (EBook) file;
                authorMap.computeIfAbsent(book.getAuthor(), k -> new ArrayList<>()).add(book);
            }
        }
        return authorMap;
    }
}

