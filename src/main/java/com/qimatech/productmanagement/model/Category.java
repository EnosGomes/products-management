package com.qimatech.productmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Category {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String path;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(){

    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;

    }
}
