package com.nilsedgar;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {

    Scanner scanner = new Scanner(System.in);
    //ArrayList<Book> books = new ArrayList<>();
    //ArrayList<User> users = new ArrayList<>();
    List<Book> books = (List<Book>) FileUtility.loadObject("books.ser");
    ArrayList<User> users  = (ArrayList<User>) FileUtility.loadObject("users.ser");
    User loggedInCustomer = new User(null);
    User loggedInAdmin = new User(null);

    public Program() {
        showMainMenu();
    }


    public void showListOfBooks(){
        List<Book> books = (List<Book>) FileUtility.loadObject("books.ser");
        for(Book book: books){
            System.out.println(book.getTitle() + " " + book.getAuthor() + " " + book.getGenre() + "\n");
            if(book.getIsAvailable()){
                System.out.println("\nThis book is available");
            }
            else{
                System.out.println("This book is not available");
            }
        }
        showCustomerMenu();
    }

    public void borrowBook(){
        try {
            List<Book> books = (List<Book>) FileUtility.loadObject("books.ser");
            System.out.println("Enter the title of the book you wish to borrow: ");
            String borrowBook = scanner.next();
            for (Book book : books) {
                if (book.getTitle().equals(borrowBook) && book.getIsAvailable() == true) {
                    loggedInCustomer.setBorrowedBook(book.getTitle());
                    book.setIsAvailable(false);
                }
            }
        }catch (Exception e){
            System.out.println("Something went wrong");
        }

        FileUtility.saveObject("books.ser", books);
        FileUtility.saveObject("users.ser", users);
        showCustomerMenu();
    }

    public void showMyBorrowedBooks(){
        loggedInCustomer.getBorrowedBook();
        showCustomerMenu();
    }

    public void returnBook(){

    }

    public void searchForAuthor(){
        try {
            List<Book> books = (List<Book>) FileUtility.loadObject("books.ser");
            System.out.println("Enter name of author: ");
            String bookAuthor = scanner.next();

            for (Book book : books) {
                if (book.getAuthor().equals(bookAuthor)) {
                    System.out.println("This author has written: " + book.getTitle());
                }
            }
                showCustomerMenu();
        }catch (Exception e){
            System.out.println("Something went wrong");
        }
    }

    public void addNewBook(){
        List<Book> books = (List<Book>) FileUtility.loadObject("books.ser");
        System.out.printf("Enter title: ");
        String bookTitle = scanner.next();
        System.out.printf("Enter author: ");
        String bookAuthor = scanner.next();
        System.out.print("Enter genre: ");
        String bookGenre = scanner.next();
        books.add(new Book(bookTitle, bookAuthor, bookGenre));
        FileUtility.saveObject("books.ser", books);
        showAdminMenu();
    }

    public void addNewCustomer(){
        ArrayList<User> users = (ArrayList<User>) FileUtility.loadObject("users.ser");
        System.out.printf("Enter name:");
        String customerName = scanner.next();
        users.add(new UserCustomer(customerName));
        FileUtility.saveObject("users.ser", users);
        showAdminMenu();
    }

    public void showAllUsers(){
        ArrayList<User> users = (ArrayList<User>) FileUtility.loadObject("users.ser");
        for(User user: users){
            System.out.println(user.getName());
        }
        showAdminMenu();
    }

    public void searchForUserName(){
        try {
            ArrayList<User> users = (ArrayList<User>) FileUtility.loadObject("users.ser");
            System.out.printf("Enter name: ");
            String nameSearch = scanner.next();
        for(User user: users){
            if(user.getName().equals(nameSearch)){
                System.out.println("There is a user by that name");
            }
            else{
                System.out.println("Could not find user");
            }
        }
        }catch(InputMismatchException e){
            System.out.println("Wrong input");
        }
        showAdminMenu();
    }



    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean active = true;
        System.out.println("Welcome to the Library");
        System.out.println("1. Login as Admin");
        System.out.println("2. Login as Customer");
        System.out.println("3. Exit");
        try {
            int selection = scanner.nextInt();

        while (active) {
            switch (selection) {
                case 1:
                    loginAsAdmin();
                    break;
                case 2:
                    loginAsCustomer();
                    break;
                case 3:
                    active = false;
                    break;
            }
        }
        }catch (Exception e){
            System.out.println("Wrong input. Please use 1, 2 or 3");
            showMainMenu();
        }
    }

    private void loginAsCustomer(){
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String pass = scanner.nextLine();

        ArrayList<User> users = (ArrayList<User>) FileUtility.loadObject("users.ser");

        for (User user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(pass)){
                loggedInCustomer.setName(username);
                showCustomerMenu();
                break;
            }
        }
        System.out.println("Wrong username or password");
    }

    private void showCustomerMenu() {
        System.out.println("Logged in as: " + loggedInCustomer.getName());
        System.out.println("1. Show list of all books");
        System.out.println("2. Borrow a book");
        System.out.println("3. Search authors");
        System.out.println("4. Show my borrowed books");
        System.out.println("5. Return books");
        System.out.println("6. Return to main menu");
        int select = scanner.nextInt();
        switch (select) {
            case 1:
                showListOfBooks();
                break;
            case 2:
                borrowBook();
                break;
            case 3:
                searchForAuthor();
                break;
            case 4:
                showMyBorrowedBooks();
                break;
            case 5:
                returnBook();
                break;
            case 6:
                showMainMenu();
                loggedInCustomer.setName(null);
                break;
        }

    }

    private void loginAsAdmin() {

        ArrayList<User> users = (ArrayList<User>) FileUtility.loadObject("users.ser");

        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String pass = scanner.nextLine();


        for (User user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(pass)){
                loggedInAdmin.setName(username);
                showAdminMenu();
            }
        }
                System.out.println("Wrong username or password");

    }

    private void showAdminMenu() {
        System.out.println("Logged in as: " + loggedInAdmin.getName());
        System.out.println("1. Add new book");
        System.out.println("2. Add new customer");
        System.out.println("3. Show all users");
        System.out.println("4. Search for users");
        System.out.println("5. Return to main menu");
        int select = scanner.nextInt();
        switch (select) {
            case 1:
                addNewBook();
                break;
            case 2:
                addNewCustomer();
                break;
            case 3:
                showAllUsers();
                break;
            case 4:
                searchForUserName();
                break;
            case 5:
                showMainMenu();
                loggedInAdmin.setName(null);
                break;
        }
    }

    public void createUserList() {
        users.add(new User("Nils"));
        users.add(new User("Karin"));
        users.add(new User("Elvis"));
        users.add(new User("Donna"));
        users.add(new User("Bo"));

        FileUtility.saveObject("users.ser", users);
    }


    public void createBookList() {

        books.add(new Book("Harry Potter", "JK Rowling", "Fantasy"));
        books.add(new Book("Lord Of The Rings", "J.R.R Tolkien", "Fantasy"));
        books.add(new Book("Moby Dick", "Herman Melville", "Drama"));
        books.add(new Book("Hamlet", "William Shakespeare", "Tragedy"));
        books.add(new Book("Huckleberry Finn", "Mark Twain", "Adventure"));
        books.add(new Book("Pride and Prejudice", "Jane Austen", "Periodical"));
        books.add(new Book("The Iliad", "Homer", "Epic"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "Gothic"));

        FileUtility.saveObject("books.ser", books);
    }
}
