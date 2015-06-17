package com.skytopper.budgettracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.skytopper.budgettracker.model.Category;
import com.skytopper.budgettracker.model.CategorySum;
import com.skytopper.budgettracker.model.Company;
import com.skytopper.budgettracker.model.Inventory;
import com.skytopper.budgettracker.model.Subcategory;
import com.skytopper.budgettracker.model.UnitofMeasure;

import java.util.ArrayList;

/**
 * Created by swayangjit on 4/22/2015.
 */
public class DatabaseHelper {
    private DatabaseTable table;
    private Context context;

    public DatabaseHelper(Context context) {
        this.context = context;
        SQLiteOpenHelper helper = new DbOpenHelper(context);
        table = new DatabaseTable(helper.getWritableDatabase());
    }

    public long addCompany(Company company) {
        return table.addCompany(company);
    }

    public ArrayList<Company> getCompanies() {

        return table.getCompanies();
    }

    public void updateCompany(long id, Company company) {
        table.updateCompany(id, company);
    }

    public void deleteCompany(long id) {
        table.deleteCompany(id);
    }

    public long addUnitofMeasure(UnitofMeasure unitofMeasure) {
        return table.addUnitofMeasure(unitofMeasure);
    }

    public ArrayList<UnitofMeasure> getUnitofMeasure() {

        return table.getUnitofMeasure();
    }

    public void updateUnitofMeasure(long id, UnitofMeasure unitofMeasure) {
        table.updateUnitofMeasure(id, unitofMeasure);
    }

    public void deleteUnitofMeasuree(long id) {
        table.deleteUnitofMeasure(id);
    }

    public long addCategory(Category category) {
        return table.addCategory(category);
    }

    public ArrayList<Category> getAllCategories() {
        return table.getAllCategories();
    }

    public void updateCategory(long id, Category category) {
        table.updateCategory(id, category);
    }

    public void deleteCategory(long id) {
        table.deleteCategory(id);
    }

    public long addSubCategory(Subcategory subcategory) {
        return table.addSubCategory(subcategory);
    }

    public ArrayList<Subcategory> getSubCategories(String categoryid) {
        return table.getAllSubCategories(categoryid);
    }

    public void updateSubcategory(long id, Subcategory subcategory) {
        table.updateSubCategory(id, subcategory);
    }

    public void deleteSubcategory(long id) {
        table.deleteSubCategory(id);
    }

    public long addInventory(Inventory inventory) {
        return table.addInventory(inventory);
    }

    public ArrayList<Inventory> getAllInventories() {
        return table.getInventories();
    }
    public ArrayList<Inventory> getInventories(String subcategory,String datetime) {
        return table.getInventories(subcategory,datetime);
    }
    public ArrayList<CategorySum> getCategory(String caompanyid,String categoryname) {
        return table.getCategory(caompanyid, categoryname);
    }
    public ArrayList<CategorySum> getSubCategory(String caompanyid,String categoryname,String subcategoryName) {
        return table.getSubCategory(caompanyid, categoryname, subcategoryName);
    }
    public Category getCategory(String categoryName) {
        return table.getCategory(categoryName);
    }
    public void updateInventory(long id, Inventory inventory) {
        table.updateInventory(id, inventory);
    }

    public void deleteInventory(long id) {
        table.deleteInvntory(id);
    }
//https://github.com/kerzok/News-Reader/blob/0c50c468fecdb7faf8ca9aeefcf32261104042ef/app/src/main/java/ru/nsmelik/newsreader/fragments/AddFeedFragment.java

//
}
