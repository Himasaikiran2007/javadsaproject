import java.util.*;

class Student {
    String name;
    int id;
    String stream;
    String book1 = null;
    String book2 = null;
    int bookCount = 0;

    Student(String name, int id, String stream) {
        this.name = name;
        this.id = id;
        this.stream = stream;
    }
}

public class LibraryManagement {

    static Scanner sc = new Scanner(System.in);

    static HashMap<String, Integer> books = new HashMap<>();

    static Student students[] = {
            new Student("Rajvi", 1741078, "B.Tech-ICT"),
            new Student("Krushna", 1741086, "B.Tech-ICT"),
            new Student("Kalagee", 1741052, "B.Tech-ICT")
    };

    static Student findStudent(int id) {
        for (Student s : students) {
            if (s.id == id)
                return s;
        }
        return null;
    }

    public static void librarianMenu() {

        while (true) {

            System.out.println("\n===== LIBRARIAN MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Update Quantity");
            System.out.println("4. View Books");
            System.out.println("5. Logout");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Enter Book Name: ");
                    String name = sc.next();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    books.put(name, qty);

                    System.out.println("Book Added Successfully.");
                    break;

                case 2:
                    System.out.print("Enter Book Name: ");
                    String deleteBook = sc.next();

                    if (books.containsKey(deleteBook)) {
                        books.remove(deleteBook);
                        System.out.println("Book Deleted.");
                    } else {
                        System.out.println("Book Not Found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Book Name: ");
                    String updateBook = sc.next();

                    if (books.containsKey(updateBook)) {

                        System.out.print("Add Quantity: ");
                        int q = sc.nextInt();

                        books.put(updateBook,
                                books.get(updateBook) + q);

                        System.out.println("Updated Successfully.");
                    } else {
                        System.out.println("Book Not Found.");
                    }
                    break;

                case 4:

                    System.out.println("\nAvailable Books:");

                    for (Map.Entry<String, Integer> e : books.entrySet()) {
                        System.out.println(
                                e.getKey() + " -> " + e.getValue());
                    }
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public static void userMenu() {

        while (true) {

            System.out.println("\n===== USER MENU =====");
            System.out.println("1. Issue Book");
            System.out.println("2. Return Book");
            System.out.println("3. Logout");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:

                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();

                    Student s = findStudent(id);

                    if (s == null) {
                        System.out.println("Invalid ID");
                        break;
                    }

                    if (s.bookCount >= 2) {
                        System.out.println("Maximum 2 books allowed.");
                        break;
                    }

                    System.out.print("Enter Book Name: ");
                    String book = sc.next();

                    if (!books.containsKey(book)) {
                        System.out.println("Book Not Found.");
                        break;
                    }

                    if (books.get(book) == 0) {
                        System.out.println("Book Out Of Stock.");
                        break;
                    }

                    if (s.book1 == null)
                        s.book1 = book;
                    else
                        s.book2 = book;

                    s.bookCount++;

                    books.put(book, books.get(book) - 1);

                    System.out.println("Book Issued Successfully.");
                    break;

                case 2:

                    System.out.print("Enter Student ID: ");
                    int sid = sc.nextInt();

                    Student st = findStudent(sid);

                    if (st == null) {
                        System.out.println("Invalid ID");
                        break;
                    }

                    System.out.print("Enter Book Name: ");
                    String returnBook = sc.next();

                    boolean found = false;

                    if (st.book1 != null &&
                            st.book1.equalsIgnoreCase(returnBook)) {
                        st.book1 = null;
                        found = true;
                    }

                    if (st.book2 != null &&
                            st.book2.equalsIgnoreCase(returnBook)) {
                        st.book2 = null;
                        found = true;
                    }

                    if (found) {

                        st.bookCount--;

                        books.put(returnBook,
                                books.get(returnBook) + 1);

                        System.out.println("Book Returned Successfully.");
                    } else {
                        System.out.println("Book Not Issued To You.");
                    }

                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== LIBRARY MANAGEMENT =====");
            System.out.println("1. Librarian Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter User ID: ");
                    String id = sc.next();

                    System.out.print("Enter Password: ");
                    String pwd = sc.next();

                    if (id.equals("dsa@1") &&
                            pwd.equals("abc123")) {

                        System.out.println("Login Successful");
                        librarianMenu();
                    } else {
                        System.out.println("Invalid Credentials");
                    }

                    break;

                case 2:
                    userMenu();
                    break;

                case 3:
                    System.out.println("Thank You");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}