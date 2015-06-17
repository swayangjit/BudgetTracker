package com.skytopper.budgettracker.model;

/**
 * Created by swayangjit on 6/11/2015.
 */
public class CategorySum {
    private String categoryName;
    private double totalNoofUnit;
    private double totalValue;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getTotalNoofUnit() {
        return totalNoofUnit;
    }

    public void setTotalNoofUnit(double totalNoofUnit) {
        this.totalNoofUnit = totalNoofUnit;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }


}
