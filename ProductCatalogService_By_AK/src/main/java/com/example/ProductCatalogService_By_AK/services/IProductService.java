package com.example.ProductCatalogService_By_AK.services;

import com.example.ProductCatalogService_By_AK.models.Product;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(Product product);

    Product replaceProduct(Long id, Product product);
}
