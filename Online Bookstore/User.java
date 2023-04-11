import java.util.ArrayList;
import java.time.LocalDate;

public class User {
    private String name;
    private int password;
    private ArrayList<Book> book;
    private ArrayList<String> paymentHistory;

    public User(String name, int password) {
        this.name = name;
        this.password = password;
        this.book = new ArrayList<>();
        this.paymentHistory = new ArrayList<>();

    }

    public String getName() {
        return this.name;
    }

    public int getPassword() {
        return this.password;
    }

    public ArrayList<Book> getBookList() {
        return this.book;
    }

    public ArrayList<String> getPaymentHistoryList() {
        return this.paymentHistory;
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public void addBookToCart(Book book) {
        this.book.add(book);
    }

    public void removeBookFromCart(int index) {
        this.book.remove(index - 1);
    }
    
    public int priceOfCart() {
        int fullPrice = 0;
        for (Book book : this.book) {
            fullPrice += book.getPrice();
        }
        return fullPrice;
    }

    public void clearBookList() {
        this.book.clear();
    }

    public void addPaymentHistory() {
        String year = String.valueOf(getCurrentDate().getYear());
        String month = String.valueOf(getCurrentDate().getMonthValue());
        String day = String.valueOf(getCurrentDate().getDayOfMonth());
        String paymentHistory = "This is your payment history\n" + day + "/" + month + "/" + year
                + "\n--------------------------------------------------------\n";
        for (int i = 0; i < this.book.size(); i++) {
            paymentHistory += i + 1 + ".\n" + this.book.get(i) + "\n";
        }
        String totalPrice = String.valueOf(priceOfCart());
        paymentHistory += "------------------------------------------------\nTotal Price " + totalPrice + " euro\n";

        this.paymentHistory.add(paymentHistory);
    }

    public void printPaymentHistory() {
        for (String paymentHistory : this.paymentHistory) {
            System.out.println(paymentHistory);
        }
    }

    public String toString() {
        String cart = "";
        for (int i = 0; i < this.book.size(); i++) {
            cart += i + 1 + ".\n" + this.book.get(i) + "\n";
        }
        return cart + "\n------------------------\nTotal Price: " + priceOfCart() + " euro";
    }
}
