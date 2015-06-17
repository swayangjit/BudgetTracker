package com.skytopper.budgettracker.model;

/**
 * Created by swayangjiton 4/25/2015.
 */
public class Category {

    Long id;
    String categoryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
