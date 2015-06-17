package com.skytopper.budgettracker.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

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
public class DatabaseTable {

    /**
     * Names of database table
     */
    public static final String TABLE_COMPANY = "company";
    public static final String TABLE_UNIT_MEASURE = "unit_measure";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_SUBCATEGORY= "subcategory";
    public static final String TABLE_INVENTORY= "inventory";



    /**
     * Rows of Company table
     */
    public static final String COMPANY_NAME = "company_name";
    public static final String COMPANY_ID = "company_id";
    public static final String COMPANY_FINANCIAL_YEAR = "financial_year";
    public static final String COMPANY_ADDRESS1 = "address1";
    public static final String COMPANY_ADDRESS2 = "address2";
    public static final String COMPANY_EMAIL_ID = "email_id";
    public static final String COMPANY_PHONE_NUMBER= "phone_number";
    public static final String COMPANY_TAX_REGD1= "tax_regd1";
    public static final String COMPANY_TAX_REGD2= "tax_regd2";
    public static final String COMPANY_LOGO= "logo";
    public static final String COMPANY_SIGNATURE= "signature";
    public static final String COMPANY_DATABASE_NAME= "databse_name";


    private static final String CREATE_COMPANY_TABLE = String.format(
            "create table %s (" +
                    "_id integer not null primary key autoincrement," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text not null" +

                    ")",
            TABLE_COMPANY,
            COMPANY_NAME,
            COMPANY_FINANCIAL_YEAR,
            COMPANY_ADDRESS1,
            COMPANY_ADDRESS2,
            COMPANY_EMAIL_ID,
            COMPANY_PHONE_NUMBER,
            COMPANY_TAX_REGD1,
            COMPANY_TAX_REGD2,
            COMPANY_LOGO,
            COMPANY_DATABASE_NAME,
            COMPANY_SIGNATURE


    );

    /**
     * Rows of Units of measure table
     */
    public static final String UNIT_NAME = "unit_name";
    public static final String UNIT_SYMBOL = "unit_symbol";

    private static final String CREATE_UNIT_SYMBOL_TABLE = String.format(
            "create table %s (" +
                    "_id integer not null primary key autoincrement," +
                    "%s text not null," +
                    "%s text not null" +

                    ")",
            TABLE_UNIT_MEASURE,
            UNIT_NAME,
            UNIT_SYMBOL

    );

    /**
     * Rows of  Category table
     */
    public static final String SUB_CATEGORY_NAME = "sub_category_name";
    public static final String CATEGORY_ID = "category_id";


    private static final String CREATE_SUB_CATEGORY_TABLE = String.format(
            "create table %s (" +
                    "_id integer not null primary key autoincrement," +
                    "%s text not null," +
                    "%s text not null"+


                    ")",
            TABLE_SUBCATEGORY,
            SUB_CATEGORY_NAME,
            CATEGORY_ID


    );
    /**
     * Rows of  SubCategory table
     */
    public static final String CATEGORY_NAME = "category_name";

    private static final String CREATE_CATEGORY_TABLE = String.format(
            "create table %s (" +
                    "_id integer not null primary key autoincrement," +
                    "%s text not null" +


                    ")",
            TABLE_CATEGORY,
            CATEGORY_NAME


    );

    /**
     * Rows of Inventory table
     */
    public static final String INVENTORY_ITEM_NAME = "inventory_item_name";
    public static final String INVENTORY_ITEM_DESCRIPTION = "inventory_item_description";
    public static final String INVENTORY_UNIT_MEASURE = "inventory_unit_measure";
    public static final String INVENTORY_CATEGORY = "inventory_category";
    public static final String INVENTORY_SUB_CATEGORY = "inventory_subcategory";
    public static final String INVENTORY_CREATED_DATE = "inventory_created_date";
    public static final String INVENTORY_QUANTITY = "inventory_quantity";
    public static final String INVENTORY_COST = "inventory_cost";
    public static final String INVENTORY_VALUE = "inventory_value";
    public static final String INVENTORY_IMAGE = "inventory_image";

    private static final String CREATE_INVENTORY_TABLE = String.format(
            "create table %s (" +
                    "_id integer not null primary key autoincrement," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text ," +
                    "%s text ," +
                    "%s DATETIME ," +
                    "%s integer ," +
                    "%s real ," +
                    "%s real ," +
                    "%s text" +
                    ")",
            TABLE_INVENTORY,
            COMPANY_ID,
            INVENTORY_ITEM_NAME,
            INVENTORY_ITEM_DESCRIPTION ,
            INVENTORY_UNIT_MEASURE,
            INVENTORY_CATEGORY,
            INVENTORY_SUB_CATEGORY,
            INVENTORY_CREATED_DATE,
            INVENTORY_QUANTITY ,
            INVENTORY_COST,
            INVENTORY_VALUE,
            INVENTORY_IMAGE

    );



   /*
     * Queries for tables
     */
   private static final String SELECT_ALL_COMPANY = "select * from " + TABLE_COMPANY;
    private static final String SELECT_ALL_UNIT_MEASURES = "select * from " + TABLE_UNIT_MEASURE;
    private static final String SELECT_ALL_CATEGORIES = "select * from " + TABLE_CATEGORY;
    private static final String SELECT_A_CATEGORY = "select * from " + TABLE_CATEGORY+" where "+CATEGORY_NAME+" = '";
    private static final String SELECT_ALL_SUB_CATEGORIES = "select * from " + TABLE_SUBCATEGORY+"  where  "+CATEGORY_ID+" = '";
    private static final String SELECT_ALL_SUB_CATEGORIES_TOTAL = "select * from " + TABLE_SUBCATEGORY;
    private static final String SELECT_ALL_INVENTORY = "select * from " + TABLE_INVENTORY;

    private static final String SELECT_INVENTORY_CATEGORY = "select inventory_category,SUM(inventory_quantity),SUM(inventory_value) from " + TABLE_INVENTORY
                                                             +" where "+COMPANY_ID;
    private static final String SELECT_INVENTORY_SUB_CATEGORY = "select inventory_subcategory,SUM(inventory_quantity),SUM(inventory_value) from " + TABLE_INVENTORY
            +" where "+COMPANY_ID;





    private  SQLiteDatabase db=null;

    public static void init(SQLiteDatabase db) {
        db.execSQL(CREATE_COMPANY_TABLE);
        db.execSQL(CREATE_UNIT_SYMBOL_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_SUB_CATEGORY_TABLE);
        db.execSQL(CREATE_INVENTORY_TABLE);
    }


    public DatabaseTable(SQLiteDatabase db) {
        this.db = db;
    }
    public long addCompany(Company company) {
        ContentValues values = new ContentValues();
        if(company.getCompanyName()!=null){
            values.put(COMPANY_NAME, company.getCompanyName());
        }
        if(company.getDatabaseName()!=null){
            values.put(COMPANY_DATABASE_NAME, company.getDatabaseName());
        }
        if( company.getFinancialYear()!=null){
            values.put(COMPANY_FINANCIAL_YEAR, company.getFinancialYear());
        }
        if( company.getAddress1()!=null){
            values.put(COMPANY_ADDRESS1, company.getAddress1());
        }
        if( company.getAddress2()!=null){
            values.put(COMPANY_ADDRESS2, company.getAddress2());
        }
        if( company.getEmailId()!=null){
            values.put(COMPANY_EMAIL_ID, company.getEmailId());
        }
        if( company.getPhoneNumber()!=null){
            values.put(COMPANY_PHONE_NUMBER, company.getPhoneNumber());
        }

        if(company.getTaxRegd1()!=null){
            values.put(COMPANY_TAX_REGD1, company.getTaxRegd1());
        }
        if( company.getTaxRegd2()!=null){
            values.put(COMPANY_TAX_REGD2, company.getTaxRegd2());
        }

        if(company.getCompanyLogo()!=null){
            values.put(COMPANY_LOGO, company.getCompanyLogo());
        }
        if(company.getCompanySignature()!=null){
            values.put(COMPANY_SIGNATURE, company.getCompanySignature());
        }
        return db.insertOrThrow(TABLE_COMPANY, null, values);
    }

    public ArrayList<Company> getCompanies() {
        Cursor cursor = null;
        ArrayList<Company> result = new ArrayList<Company>();
        try {
            cursor = db.rawQuery(SELECT_ALL_COMPANY, null);

            while (cursor.moveToNext()) {

                long id =cursor.getLong(0);
                String companyName = cursor.getString(1);
                String financialYear = cursor.getString(2);
                String address1 = cursor.getString(3);
                String address2 = cursor.getString(4);
                String emailid = cursor.getString(5);
                String phoneNumber = cursor.getString(6);
                String tax_regd1 = cursor.getString(7);
                String tax_regd2 = cursor.getString(8);
                String logo = cursor.getString(9);
                String database_Name = cursor.getString(10);
                String signature = cursor.getString(11);
                Company company=new Company();
                company.setId(id);
                company.setCompanyName(companyName);
                company.setFinancialYear(financialYear);
                company.setAddress1(address1);
                company.setAddress2(address2);
                company.setEmailId(emailid);
                company.setPhoneNumber(phoneNumber);
                company.setTaxRegd1(tax_regd1);
                company.setTaxRegd2(tax_regd2);
                company.setCompanyLogo(logo);
                company.setDatabaseName(database_Name);
                company.setCompanySignature(signature);

                result.add(company);
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

    public void updateCompany(long id, Company company) {
        ContentValues values = new ContentValues();

        if(company.getCompanyName()!=null){
            values.put(COMPANY_NAME, company.getCompanyName());
        }
        if(company.getDatabaseName()!=null){
            values.put(COMPANY_DATABASE_NAME, company.getDatabaseName());
        }
        if( company.getFinancialYear()!=null){
            values.put(COMPANY_FINANCIAL_YEAR, company.getFinancialYear());
        }
        if( company.getAddress1()!=null){
            values.put(COMPANY_ADDRESS1, company.getAddress1());
        }
        if( company.getAddress2()!=null){
            values.put(COMPANY_ADDRESS2, company.getAddress2());
        }
        if( company.getEmailId()!=null){
            values.put(COMPANY_EMAIL_ID, company.getEmailId());
        }
        if( company.getPhoneNumber()!=null){
            values.put(COMPANY_PHONE_NUMBER, company.getPhoneNumber());
        }

        if(company.getTaxRegd1()!=null){
            values.put(COMPANY_TAX_REGD1, company.getTaxRegd1());
        }
        if( company.getTaxRegd2()!=null){
            values.put(COMPANY_TAX_REGD2, company.getTaxRegd2());
        }

        if(company.getCompanyLogo()!=null){
            values.put(COMPANY_LOGO, company.getCompanyLogo());
        }
        if(company.getCompanySignature()!=null){
            values.put(COMPANY_SIGNATURE, company.getCompanySignature());
        }
        db.update(TABLE_COMPANY, values, "_id = ?", new String[]{Long.toString(id)});
    }

    public void deleteCompany(long id) {
        db.delete(TABLE_COMPANY, "_id = ?", new String[]{Long.toString(id)});
    }


    public long addInventory(Inventory inventory) {
        ContentValues values = new ContentValues();

        if(inventory.getCompanyId()!=null){
            values.put(COMPANY_ID, inventory.getCompanyId());
        }
        if(inventory.getItemName()!=null){
            values.put(INVENTORY_ITEM_NAME, inventory.getItemName());
        }
        if(inventory.getItemDescription()!=null){
            values.put(INVENTORY_ITEM_DESCRIPTION, inventory.getItemDescription());
        }
        if( inventory.getUnitofMeasure()!=null){
          values.put(INVENTORY_UNIT_MEASURE, inventory.getUnitofMeasure());
        }
        if( inventory.getCategory()!=null){
            values.put(INVENTORY_CATEGORY, inventory.getCategory());
        }
       if( inventory.getSubcategory()!=null){
           values.put(INVENTORY_SUB_CATEGORY, inventory.getSubcategory());
       }
        if( inventory.getCreatedDate()!=null){
            values.put(INVENTORY_CREATED_DATE, inventory.getCreatedDate());
        }
        if( inventory.getQuantity()!=0){
            values.put(INVENTORY_QUANTITY, inventory.getQuantity());
        }

       if(inventory.getCost()!=0){
           values.put(INVENTORY_COST, inventory.getCost());
       }
       if( inventory.getValue()!=0){
           values.put(INVENTORY_VALUE, inventory.getValue());
       }

        if(inventory.getImage()!=null){
            values.put(INVENTORY_IMAGE, inventory.getImage());
        }

        return db.insertOrThrow(TABLE_INVENTORY, null, values);
    }
    public  ArrayList<CategorySum> getCategory(String companyid,String category){
        Cursor cursor = null;
        ArrayList<CategorySum> result = new ArrayList<CategorySum>();
     try {
        cursor = db.rawQuery(SELECT_INVENTORY_CATEGORY+"='"+companyid+"'"+" AND "+INVENTORY_CATEGORY+"='"+category+"'", null);
        System.out.println("Query"+SELECT_INVENTORY_CATEGORY+"='"+companyid+"'");
        while (cursor.moveToNext()) {

            String categoryName =cursor.getString(0);
            double totalUnit = cursor.getDouble(1);
            double totalValue = cursor.getDouble(2);
            CategorySum categorySum=new CategorySum();
            categorySum.setCategoryName(categoryName);
            categorySum.setTotalNoofUnit(totalUnit);
            categorySum.setTotalValue(totalValue);
            result.add(categorySum);
        }

    } finally {
        if (cursor != null) {
            cursor.close();
        }
    }
        return result;
}
    public  ArrayList<CategorySum> getSubCategory(String companyid,String category,String subcategory){
        Cursor cursor = null;
        ArrayList<CategorySum> result = new ArrayList<CategorySum>();
        try {
            cursor = db.rawQuery(SELECT_INVENTORY_SUB_CATEGORY+"='"+companyid+"'"+" AND "+INVENTORY_CATEGORY+"='"+category+"'"+" AND "+INVENTORY_SUB_CATEGORY+"='"+subcategory+"'", null);
            System.out.println("Query"+SELECT_INVENTORY_SUB_CATEGORY+"='"+companyid+"'"+" AND "+INVENTORY_CATEGORY+"='"+category+"'"+" AND "+INVENTORY_SUB_CATEGORY+"='"+subcategory+"'");
            while (cursor.moveToNext()) {

                String subcategoryName =cursor.getString(0);
                double totalUnit = cursor.getDouble(1);
                double totalValue = cursor.getDouble(2);
                CategorySum categorySum=new CategorySum();
                categorySum.setCategoryName(subcategoryName);
                categorySum.setTotalNoofUnit(totalUnit);
                categorySum.setTotalValue(totalValue);
                result.add(categorySum);
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }
    public ArrayList<Inventory> getInventories() {
        Cursor cursor = null;
        ArrayList<Inventory> result = new ArrayList<Inventory>();
        try {
            cursor = db.rawQuery(SELECT_ALL_INVENTORY, null);

            while (cursor.moveToNext()) {

                long id =cursor.getLong(0);
                String companyid = cursor.getString(1);
                String itemname = cursor.getString(2);
                String itemdescription = cursor.getString(3);
                String unit_of_measure = cursor.getString(4);
                String category = cursor.getString(5);
                String subcategory = cursor.getString(6);
                String createdDate = cursor.getString(7);
                int quantity = cursor.getInt(8);
                double cost = cursor.getDouble(9);
                double value = cursor.getDouble(10);
                String imagePath = cursor.getString(11);
                Inventory inventory=new Inventory();
                inventory.setId(id);
                inventory.setCompanyId(companyid);
                inventory.setItemName(itemname);
                inventory.setItemDescription(itemdescription);
                inventory.setUnitofMeasure(unit_of_measure);
                inventory.setCategory(category);
                inventory.setSubcategory(subcategory);
                inventory.setCreatedDate(createdDate);
                inventory.setQuantity(quantity);
                inventory.setCost(cost);
                inventory.setValue(value);
                inventory.setImage(imagePath);

                result.add(inventory);
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

    public ArrayList<Inventory> getInventories(String subcategory,String datetime) {
        Cursor cursor = null;
        ArrayList<Inventory> result = new ArrayList<Inventory>();
        try {
            cursor = db.rawQuery(SELECT_ALL_INVENTORY+" where "+INVENTORY_SUB_CATEGORY+" ='"+subcategory+"'" +"AND "+INVENTORY_CREATED_DATE+" <='"+datetime+"'", null);
            System.out.println("Inventory Query::"+SELECT_ALL_INVENTORY+" where "+INVENTORY_SUB_CATEGORY+" ='"+subcategory+"'" +"AND "+INVENTORY_CREATED_DATE+" <='"+datetime+"'");
            while (cursor.moveToNext()) {

                long id =cursor.getLong(0);
                String companyid = cursor.getString(1);
                String itemname = cursor.getString(2);
                String itemdescription = cursor.getString(3);
                String unit_of_measure = cursor.getString(4);
                String category = cursor.getString(5);
                String subcategory1 = cursor.getString(6);
                String createdDate = cursor.getString(7);
                int quantity = cursor.getInt(8);
                double cost = cursor.getDouble(9);
                double value = cursor.getDouble(10);
                String imagePath = cursor.getString(11);
                Inventory inventory=new Inventory();
                inventory.setId(id);
                inventory.setCompanyId(companyid);
                inventory.setItemName(itemname);
                inventory.setItemDescription(itemdescription);
                inventory.setUnitofMeasure(unit_of_measure);
                inventory.setCategory(category);
                inventory.setSubcategory(subcategory1);
                inventory.setCreatedDate(createdDate);
                inventory.setQuantity(quantity);
                inventory.setCost(cost);
                inventory.setValue(value);
                inventory.setImage(imagePath);

                result.add(inventory);
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

        public void updateInventory(long id, Inventory inventory) {
            ContentValues values = new ContentValues();
            values.put(INVENTORY_ITEM_NAME, inventory.getItemName());
            values.put(INVENTORY_ITEM_DESCRIPTION, inventory.getItemDescription());
            values.put(INVENTORY_UNIT_MEASURE, inventory.getUnitofMeasure());
            values.put(INVENTORY_CATEGORY, inventory.getCategory());
            values.put(INVENTORY_SUB_CATEGORY, inventory.getSubcategory());
            values.put(INVENTORY_CREATED_DATE, inventory.getCreatedDate());
            values.put(INVENTORY_QUANTITY, inventory.getQuantity());
            values.put(INVENTORY_COST, inventory.getCost());
            values.put(INVENTORY_VALUE, inventory.getValue());
            values.put(INVENTORY_IMAGE, inventory.getImage());;
            db.update(TABLE_INVENTORY, values, "_id = ?", new String[]{Long.toString(id)});
        }

        public void deleteInvntory(long id) {
            db.delete(TABLE_INVENTORY, "_id = ?", new String[]{Long.toString(id)});
        }

    public long addUnitofMeasure(UnitofMeasure unitofMeasure) {
        ContentValues values = new ContentValues();
        if(unitofMeasure.getUnitName()!=null){
            values.put(UNIT_NAME, unitofMeasure.getUnitName());
        }
        if(unitofMeasure.getUnitDescription()!=null){
            values.put(UNIT_SYMBOL, unitofMeasure.getUnitDescription());
        }

        return db.insertOrThrow(TABLE_UNIT_MEASURE, null, values);
    }

    public ArrayList<UnitofMeasure> getUnitofMeasure() {
        Cursor cursor = null;
        ArrayList<UnitofMeasure> result = new ArrayList<>();
        try {
            cursor = db.rawQuery(SELECT_ALL_UNIT_MEASURES, null);

            while (cursor.moveToNext()) {

                long id =cursor.getLong(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                UnitofMeasure unitofMeasure=new UnitofMeasure();
                unitofMeasure.setId(id);
                unitofMeasure.setUnitName(name);
                unitofMeasure.setUnitDescription(description);

                result.add(unitofMeasure);
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

    public void updateUnitofMeasure(long id, UnitofMeasure unitofMeasure) {
        ContentValues values = new ContentValues();
        values.put(UNIT_NAME, unitofMeasure.getUnitName());
        values.put(UNIT_SYMBOL, unitofMeasure.getUnitDescription());
        db.update(TABLE_UNIT_MEASURE, values, "_id = ?", new String[]{Long.toString(id)});
    }

    public void deleteUnitofMeasure(long id) {
        db.delete(TABLE_UNIT_MEASURE, "_id = ?", new String[]{Long.toString(id)});
    }


    public long addCategory(Category category) {
        ContentValues values = new ContentValues();
        if(category.getCategoryName()!=null){
            values.put(CATEGORY_NAME, category.getCategoryName());
        }


        return db.insertOrThrow(TABLE_CATEGORY, null, values);
    }
    public Category getCategory(String categoryName) {
        Cursor cursor = null;
        Category category = new Category();
        try {
            cursor = db.rawQuery(SELECT_A_CATEGORY+categoryName+"'", null);
            cursor.moveToFirst();
            long id =cursor.getLong(0);
            String name = cursor.getString(1);
            category.setId(id);
            category.setCategoryName(name);




        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return category;
    }
    public ArrayList<Category> getAllCategories() {
        Cursor cursor = null;
        ArrayList<Category> result = new ArrayList<Category>();
        try {
            cursor = db.rawQuery(SELECT_ALL_CATEGORIES, null);

            while (cursor.moveToNext()) {

                long id =cursor.getLong(0);
                String name = cursor.getString(1);
                System.out.println(" inside get All Categoriesid::::::"+ id);
                Category category=new Category();
                category.setId(id);
                category.setCategoryName(name);

                result.add(category);
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

    public void updateCategory(long id, Category category) {
        ContentValues values = new ContentValues();
        values.put(CATEGORY_NAME, category.getCategoryName());


        db.update(TABLE_CATEGORY, values, "_id = ?", new String[]{Long.toString(id)});
    }

    public void deleteCategory(long id) {
        System.out.println(" id::::::"+ id);
        db.delete(TABLE_CATEGORY, "_id = ?", new String[]{Long.toString(id)});
    }
    public long addSubCategory(Subcategory subcategory) {
        ContentValues values = new ContentValues();
        System.out.println("Id::::::"+subcategory.getCategoryid());
        System.out.println("category.getSubcategoryName()::::::"+subcategory.getSubcategoryName());
        values.put(SUB_CATEGORY_NAME, subcategory.getSubcategoryName());
        values.put(CATEGORY_ID, subcategory.getCategoryid());

        return db.insertOrThrow(TABLE_SUBCATEGORY, null, values);
    }

    public ArrayList<Subcategory> getAllSubCategories(String categoryId) {
        Cursor cursor = null;
        ArrayList<Subcategory> result = new ArrayList<Subcategory>();
        try {
            if (TextUtils.isEmpty(categoryId)){
                cursor = db.rawQuery(SELECT_ALL_SUB_CATEGORIES_TOTAL, null);
            }
            else{
                cursor = db.rawQuery(SELECT_ALL_SUB_CATEGORIES+categoryId+"'", null);
            }


            while (cursor.moveToNext()) {

                long id =cursor.getLong(0);
                String name = cursor.getString(1);
                String categoryid = cursor.getString(2);

                Subcategory subcategory=new Subcategory();
                subcategory.setId(id);
                subcategory.setSubcategoryName(name);
                subcategory.setCategoryid(categoryid);
                result.add(subcategory);
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

    public void updateSubCategory(long id, Subcategory subcategory) {
        ContentValues values = new ContentValues();
        values.put(SUB_CATEGORY_NAME, subcategory.getSubcategoryName());
        values.put(CATEGORY_ID,subcategory.getCategoryid());
        db.update(TABLE_SUBCATEGORY, values, "_id = ?", new String[]{Long.toString(id)});
    }

    public void deleteSubCategory(long id) {
        db.delete(TABLE_SUBCATEGORY, "_id = ?", new String[]{Long.toString(id)});
    }


}

