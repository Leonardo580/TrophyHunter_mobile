/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.entity;

/**
 *
 * @author rihab bns
 */
public class Product {
    private int idProduct,quantity;
    private String prodName,description,image;
    private float price,discount;
    private Category category;

    public Product(int idProduct, int quantity, String prodName, String description, String image, float price, float discount, Category category) {
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.prodName = prodName;
        this.description = description;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.category = category;
    }

    public Product(int quantity, String prodName, String description, String image, float price, float discount, Category category) {
        this.quantity = quantity;
        this.prodName = prodName;
        this.description = description;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.category = category;
    }

    public Product(String prodName, String description) {
        this.prodName = prodName;
        this.description = description;
    }

    
    public int getIdProduct() {
        return idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProdName() {
        return prodName;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public float getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" + "idProduct=" + idProduct + ", quantity=" + quantity + ", prodName=" + prodName + ", description=" + description + ", image=" + image + ", price=" + price + ", discount=" + discount + ", category=" + category + '}';
    }
    
}
