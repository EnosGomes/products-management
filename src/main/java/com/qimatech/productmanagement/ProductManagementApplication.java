package com.qimatech.productmanagement;

import com.qimatech.productmanagement.model.Category;
import com.qimatech.productmanagement.model.Product;
import com.qimatech.productmanagement.repository.CategoryRepository;
import com.qimatech.productmanagement.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@SpringBootApplication
public class ProductManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductManagementApplication.class, args);
    }

    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, CategoryRepository categoryRepository) {
        return args -> {


            categoryRepository.save(new Category("FURNITURE","furniture in general"));
            categoryRepository.save(new Category("ELETRONICS","Eletronics in general"));
            categoryRepository.save(new Category("BEAUTY","Beauty in general"));

            Category category1 = new Category();
            category1.setName("SPORTS");
            category1.setDescription("Sports in general");

            categoryRepository.save(category1);

            Product product1 = new Product();
            product1.setName("TV LG OLED 43 inches");
            product1.setCategory(category1);
            product1.setDescription("Tv to living room");
            product1.setPrice(1000.0);
            product1.setAvailable(true);
            product1.setCategoryPath("ELETRONICS/TVS/LG OLED 43");

            productRepository.save(product1);

        };
    }

}
