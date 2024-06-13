package com.qimatech.productmanagement.controller;


import com.qimatech.productmanagement.model.Product;
import com.qimatech.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("/screen")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> fetchAllProducts() {
        return productService.findAll();
    }


    @GetMapping("/product/search")
    public ResponseEntity searchProduct (Pageable pageable) {
        return ResponseEntity.ok(productService.readProducts(pageable));
    }

    @GetMapping("/product/search/filter")
    public ResponseEntity searchProductWithFilter (@RequestParam("filter") String filter, Pageable pageable) {
        return ResponseEntity.ok(productService.filterProducts(filter, pageable));
    }
}
