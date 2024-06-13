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

            Category category1 = new Category();
            category1.setName("SPORTS");
            category1.setDescription("Sports in general");

            Category category2 = new Category();
            category2.setName("ELETRONICS");
            category2.setDescription("Eletronics in general");

            categoryRepository.save(category1);
            categoryRepository.save(category2);

            Product product1 = new Product();
            product1.setName("TV LG OLED 43 inches");
            product1.setCategory(category1);
            product1.setDescription("Tv to living room");
            product1.setPrice(1000.0);
            product1.setAvailable(true);
            product1.setCategoryPath("ELETRONICS/TVS/LG OLED 43");

            Product product2 = new Product();
            product2.setName("TV Samsung LG OLED 43 inches");
            product2.setCategory(category2);
            product2.setDescription("Tv to living room");
            product2.setPrice(1000.0);
            product2.setAvailable(false);
            product2.setCategoryPath("ELETRONICS/TVS/SAMSUNG 43");

            Product product3 = new Product();
            product3.setName("Home theather JBL");
            product3.setCategory(category1);
            product3.setDescription("Tv to living room");
            product3.setPrice(1000.0);
            product3.setAvailable(false);
            product3.setCategoryPath("ELETRONICS/TVS/HOME JBL");

            Product product4 = new Product();
            product4.setName("lenovo");
            product4.setCategory(category2);
            product4.setDescription("laptop to work");
            product4.setPrice(11000.0);
            product4.setAvailable(true);
            product4.setCategoryPath("ELETRONICS/computers/lenovo");

            Product product5 = new Product();
            product5.setName("acer");
            product5.setCategory(category2);
            product5.setDescription("laptop to work");
            product5.setPrice(2002.66);
            product5.setAvailable(true);
            product5.setCategoryPath("ELETRONICS/computers/acer");

            Product product6 = new Product();
            product6.setName("dell");
            product6.setCategory(category2);
            product6.setDescription("laptop to work");
            product6.setPrice(221.98);
            product6.setAvailable(true);
            product6.setCategoryPath("ELETRONICS/computers/dell");

            Product product7 = new Product();
            product7.setName("hp");
            product7.setCategory(category2);
            product7.setDescription("laptop to work");
            product7.setPrice(522.33);
            product7.setAvailable(true);
            product7.setCategoryPath("ELETRONICS/computers/hp");

            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);
            productRepository.save(product6);
            productRepository.save(product7);

        };
    }

}
