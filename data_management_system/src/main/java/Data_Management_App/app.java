package Data_Management_App;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class app {
    
    //Initializing the main variables to be used in the program

    private static Stock stock = new Stock();
    private static Scanner scanner = new Scanner(System.in);

    //Main method to run the program
    public static void main(String[] args) {

        //Loads the files from the file
        System.out.println("Loading files...");
        stock.loadFiles("data_management_system\\src\\main\\java\\Data_Management_App\\datafiles.txt");
        System.out.println("Files loaded.");
        System.out.println();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addFile();
                    break;
                case 2:
                    editPages();
                    break;
                case 3:
                    displayBooksByAuthor();
                    break;
                case 4:
                    stock.displayAlpha();
                    break;
                case 5:
                    stock.displayVideo();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /*
     * Displays the menu options too the user
     */
    private static void displayMenu() {
        System.out.println("1. Add File");
        System.out.println("2. Edit Pages in EBook");
        System.out.println("3. Display Books by Author");
        System.out.println("4. Display All Files Alphabetically");
        System.out.println("5. Display Video Files by Price and Length");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    /*
     * Gets the user choice  from the console and validates it if it is wrong
     * 
     * @return the user choice else -1 if the input is invalid
     * 
     */
    private static int getUserChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next(); // clear invalid input
            return -1;
        }
    }

    /*
     * Adds a file to the stock objecct using user input 
     */
    private static void addFile() {

        //Enters main details of the file
        System.out.print("Enter file type (DataFile, VideoFile, EBook): ");
        String type = scanner.nextLine();
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
        System.out.print("Enter file size: ");
        int fileSize = getIntInput();
        System.out.print("Enter price: ");
        double price = getDoubleInput();


        //Stores if just Regular File or creates and adds appropriate file
        switch (type) {
            case "DataFile":
                stock.addFile(new DataFile(fileName, fileSize, price));
                break;
            case "VideoFile":
                System.out.print("Enter length in minutes: ");
                int lengthInMins = getIntInput();
                stock.addFile(new VideoFile(fileName, fileSize, price, lengthInMins));
                break;
            case "EBook":
                System.out.print("Enter Title of e-book: ");
                String title = scanner.nextLine();
                System.out.print("Enter author: ");
                String author = scanner.nextLine();
                System.out.print("Enter number of pages: ");
                int numPages = getIntInput();
                stock.addFile(new EBook(fileName, fileSize, price, title, author , numPages));
                break;
            default:
                System.out.println("Invalid file type.");
        }
    }

    
    /*
     * Edits the number of pages in an EBook
     */
    private static void editPages() {
        System.out.print("Enter file name: ");
        String fileName = scanner.next();
        System.out.print("Enter new number of pages: ");
        int newPages = getIntInput();
        stock.editPages(fileName, newPages);
    }

    /*
     * Displays all books by a certain author
     */
    private static void displayBooksByAuthor() {
        System.out.print("Enter author name: ");
        String author = scanner.next();
        Map<String, List<EBook>> authorMap = stock.createAuthorMap();
        List<EBook> books = authorMap.get(author);
        if (books != null && !books.isEmpty()) {
            books.forEach(System.out::println);
        } else {
            System.out.println("No books found for author: " + author);
        }
    }

    /*
     * Gets an integer input from the user  and validates it if not it should re entered
     */
    private static int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); // clear invalid input
            }
        }
    }

    /*
     * Gets a double input from the user and validates it if not it should be re entered
     */
    private static double getDoubleInput() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); // clear invalid input
            }
        }
    }
}

