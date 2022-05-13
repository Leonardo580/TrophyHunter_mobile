package com.trophy.entity;

import java.util.Objects;

public class Category {
    private int id_category;
    private String category;

    public Category() {
    }

    public Category(String category) {
        this.category = category;
    }

    public Category(int id_category, String category) {
        this.id_category = id_category;
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id_category;
        hash = 23 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (this.id_category != other.id_category) {
            return false;
        }
        return Objects.equals(this.category, other.category);
    }
}
