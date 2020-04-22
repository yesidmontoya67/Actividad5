package co.edu.ff.orders.product.controllers;

import co.edu.ff.orders.product.domain.*;
import co.edu.ff.orders.product.ecxeptions.ProductDoesNotExists;
import co.edu.ff.orders.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductContoroller {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductOperation> createProduct(@RequestBody ProductoperationRequest productBody){
        ProductOperation productOperation= productService.createProduct(productBody);
        if (productOperation.isValid()){
            return ResponseEntity.ok(ProductOperationSuccess.of(productOperation.value()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ProductOperationFailure.of(productOperation.failure()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOperation> findProductById(@PathVariable ProductId id){
        ProductOperation productOperation= productService.findProductById(id);
        if (productOperation.isValid()){
            return ResponseEntity.ok(ProductOperationSuccess.of(productOperation.value()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ProductOperationFailure.of(productOperation.failure()));
    }

    @GetMapping("/")
    public List<Product> findAllProducts(){
        return productService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOperation> updateProducte(@PathVariable ProductId id, @RequestBody ProductoperationRequest productoperationRequest){
        ProductOperation productOperation= productService.updateProduct(id,productoperationRequest);
        if (productOperation.isValid()){
            return ResponseEntity.ok(ProductOperationSuccess.of(productOperation.value()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ProductOperationFailure.of(productOperation.failure()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductOperation> deleteProduct(@PathVariable ProductId id){
        ProductOperation productOperation= productService.deleteProduct(id);
        if (productOperation.isValid()){
            return ResponseEntity.ok(ProductOperationSuccess.of(productOperation.value()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ProductOperationFailure.of(productOperation.failure()));
    }


}
