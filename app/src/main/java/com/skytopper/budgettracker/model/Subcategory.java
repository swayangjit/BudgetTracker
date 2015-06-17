package com.skytopper.budgettracker.model;

/**
 * Created by swayangjit on 4/25/2015.
 */
public class Subcategory {
    Long id;
    String categoryid;
    String subcategoryName;

    public Long getId() {
        return id;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }
}
