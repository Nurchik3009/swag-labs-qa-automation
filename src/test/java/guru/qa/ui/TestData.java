package guru.qa.ui;

public class TestData {

    private TestData() {
    }

    // Credentials
    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_OUT_USER = "locked_out_user";
    public static final String PASSWORD = "secret_sauce";
    public static final String INVALID_PASSWORD = "wrong_password";

    // Login errors
    public static final String LOCKED_OUT_ERROR =
            "Epic sadface: Sorry, this user has been locked out.";

    public static final String INVALID_CREDENTIALS_ERROR =
            "Epic sadface: Username and password do not match any user in this service";

    // Products
    public static final String PRODUCTS_PAGE_TITLE = "Products";
    public static final int EXPECTED_PRODUCTS_COUNT = 6;
    public static final String CHEAPEST_PRODUCT_PRICE = "$7.99";

    // Cart
    public static final String BACKPACK_NAME = "Sauce Labs Backpack";
    public static final String ONE_PRODUCT_IN_CART = "1";

    // Customer information
    public static final String CUSTOMER_FIRST_NAME = "Ainur";
    public static final String CUSTOMER_LAST_NAME = "Kaliakbarova";
    public static final String CUSTOMER_POSTAL_CODE = "050000";

    // Checkout
    public static final String ORDER_SUCCESS_MESSAGE =
            "Thank you for your order!";
}