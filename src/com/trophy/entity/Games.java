package com.trophy.entity;

public class Games {
    private int id_game;
    private Category category;
    private String name,description,img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    private Float rate;

    public Games(int id_game, Category category, String name, String description, Float rate) {
        this.id_game = id_game;
        this.category = category;
        this.name = name;
        this.description = description;
        this.rate = rate;
    }

    public Games(Category category, String name, String description, Float rate) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.rate = rate;
    }

    public Games() {
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Games{" +
                "id_game=" + id_game +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                '}';
    }
}
