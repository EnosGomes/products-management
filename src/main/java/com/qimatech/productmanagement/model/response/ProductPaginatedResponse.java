package com.qimatech.productmanagement.model.response;


import com.qimatech.productmanagement.model.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductPaginatedResponse {
    private List<Product> products;
    private Long numberOfProducts;
    private int numberOfPages;

}
