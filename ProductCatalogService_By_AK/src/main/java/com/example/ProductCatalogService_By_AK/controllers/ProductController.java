package com.example.ProductCatalogService_By_AK.controllers;

import com.example.ProductCatalogService_By_AK.dtos.CategoryDto;
import com.example.ProductCatalogService_By_AK.dtos.ProductDto;
import com.example.ProductCatalogService_By_AK.models.Category;
import com.example.ProductCatalogService_By_AK.models.Product;
import com.example.ProductCatalogService_By_AK.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        List<Product> products = iProductService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();

        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
                ProductDto productDto = from (product);
                productDtos.add(productDto);
            }

            return productDtos;
        }

        return null;
    }

    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setAmount(product.getAmount());
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setIsPrimeSpecific(product.getIsPrimeSpecific());

        if (product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());

            productDto.setCategory(categoryDto);
        }

        return productDto;
    }

    private Product from (ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setAmount(productDto.getAmount());
        product.setId(productDto.getId());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        product.setIsPrimeSpecific(productDto.getIsPrimeSpecific());

        if (productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            category.setId(productDto.getCategory().getId());

            product.setCategory(category);
        }

        return product;
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleExceptions(Exception exception) {
        return new ResponseEntity<>("Kuch Toh Phata Hai", HttpStatus.BAD_REQUEST);
    }
}
