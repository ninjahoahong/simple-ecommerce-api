package com.ninjahoahong.bookingsolution.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Set;

@Entity
public class Product {

    @Id
    private String code;
    private String name;
    private Float price;
    private Integer quantity;
    private Set<String> categories;
    private String brand;
    private String description;
    private String imgUrl;

    public Product() {
    }

    public Product(
            String code, String name, Float price, Integer quantity, Set<String> categories,
            String brand, String description, String imgUrl) {

        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categories = categories;
        this.brand = brand;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setPrice(Float price) {

        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
