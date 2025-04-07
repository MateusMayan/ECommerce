package com.mayan.ecommerce.dtos;

import com.mayan.ecommerce.entities.Product;

public class ProductMinDTO {
    private Long id;
    private String name;
    private String imgUrl;
    private Double price;

    public ProductMinDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.imgUrl = entity.getImgUrl();
        this.price = entity.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Double getPrice() {
        return price;
    }
}
