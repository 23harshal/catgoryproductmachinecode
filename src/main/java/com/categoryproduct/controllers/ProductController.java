package com.categoryproduct.controllers;

import com.categoryproduct.entities.Product;
import com.categoryproduct.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable){
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(Product product){
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
       Product product = productService.getProductById(id);
       if(product != null) {
           return ResponseEntity.ok(product);
       }
       return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails){
        Product existingProduct = productService.getProductById(id);
        if(existingProduct != null) {
            existingProduct.setProductName(productDetails.getProductName());
            existingProduct.setPrice(productDetails.getPrice());
            return ResponseEntity.ok(productService.saveProduct(existingProduct));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        Product product = productService.getProductById(id);
        if(product != null) {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<Product>> getAllProductsByCategoryId(@PathVariable Long categoryId, Pageable pageable){
        return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId, pageable));
    }





}
