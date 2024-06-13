package com.qimatech.productmanagement.service;


import com.qimatech.productmanagement.model.Product;
import com.qimatech.productmanagement.model.response.ProductPaginatedResponse;
import com.qimatech.productmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public ProductPaginatedResponse readProducts(Pageable pageable) {
        Page<Product> books = productRepository.findAll(pageable);
        return ProductPaginatedResponse.builder()
                .numberOfProducts(books.getTotalElements()).numberOfPages(books.getTotalPages())
                .products(books.getContent())
                .build();
    }

    public ProductPaginatedResponse filterProducts(String name, Pageable pageable) {

        Page<Product> books = productRepository.findAllByNameContains(name, pageable);
        return ProductPaginatedResponse.builder()
                .numberOfProducts(books.getTotalElements()).numberOfPages(books.getTotalPages())
                .products(books.getContent())
                .build();
    }

    public Product save(Product product) {

        //setting the category path of product
        product.setCategoryPath(product.getCategory().getName()+"/"+product.getName());
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
