package co.edu.ff.orders.product.domain;

import co.edu.ff.orders.product.ecxeptions.ProductException;

public interface ProductOperation {
    Product value();
    ProductException failure();
    Boolean isValid();
}
