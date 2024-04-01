import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class LibraryManagementSystem {
    private Map<String, Book> books;
    private List<User> users;

    public LibraryManagementSystem() {
        books = new HashMap<>();
        users = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        Book book = new Book(title, author);
        books.put(title, book);
    }

    public void addUser(String name) {
        User user = new User(name);
        users.add(user);
    }

    public void borrowBook(String title, String userName) {
        Book book = books.get(title);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println(userName + " has borrowed " + title);
        } else {
            System.out.println("Book is not available for borrowing.");
        }
    }

    public void returnBook(String title, String userName) {
        Book book = books.get(title);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println(userName + " has returned " + title);
        } else {
            System.out.println("Book is not borrowed or does not exist.");
        }
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        library.addBook("Java Programming", "John Doe");
        library.addBook("Python Basics", "Jane Smith");
        library.addUser("Alice");
        library.addUser("Bob");

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. Display Available Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter book title to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String borrower = scanner.nextLine();
                    library.borrowBook(borrowTitle, borrower);
                    break;
                case 2:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter book title to return: ");
                    String returnTitle = scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String returner = scanner.nextLine();
                    library.returnBook(returnTitle, returner);
                    break;
                case 3:
                    library.displayAvailableBooks();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
