package com.qimatech.productmanagement.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min=2, max=30)
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min=2, max=30)
    private String description;

    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "Quantity must be a numeric value")
    @NotNull
    private Double price;

    @NotEmpty(message = "Category Path cannot be empty")
    private String categoryPath;

    @NotNull
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    @NotNull
    private Category category;


    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, String description, Double price, String categoryPath, boolean available, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryPath = categoryPath;
        this.available = available;
        this.category = category;
    }
}
