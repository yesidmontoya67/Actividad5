package co.edu.ff.orders.product.ecxeptions;

import co.edu.ff.orders.product.domain.Product;
import co.edu.ff.orders.user.domain.Username;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value(staticConstructor = "of")
public class ProductDoesNotExists extends ProductException {
    Product product;

    private ProductDoesNotExists(Product product) {
        super(String.format("Product %s don´t exist", product.getName().getValue()));
        this.product = product;
    }
}
