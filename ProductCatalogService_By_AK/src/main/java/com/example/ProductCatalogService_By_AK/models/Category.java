package com.example.ProductCatalogService_By_AK.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Category extends BaseModel {

    private String name;

    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;
}
