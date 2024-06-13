package com.qimatech.productmanagement.controller;

import com.qimatech.productmanagement.model.Category;
import com.qimatech.productmanagement.model.Product;
import com.qimatech.productmanagement.repository.CategoryRepository;
import com.qimatech.productmanagement.repository.ProductRepository;
import com.qimatech.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductThymeleafController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String viewHomePage(Model model) {
        List<Product> produtos = productService.findAll();
        System.out.println(produtos);
        model.addAttribute("produtos", produtos );
        return "index";
    }

    @GetMapping("/productsPaginated")
    public String getAll(Model model, @RequestParam(required = false) String keyword,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "6") int size,
                         @RequestParam(defaultValue = "id,asc") String[] sort) {
        try {
            List<Product> products;

            String sortField = sort[0];
            String sortDirection = sort[1];

            Sort.Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort.Order order = new Sort.Order(direction, sortField);

            Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));

            Page<Product> pageProducts;
            if (keyword == null) {
                pageProducts = productRepository.findAll(pageable);
            } else {
                pageProducts = productRepository.findByNameContainingIgnoreCase(keyword, pageable);
                model.addAttribute("keyword", keyword);
            }

            products = pageProducts.getContent();

            model.addAttribute("products", products);
            model.addAttribute("currentPage", pageProducts.getNumber() + 1);
            model.addAttribute("totalItems", pageProducts.getTotalElements());
            model.addAttribute("totalPages", pageProducts.getTotalPages());
            model.addAttribute("pageSize", size);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDirection", sortDirection);
            model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "products";
    }

    @GetMapping("/addnew")
    public String addNewProduct(Model model) {
        List<Category> categories = categoryRepository.findAll();
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "newproduct";
    }

    @PostMapping("/save")
    public String saveEmployee( @ModelAttribute("employee") @Valid Product product) {
        productService.save(product);
        return "redirect:/screen/inicio";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        List<Category> categories = categoryRepository.findAll();
        Optional<Product> product = Optional.ofNullable(productService.findById(id));
        model.addAttribute("product", product.get());
        model.addAttribute("categories", categories);
        return "updateproduct";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        productService.deleteProductById(id);
        return "redirect:/screen/inicio";

    }
}
