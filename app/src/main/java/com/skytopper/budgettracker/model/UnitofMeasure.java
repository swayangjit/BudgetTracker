package com.skytopper.budgettracker.model;

/**
 * Created by swayangjit on 4/25/2015.
 */
public class UnitofMeasure {

    private String unitName;
    private String unitDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    Long id;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }
}
