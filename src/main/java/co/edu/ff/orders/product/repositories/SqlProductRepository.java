package co.edu.ff.orders.product.repositories;

import co.edu.ff.orders.product.domain.*;
import co.edu.ff.orders.product.ecxeptions.ProductDoesNotExists;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SqlProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;


    public SqlProductRepository(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    private final RowMapper<Product> rowMapper = (resultSet, i) -> {
        Long id1 = resultSet.getLong("ID");
        Name name = Name.of(resultSet.getString("NAME"));
        Description description = Description.of(resultSet.getString("DESCRIPTION"));
        BasePrice basePrice= BasePrice.of(resultSet.getBigDecimal("BASE_PRICE"));
        TaxRate taxRate= TaxRate.of(resultSet.getBigDecimal("TAX_RATE"));
        ProductStatus productStatus= ProductStatus.valueOf(resultSet.getString("STATUS"));
        InventoryQueantity inventoryQueantity= InventoryQueantity.of(resultSet.getInt("INVENTORY_QUANTITY"));

        return Product.of(id1, name, description,basePrice,taxRate,productStatus,inventoryQueantity);
    };

    @Override
    public ProductOperation insertOne(ProductoperationRequest productoperationRequest) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("NAME", productoperationRequest.getName().getValue());
        parameters.put("DESCRIPTION", productoperationRequest.getDescription().getValue());
        parameters.put("BASE_PRICE", productoperationRequest.getBasePrice().getValue());
        parameters.put("TAX_RATE", productoperationRequest.getTaxRate().getValue());
        parameters.put("PRODUCT_STATUS", productoperationRequest.getProductStatus());
        parameters.put("INVENTORY_QUANTITY", productoperationRequest.getInventoryQueantity().getValue());



        Number number = simpleJdbcInsert.executeAndReturnKey(parameters);
        long id = number.longValue();
        return ProductOperationSuccess.of(Product.of(
                id,
                productoperationRequest.getName(),
                productoperationRequest.getDescription(),
                productoperationRequest.getBasePrice(),
                productoperationRequest.getTaxRate(),
                productoperationRequest.getProductStatus(),
                productoperationRequest.getInventoryQueantity()
                )

        );

    }

    @Override
    public ProductOperation findById(ProductId productId) {
        String SQL = "SELECT ID, NAME, DESCRIPTION, BASE_PRICE, TAX_RATE, STATUS, INVENTORY_QUANTITY FROM PRODUCTS WHERE ID = ?";
        Object[] objects = {productId.getValue()};

        try {
            Product product = jdbcTemplate.queryForObject(SQL, objects, rowMapper);
            return ProductOperationSuccess.of(product);
        } catch (ProductDoesNotExists e) {
            return ProductOperationFailure.of(e);
        }
    }

    @Override
    public List<Product> findAll() {
        String SQL = "SELECT ID, NAME, DESCRIPTION, BASE_PRICE, TAX_RATE, STATUS, INVENTORY_QUANTITY FROM PRODUCTS";

        try {
            List<Product> products = jdbcTemplate.query(SQL,rowMapper);
            return products;
        } catch (ProductDoesNotExists e) {
            ProductOperationFailure.of(e);
            return null;
        }
    }

    @Override
    public ProductOperation updateOne(ProductId productId, ProductoperationRequest productoperationRequest) {
        String SQL = "UPDATE PRODUCTS SET NAME= ?, DESCRIPTION = ?, BASE_PRICE = ?, TAX_RATE= ?, STATUS= ?, INVENTORY_QUANTITY= ?  WHERE ID = ?";
        Object[] objects = {productoperationRequest.getName(),
                productoperationRequest.getDescription(),
                productoperationRequest.getBasePrice(),
                productoperationRequest.getTaxRate(),
                productoperationRequest.getProductStatus(),
                productoperationRequest.getInventoryQueantity(),
                productId.getValue()};

        try {
            Product product = jdbcTemplate.queryForObject(SQL, objects, rowMapper);
            return ProductOperationSuccess.of(product);
        } catch (ProductDoesNotExists e) {
            return ProductOperationFailure.of(e);
        }

    }

    @Override
    public ProductOperation deleteOne(ProductId productId) {
        String SQL = "DELETE FROM PRODUCTS WHERE ID = ?";
        Object[] objects = {productId.getValue()};

        try {
            Product product = jdbcTemplate.queryForObject(SQL, objects, rowMapper);
            return ProductOperationSuccess.of(product);
        } catch (ProductDoesNotExists e) {
            return ProductOperationFailure.of(e);
        }
    }
}
