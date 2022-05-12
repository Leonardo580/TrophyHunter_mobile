package com.trophy.entity;

import java.util.Objects;

public class Category {
    private int idCategory;
    private String category;

    public Category() {
    }

    public Category(String category) {
        this.category = category;
    }

    public Category(int idCategory, String category) {
        this.idCategory = idCategory;
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }

    public int getId_category() {
        return idCategory;
    }

    public void setId_category(int idCategory) {
        this.idCategory = idCategory;
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
        hash = 23 * hash + this.idCategory;
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
        if (this.idCategory != other.idCategory) {
            return false;
        }
        return Objects.equals(this.category, other.category);
    }
}
