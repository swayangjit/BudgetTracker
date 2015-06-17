package com.skytopper.budgettracker.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.activities.InventoryActivity;
import com.skytopper.budgettracker.calculator.CalculatorPopup;
import com.skytopper.budgettracker.calculator.ViewSize;
import com.skytopper.budgettracker.database.DatabaseHelper;
import com.skytopper.budgettracker.database.DatabaseSingleton;
import com.skytopper.budgettracker.model.Category;
import com.skytopper.budgettracker.model.Inventory;
import com.skytopper.budgettracker.model.Subcategory;
import com.skytopper.budgettracker.model.UnitofMeasure;
import com.skytopper.budgettracker.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by swayangjit on 4/18/2015.
 */
public class AddInventoryItems extends Fragment  implements View.OnClickListener{
    private LinearLayout mBtn_ShowCalculator=null;
    private LinearLayout mLayout_add_category=null;
    private Spinner mSpinner_UnitofMeasure=null;
    private Spinner mSpinner_Category=null;
    private Spinner mSpinner_SubCategory=null;
    public static final String DEFAULT_TEXT = "0";
    public static final int WIDTH_IN_MEDIUM_DENSITY = 100;
    public static final int HEIGHT_IN_MEDIUM_DENSITY = 50;
    private DatabaseHelper mDatabaseHelper=null;
    private ArrayList<UnitofMeasure> mUnitofMeasuresList=null;
    private ArrayList<Subcategory> mSubCategoryList=null;
    private ArrayList<Category> mCategoryList=null;
    private ArrayList<String> mUnitofMeasuresName=null;
    private ArrayList<String> mCategoryName=null;
    private ArrayList<String> mSubCategoryName=null;
    private EditText mEdt_Quantity=null;
    private EditText mEdt_Cost=null;
    private EditText mEdt_Value=null;
    private EditText mEdt_ItemName=null;
    private EditText mEdt_ItemDescription=null;
    private TextView mTxt_CreatedDate=null;
    private LinearLayout mLayout_CreatedDate=null;

    private int year;
    private int month;
    private int day;

    private Inventory mInventory;
    private String mMode=null;

    private DatePickerDialog mDatePickerDialog=null;
    private DatePickerDialog.OnDateSetListener mDatePickerListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker dp, int selectedYear, int monthOfYear,
                              int dayOfMonth) {
            year = selectedYear;
            month = monthOfYear;
            day = dayOfMonth;
            String strDay;
            if(day<10){
                strDay="0"+day;
            }
            else{
                strDay=""+day;
            }
            int mon=month+1;

            //String date=mon +"-"+ strDay + "-" +year;

            //String date=year +"-"+ mon + "-" +strDay;
            String date=strDay +"-"+ mon + "-" +year;

            mTxt_CreatedDate.setText(date);


        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((InventoryActivity) getActivity()).setHomeAsEnabled(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_add_inventory_item, container, false);
        ((InventoryActivity) getActivity()).setTitle("Add Inventory Items");
        mBtn_ShowCalculator=(LinearLayout)view.findViewById(R.id.layout_show_calculator);
        mBtn_ShowCalculator.setOnClickListener(this);

        mLayout_add_category=(LinearLayout)view.findViewById(R.id.layout_add_category);
        mLayout_add_category.setOnClickListener(this);
        mSpinner_UnitofMeasure=(Spinner)view.findViewById(R.id.spinner_unit_masurment);
        mSpinner_Category=(Spinner)view.findViewById(R.id.spinner_category);
        mSpinner_SubCategory=(Spinner)view.findViewById(R.id.spinner_subcategory);

        mEdt_Quantity=(EditText)view.findViewById(R.id.edt_quantity);
        mEdt_Cost=(EditText)view.findViewById(R.id.edt_cost);
        mEdt_Value=(EditText)view.findViewById(R.id.edt_value);
        mEdt_ItemName=(EditText)view.findViewById(R.id.edt_item_name);
        mEdt_ItemDescription=(EditText)view.findViewById(R.id.edt_item_description);
        mTxt_CreatedDate=(TextView)view.findViewById(R.id.txt_created_date);
        mLayout_CreatedDate=(LinearLayout)view.findViewById(R.id.layout_date);
        mLayout_CreatedDate.setOnClickListener(this);

        mDatabaseHelper = DatabaseSingleton.getInstance().getHelper();

        mCategoryList=mDatabaseHelper.getAllCategories();

        mUnitofMeasuresList=mDatabaseHelper.getUnitofMeasure();
        mSubCategoryList=mDatabaseHelper.getSubCategories("0");

        if(mUnitofMeasuresList!=null){
            if(mUnitofMeasuresList.size()>0){
                mUnitofMeasuresName=new ArrayList<>();
                for(int i=0;i<mUnitofMeasuresList.size();i++){
                    mUnitofMeasuresName.add(mUnitofMeasuresList.get(i).getUnitName());
                }

                ArrayAdapter<String> unitMeasureAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,mUnitofMeasuresName);
                mSpinner_UnitofMeasure.setAdapter(unitMeasureAdapter);
                if(mInventory!=null){
                    if(!TextUtils.isEmpty(mInventory.getUnitofMeasure())){
                        int position=unitMeasureAdapter.getPosition(mInventory.getUnitofMeasure());
                        mSpinner_UnitofMeasure.setSelection(position);
                    }

                }
            }
        }

        if(mCategoryList!=null){
            if(mCategoryList.size()>0){
                mCategoryName=new ArrayList<>();
                for(int i=0;i<mCategoryList.size();i++){
                    mCategoryName.add(mCategoryList.get(i).getCategoryName());
                    System.out.println(mCategoryList.get(i).getCategoryName());
                }

                ArrayAdapter<String> categoryAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,mCategoryName);
                mSpinner_Category.setAdapter(categoryAdapter);

                if(mInventory!=null){
                    if(!TextUtils.isEmpty(mInventory.getCategory())){
                        int position=categoryAdapter.getPosition(mInventory.getCategory());
                        mSpinner_Category.setSelection(position);
                    }

                }
            }
            Calendar c = Calendar.getInstance();
            year  = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day   = c.get(Calendar.DAY_OF_MONTH);
            mTxt_CreatedDate.setText(day+""+"-"+(month+1)+"-"+year);

            mDatePickerDialog=new DatePickerDialog(getActivity(), mDatePickerListener, year, month, day);

        }

        mSpinner_Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mSubCategoryList=mDatabaseHelper.getSubCategories(String.valueOf(mCategoryList.get(position).getId()));
                if(mSubCategoryList!=null){
                    if(mSubCategoryList.size()>0){
                        mSubCategoryName=new ArrayList<String>();
                        for(int i=0;i<mSubCategoryList.size();i++){
                            mSubCategoryName.add(mSubCategoryList.get(i).getSubcategoryName());
                        }

                        ArrayAdapter<String> unitMeasureAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,mSubCategoryName);
                        mSpinner_SubCategory.setAdapter(unitMeasureAdapter);

                        if(mInventory!=null){
                            if(!TextUtils.isEmpty(mInventory.getSubcategory())){
                                int position1=unitMeasureAdapter.getPosition(mInventory.getSubcategory());
                                mSpinner_SubCategory.setSelection(position1);
                            }

                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//
        mEdt_Cost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String quantity=mEdt_Quantity.getText().toString();
                String cost=mEdt_Cost.getText().toString();
                if(!TextUtils.isEmpty(quantity) && !TextUtils.isEmpty(cost)) {
                    if(mInventory!=null){
                        double intQuantity=Double.valueOf(quantity);
                        double intCost=Double.valueOf(cost);
                        mEdt_Value.setText(String.valueOf(intQuantity*intCost));
                    }
                    else{
                        int intQuantity=Integer.valueOf(quantity);
                        int intCost=Integer.valueOf(cost);
                        mEdt_Value.setText(String.valueOf(intQuantity*intCost));
                    }

                }

            }
        });

        if(mInventory!=null && mMode!=null){
            if(mMode.equalsIgnoreCase("edit")){
                mEdt_ItemName.setText(mInventory.getItemName());
                mEdt_ItemDescription.setText(mInventory.getItemDescription());
                mEdt_Quantity.setText(String.valueOf(mInventory.getQuantity()));
                mEdt_Cost.setText(String.valueOf(mInventory.getCost()));
                mEdt_Value.setText(String.valueOf(mInventory.getValue()));
            }
        }

        return view;
    }

    private LinearLayout contentView() {
        ViewSize viewSize = new ViewSize();
        // container of text view. TODO: change to container with button 'open calc'
        LinearLayout contentView = new LinearLayout(getActivity());
        contentView.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                viewSize.computeWidth(WIDTH_IN_MEDIUM_DENSITY, getActivity()),
                viewSize.computeHeight(HEIGHT_IN_MEDIUM_DENSITY, getActivity())
        );
        params.setMargins(5, 5, 0, 0);
        TextView textView = new TextView(getActivity());
        textView.setText(DEFAULT_TEXT);
        textView.setPadding(2, 0, 2, 0);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        textView.setLayoutParams(params);
        textView.setOnClickListener(this);
        contentView.addView(textView);
        return contentView;

    }

    public void saveInventoryItem(){
        InventoryActivity.popCurrentFragment();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.layout_show_calculator :
                CalculatorPopup calculatorPopup = new CalculatorPopup(getActivity());
//                calculatorPopup.getCalculator().setViewWhereClickHappen(contentView());
                calculatorPopup.show();
                break;
            case R.id.layout_add_category:
                AddItemsDialogFragment dFragment = new AddItemsDialogFragment();
                // Show DialogFragment
                dFragment.show(getFragmentManager(), "Dialog Fragment");

                break;
            case R.id.layout_date :
                mDatePickerDialog.show();
                break;


        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.activity_itemdetail, menu);

//
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        System.out.println("Inside Add inventory OPtion Item selected");
        //noinspection SimplifiableIfStatement
        if(id==android.R.id.home){
            ((InventoryActivity)getActivity()).onBackPressed();
        }
        else if (id == R.id.save) {
            saveInventory();
        }



        return super.onOptionsItemSelected(item);
    }

    private void saveInventory(){


        String itemname=mEdt_ItemName.getText().toString();
        String itemDescription=mEdt_ItemDescription.getText().toString();
        if(TextUtils.isEmpty(itemname)){
            Toast.makeText(getActivity(),"Item name Cant be empty",Toast.LENGTH_LONG).show();
        }
        else{
            Inventory inventory=new Inventory();
            inventory.setItemName(itemname);
            inventory.setItemDescription(itemDescription);
            inventory.setCompanyId( PreferenceUtil.getPreference(getActivity(), "current_company_id",""));
            if(TextUtils.isEmpty(mEdt_Quantity.getText().toString())){
                inventory.setQuantity(0);
            }
            else{
                inventory.setQuantity(Integer.parseInt(mEdt_Quantity.getText().toString()));
            }
            if(TextUtils.isEmpty(mEdt_Value.getText().toString())){
                inventory.setValue(0.0);
            }
            else{
                inventory.setValue(Double.valueOf(mEdt_Value.getText().toString()));
            }
            if(TextUtils.isEmpty(mEdt_Cost.getText().toString())){
                inventory.setCost(0.0);
            }
            else{
                inventory.setCost(Double.valueOf(mEdt_Cost.getText().toString()));
            }

            if(mSpinner_Category.getSelectedItem()!=null){
                String category =mSpinner_Category.getSelectedItem().toString();
                if(category!=null){
                    inventory.setCategory(category);
                }
                else{
                    inventory.setCategory("");
                }
            }
            else{
                inventory.setCategory("");
            }

            if(mSpinner_SubCategory.getSelectedItem()!=null){
                String subCategory =mSpinner_SubCategory.getSelectedItem().toString();
                if(subCategory!=null){
                    inventory.setSubcategory(subCategory);
                }
            }
            else{
                inventory.setSubcategory("");
            }
            if (mSpinner_UnitofMeasure.getSelectedItem() != null) {

                String unitofMeasure = mSpinner_UnitofMeasure.getSelectedItem().toString();
                if (unitofMeasure != null) {
                    inventory.setUnitofMeasure(unitofMeasure);
                }
            } else {
                inventory.setUnitofMeasure("");
            }
            String date=mTxt_CreatedDate.getText().toString();
            Calendar calendar=Calendar.getInstance();
            String[] dateArray=date.split("-");
            calendar.set(Integer.valueOf(dateArray[2]), Integer.valueOf(dateArray[1]), Integer.valueOf(dateArray[0]));

            inventory.setCreatedDate(String.valueOf(calendar.getTimeInMillis()));
            inventory.setImage(String.valueOf(""));

            if(mInventory!=null && mMode!=null) {
                if (mMode.equalsIgnoreCase("edit")) {
                    mDatabaseHelper.updateInventory(mInventory.getId(),inventory);
                }
            }
            else{
                mDatabaseHelper.addInventory(inventory);
            }

            InventoryActivity.popCurrentFragment();
        }
    }

    public void setInventoryItem(Inventory inventory,String mode){
        mInventory=inventory;
        mMode=mode;
    }

}
