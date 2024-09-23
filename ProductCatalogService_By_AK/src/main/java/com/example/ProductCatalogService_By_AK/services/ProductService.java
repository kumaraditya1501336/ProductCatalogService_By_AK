package com.example.ProductCatalogService_By_AK.services;

import com.example.ProductCatalogService_By_AK.clients.FakeStoreApiClient;
import com.example.ProductCatalogService_By_AK.dtos.FakeStoreProductDto;
import com.example.ProductCatalogService_By_AK.models.Category;
import com.example.ProductCatalogService_By_AK.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fkps")
public class ProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

//    @Autowired
//    private FakeStoreApiClient fakeStoreApiClient;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForEntity(
                "https://fakestoreapi.com/products", FakeStoreProductDto[].class).getBody();

        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(from(fakeStoreProductDto));
        }

        return products;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setAmount(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());

        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        product.setImageUrl(fakeStoreProductDto.getImage());

        return product;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    private FakeStoreProductDto from (Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getAmount());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());

        if (product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }

        return fakeStoreProductDto;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
