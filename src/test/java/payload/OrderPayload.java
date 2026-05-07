package payload;

public class OrderPayload {

    private int bookId;
    private String customerName;

    public OrderPayload(int bookId, String customerName) {
        this.bookId = bookId;
        this.customerName = customerName;
    }

    public int getBookId() {
        return bookId;
    }

    public String getCustomerName() {
        return customerName;
    }
}