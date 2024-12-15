package com.categoryproduct.services;

import com.categoryproduct.entities.Product;
import com.categoryproduct.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProducts(Pageable pageable){
        return productRepository.findAll(pageable);
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id , Product productDetails){
        Product product = productRepository.findById(id).orElse(null);

        if(product != null){
            product.setProductName(productDetails.getProductName());
            product.setCategory(productDetails.getCategory());
            product.setPrice(productDetails.getPrice());
            productRepository.save(product);
        }
        return null;
    }

    public Page<Product> getProductsByCategoryId(Long categoryId , Pageable pageable){
        return  productRepository.findByCategoryId(categoryId, pageable);
    }


}
