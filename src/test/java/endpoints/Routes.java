package endpoints;

public class Routes {

    public static final String BASE_URL = "https://simple-books-api.click";

    // Public endpoints
    public static final String STATUS = "/status";
    public static final String BOOKS = "/books";
    public static final String SINGLE_BOOK = "/books/{bookId}";

    // Authentication
    public static final String API_CLIENT = "/api-clients";

    // Orders
    public static final String ORDERS = "/orders";
    public static final String SINGLE_ORDER = "/orders/{orderId}";
}