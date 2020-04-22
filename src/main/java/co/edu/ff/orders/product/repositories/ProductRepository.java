package co.edu.ff.orders.product.repositories;

import co.edu.ff.orders.product.domain.Product;
import co.edu.ff.orders.product.domain.ProductId;
import co.edu.ff.orders.product.domain.ProductOperation;
import co.edu.ff.orders.product.domain.ProductoperationRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    ProductOperation insertOne(ProductoperationRequest productoperationRequest);
    ProductOperation findById (ProductId productId);
    List<Product> findAll();
    ProductOperation updateOne(ProductId productId, ProductoperationRequest productoperationRequest);
    ProductOperation deleteOne(ProductId productId);
}
