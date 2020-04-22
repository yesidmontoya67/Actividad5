package co.edu.ff.orders.product.ecxeptions;

public abstract class ProductException extends RuntimeException{
    public ProductException(String message) {
        super(message);
    }
}
