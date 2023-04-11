import java.util.ArrayList;

public class Book {
    private String name;
    private String author;
    private int price;
    private ArrayList<Book> availableBook;
    private int numberOfBookCopy;

    public Book(String name, String author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.availableBook = new ArrayList<>();
        this.numberOfBookCopy = 0;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getPrice() {
        return this.price;
    }

    public int getNumberOfCopy() {
        return this.numberOfBookCopy;
    }

    public void addBook(int howManyTimeToAdd, Book book) {
        for (int i = 0; i < howManyTimeToAdd; i++) {
            this.availableBook.add(book);
        }
    }

    public int setNumberOfBookCopy(int numberOfCopy) {
        return this.numberOfBookCopy = numberOfCopy;
    }

    public int decreaseNumberOfBookCopy() {
        return this.numberOfBookCopy--;
    }

    public int increaseNumberOfBookCopy() {
        return this.numberOfBookCopy++;
    }

    public String printBook() {
        if (this.numberOfBookCopy == 0) {
            return "Name: " + this.name + "\nAuthor: " + this.author + "\nPrice :" + this.price + " euro" +
                    "\nAvailable: OUT OF THE STOCK !";
        }
        return "Name: " + this.name + "\nAuthor: " + this.author + "\nPrice :" + this.price + " euro" +
                "\nAvailable: " + this.numberOfBookCopy;

    }

    public String toString() {
        return "Name: " + this.name + "\nAuthor: " + this.author + "\nPrice :" + this.price + " euro";
                
    }
}
