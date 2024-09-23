package com.example.ProductCatalogService_By_AK.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {
    private Long id;

    private String title;

    private String description;

    private String category;

    private Double price;

    private String image;

}
