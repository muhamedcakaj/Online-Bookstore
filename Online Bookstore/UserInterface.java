import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
    private Scanner scan;
    private ArrayList<Book> bookList;
    private ArrayList<User> user;

    public UserInterface(Scanner scan) {
        this.scan = scan;
        this.bookList = new ArrayList<>();
        this.user = new ArrayList<>();
    }

    public void start() {
        while (true) {
            System.out.println("->Administrator\n->User\n->Exit");
            String option = this.scan.nextLine();
            if (option.equalsIgnoreCase("Exit")) {
                break;
            } else if (option.equalsIgnoreCase("Administrator")) {
                administrator();
            } else if (option.equalsIgnoreCase("User")) {
                user();
            }
        }
    }

    public void administrator() {
        System.out.println("Type the name");
        String name = this.scan.nextLine();
        System.out.println("Type the password");
        int password = Integer.valueOf(this.scan.nextLine());
        if (name.equalsIgnoreCase("Admin") && password == 1234) {
            while (true) {
                System.out.println("->1.Add Book\n->2.Remove Book\n->3.Exit");
                int option = Integer.valueOf(this.scan.nextLine());
                if (option == 3) {
                    break;
                } else if (option == 1) {
                    administratorAddBooks();
                } else if (option == 2) {
                    administratorRemoveBooks();
                }

            }
        } else {
            System.out.println("Name or password is wrong");
        }

    }

    public void administratorAddBooks() {
        System.out.println("Write the name of the book");
        String name = this.scan.nextLine();
        System.out.println("Write the author of the book");
        String author = this.scan.nextLine();
        System.out.println("Write the price of the book");
        int price = Integer.valueOf(this.scan.nextLine());
        this.bookList.add(new Book(name, author, price));
        System.out.println("How many copy's of book do you want");
        int bookCopy = Integer.valueOf(this.scan.nextLine());
        int bookIndex = getBookIndex(name);
        this.bookList.get(bookIndex).addBook(bookCopy, this.bookList.get(bookIndex));
        this.bookList.get(bookIndex).setNumberOfBookCopy(bookCopy);
        System.out.println("Book added succesfully");
    }

    public void administratorRemoveBooks() {
        if (this.bookList.isEmpty()) {
            System.out.println("There are no book's at the moment");
        } else {

            int index = 1;
            for (Book book : this.bookList) {
                System.out.println(index + ". " + book.getName());
                index++;
            }
            System.out.println("Write the number of the book you want to remove");
            int number = Integer.valueOf(this.scan.nextLine());
            this.bookList.remove(number - 1);
            System.out.println("Book removed succesfully");
        }
    }

    public void user() {
        while (true) {
            System.out.println("->Log In\n->Sing Up\n->Exit");
            String option = this.scan.nextLine();
            if (option.equalsIgnoreCase("Exit")) {
                break;
            } else if (option.equalsIgnoreCase("Log In")) {
                System.out.println("Write the name");
                String name = this.scan.nextLine();
                System.out.println("Write the password");
                int password = Integer.valueOf(this.scan.nextLine());
                if (userLogIn(name, password)) {
                    int userIndex = userIndex(name);
                    System.out.println("You have succesfully loged in");
                    while (true) {
                        System.out.println("->1.Search book by name\n->2.Add book to shopping cart" +
                                "\n->3.View Shopping Cart\n->4.Payment History\n->5.Checkout\n->6.Exit");
                        int option2 = Integer.valueOf(this.scan.nextLine());
                        if (option2 == 6) {
                            break;
                        } else if (option2 == 1) {
                            searchBook(userIndex);
                        } else if (option2 == 2) {
                            addBookToShoppingCart(userIndex);
                        } else if (option2 == 3) {
                            viewShoppingCart(userIndex);
                        } else if (option2 == 4) {
                            paymentHistory(userIndex);
                        } else if (option2 == 5) {
                            checkout(userIndex);
                        }
                    }
                } else {
                    System.out.println("The name or password is wrong check again");
                }
            } else if (option.equalsIgnoreCase("Sing Up")) {
                userSingUp();
            }
        }
    }

    public boolean userLogIn(String name, int password) {
        for (User user : this.user) {
            if (user.getName().equals(name) && user.getPassword() == password) {
                return true;
            }
        }
        return false;
    }

    public void userSingUp() {
        System.out.println("Write your name");
        String name = this.scan.nextLine();
        System.out.println("Write your password");
        int password = Integer.valueOf(this.scan.nextLine());
        this.user.add(new User(name, password));
        System.out.println("You have succesfully sing up");

    }

    public void searchBook(int index) {
        if (this.bookList.isEmpty()) {
            System.out.println("There are no books available at the moment");
        } else {
            while (true) {
                System.out.println("Write the name of the Book(Press enter to get back)");
                String option = this.scan.nextLine();
                if (option.equals("")) {
                    break;
                } else if (isBookAvailable(option)) {
                    int bookIndex = getBookIndex(option);
                    System.out.println("\n" + this.bookList.get(bookIndex).printBook());
                    System.out.println("------------------------------------"
                            + "\nPress P to purchase the book(Press enter to get back)");
                    String option2 = this.scan.nextLine();
                    if (option2.equals("")) {
                        break;
                    } else if (option2.equalsIgnoreCase("P")) {
                        if (this.bookList.get(bookIndex).getNumberOfCopy() <= 0) {
                            System.out.println("You can't purchase this product because is OUT OF THE STOCK");
                        } else {
                            this.user.get(index).addBookToCart(this.bookList.get(bookIndex));
                            this.bookList.get(bookIndex).decreaseNumberOfBookCopy();
                            System.out.println("Book added to cart succesfully");
                        }
                    }
                } else {
                    System.out.println("No result found");
                }
            }
        }
    }

    public void addBookToShoppingCart(int index) {
        if (this.bookList.isEmpty()) {
            System.out.println("There are no books available at the moment");
        } else {
            int j = 0;
            if (j == 0) {
                System.out.println("These are the book available");
                for (int i = 0; i < this.bookList.size(); i++) {
                    System.out.println(i + 1 + ".\n" + this.bookList.get(i).printBook());
                }
                j++;
            }
            while (true) {

                System.out.println("\nChoose the book you want to add to cart by number(Press enter to get back)");
                String chooseBook = this.scan.nextLine();
                if (chooseBook.equals("")) {
                    break;
                }
                int convertStringtoInt = Integer.valueOf(chooseBook);
                if (convertStringtoInt <= this.bookList.size()) {
                    if (this.bookList.get(convertStringtoInt - 1).getNumberOfCopy() <= 0) {
                        System.out.println("You can't purchase this product because is OUT OF THE STOCK");
                    } else {

                        this.user.get(index).addBookToCart(this.bookList.get(convertStringtoInt - 1));
                        this.bookList.get(convertStringtoInt - 1).decreaseNumberOfBookCopy();
                    }
                } else {
                    System.out.println("Type the right number!");
                }
            }
        }
    }

    public void viewShoppingCart(int index) {

        if (this.user.get(index).getBookList().isEmpty()) {
            System.out.println("You didn't add anything to your cart");
        } else {
            while (true) {
                System.out.println("This is your cart at the moment");
                System.out.println(this.user.get(index));
                System.out.println("Press R if you want to remove a book from cart(Press enter to get back)");
                String option = this.scan.nextLine();
                if (option.equals("")) {
                    break;
                } else if (option.equalsIgnoreCase("R")) {
                    System.out.println("Type the number of the book you want to remove");
                    int removeIndex = Integer.valueOf(this.scan.nextLine());
                    if (removeIndex <= user.get(index).getBookList().size()) {
                        int bookIndex = getBookIndex(
                                this.user.get(index).getBookList().get(removeIndex - 1).getName());
                        this.bookList.get(bookIndex).increaseNumberOfBookCopy();
                        this.user.get(index).removeBookFromCart(removeIndex);
                        if (this.user.get(index).getBookList().isEmpty()) {
                            break;
                        }
                    } else {
                        System.out.println("Type the right number!");
                    }
                }
            }
        }

    }

    public void paymentHistory(int index) {
        if (this.user.get(index).getPaymentHistoryList().isEmpty()) {
            System.out.println("You don't have any payment history");
        } else {
            while (true) {
                this.user.get(index).printPaymentHistory();
                System.out.println("(Press enter to get back)");
                String enter = this.scan.nextLine();
                if (enter.equals("")) {
                    break;
                }
            }
        }
    }

    public void checkout(int index) {
        if (this.user.get(index).getBookList().isEmpty()) {
            System.out.println("You didn't add anything to your cart");
        } else {
            int j = 0;
            if (j == 0) {
                System.out.println(user.get(index));
                j++;
            }
            while (true) {
                System.out.println("Are you sure you want to checkout\n->Yes\n->No");
                String option = this.scan.nextLine();
                if (option.equalsIgnoreCase("No")) {
                    break;
                } else if (option.equalsIgnoreCase("Yes")) {
                    user.get(index).addPaymentHistory();
                    user.get(index).clearBookList();
                    System.out.println("Thank you for your purchase see you next time!...");
                    break;
                }
            }
        }
    }

    public int userIndex(String name) {
        int index = 0;
        for (int i = 0; i < this.user.size(); i++) {
            if (this.user.get(i).getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }

    public int getBookIndex(String name) {
        int index = 0;
        for (int i = 0; i < this.bookList.size(); i++) {
            if (this.bookList.get(i).getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }

    public boolean isBookAvailable(String name) {

        for (Book book : this.bookList) {

            if (book.getName().equals(name)) {
                return true;

            }
        }

        return false;
    }

}