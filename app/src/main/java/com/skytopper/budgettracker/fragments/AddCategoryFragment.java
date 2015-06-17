package com.skytopper.budgettracker.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.activities.InventoryActivity;
import com.skytopper.budgettracker.database.DatabaseHelper;
import com.skytopper.budgettracker.database.DatabaseSingleton;
import com.skytopper.budgettracker.model.Category;
import com.skytopper.budgettracker.model.Subcategory;
import com.skytopper.budgettracker.model.UnitofMeasure;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by swayangjit on 4/27/2015.
 */
public class AddCategoryFragment extends Fragment {
    private DatabaseHelper mDatabaseHelper=null;
    private RadioButton mRb_Category=null;
    private RadioButton mRb_Subcategory=null;
    private Spinner mSpinner_Category=null;
    private Spinner mSpinner_Modify_Category=null;
    private Spinner mSpinner_Modify_SubCategory=null;

    private   LinearLayout mLayoutCategory=null;
    private   LinearLayout mLayout_AddCategory=null;
    private   LinearLayout mLayout_ModifyCategory=null;
    private   LinearLayout mLayout_Spinner_ModifyCategory=null;
    private   LinearLayout mLayout_Spinner_ModifySubCategory=null;
    private   LinearLayout mLayout_Sub_Delete=null;
    private   LinearLayout mLayout_Category_Delete=null;
    private EditText mEdt_Category=null;
    private EditText mEdt_Modify_Category=null;
    private EditText mEdt_Modify_SubCategory=null;
    private ArrayList<Category> mCategoryList=null;
    private ArrayList<String> mCategoryName=new ArrayList<String>();
    private Category mCategory=null;
    private Subcategory mSubCategory=null;
    private   TextView mTxtCategory=null;
    private ArrayList<Subcategory> mSubCategoryList=null;
    private ArrayList<String> mSubCategoryName=null;
    private String mMode=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((InventoryActivity) getActivity()).setHomeAsEnabled(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_add_category, container, false);

        mDatabaseHelper = DatabaseSingleton.getInstance().getHelper();
        mCategoryList=new ArrayList<>();
        Category cat=new Category();
        cat.setCategoryName("default");
        cat.setId(-1L);
        mCategoryList.add(cat);
        mCategoryList.addAll(mDatabaseHelper.getAllCategories());



        mEdt_Category=(EditText)view.findViewById(R.id.edt_category);
        mEdt_Modify_Category=(EditText)view.findViewById(R.id.edt_modify_category);
        mEdt_Modify_SubCategory=(EditText)view.findViewById(R.id.edt_modify_sub_category);
        mSpinner_Category=(Spinner)view.findViewById(R.id.spinner_category);
        mSpinner_Modify_Category=(Spinner)view.findViewById(R.id.spinner_modify_category);
        mSpinner_Modify_SubCategory=(Spinner)view.findViewById(R.id.spinner_modify_sub_category);
        mRb_Category=(RadioButton)view.findViewById(R.id.rb_category);
        mRb_Subcategory=(RadioButton)view.findViewById(R.id.rb_subcategory);
        mTxtCategory=(TextView)view.findViewById(R.id.text_category);
        mLayoutCategory=(LinearLayout)view.findViewById(R.id.layout_category);
        mLayout_AddCategory=(LinearLayout)view.findViewById(R.id.layout_add_category);
        mLayout_ModifyCategory=(LinearLayout)view.findViewById(R.id.layout_modify_category);
        mLayout_Spinner_ModifyCategory=(LinearLayout)view.findViewById(R.id.layout_modify_category_spinner);
        mLayout_Spinner_ModifySubCategory=(LinearLayout)view.findViewById(R.id.layout_modify_sub_category_spinner);
        mLayout_Sub_Delete=(LinearLayout)view.findViewById(R.id.layout_sub_delete);
        mLayout_Category_Delete=(LinearLayout)view.findViewById(R.id.layout_delete_category);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mMode= bundle.getString("mode", "");
        }
        mRb_Category.setChecked(true);
        if(mMode==null){
            ((InventoryActivity) getActivity()).setTitle("Add Category/Subcategory");
        }
       else if(mMode.equalsIgnoreCase("edit")){
            mRb_Category.setText(getResources().getString(R.string.modify_category));
            mRb_Subcategory.setText(getResources().getString(R.string.modify_subctegory));
            mLayout_AddCategory.setVisibility(View.GONE);
            mLayoutCategory.setVisibility(View.GONE);
            mLayout_Category_Delete.setVisibility(View.VISIBLE);
            mLayout_ModifyCategory.setVisibility(View.VISIBLE);
            mLayout_Spinner_ModifyCategory.setVisibility(View.VISIBLE);

            if(mCategoryList!=null){
                if(mCategoryList.size()>0){
                    mCategoryName=new ArrayList<String>();
                    for(int i=0;i<mCategoryList.size();i++){
                        mCategoryName.add(mCategoryList.get(i).getCategoryName());
                    }

                    ArrayAdapter<String> unitMeasureAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,mCategoryName);
                    mSpinner_Modify_Category.setAdapter(unitMeasureAdapter);
                }
            }

            mSpinner_Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Subcategory subcat=new Subcategory();
                    subcat.setSubcategoryName("default");
                    subcat.setId(-1L);
                    mSubCategoryList.add(subcat);
                    mSubCategoryList.addAll(mDatabaseHelper.getSubCategories(String.valueOf(mCategoryList.get(position).getId())));

                    if(mSubCategoryList!=null){
                        if(mSubCategoryList.size()>0){
                            mSubCategoryName=new ArrayList<String>();
                            for(int i=0;i<mSubCategoryList.size();i++){
                                mSubCategoryName.add(mSubCategoryList.get(i).getSubcategoryName());
                            }

                            ArrayAdapter<String> unitMeasureAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,mSubCategoryName);
                            mSpinner_Modify_SubCategory.setAdapter(unitMeasureAdapter);
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ((InventoryActivity) getActivity()).setTitle("Edit Category/Subcategory");
        }
        else if(mMode.equalsIgnoreCase("delete")){
            ((InventoryActivity) getActivity()).setTitle("Delete Category/Subcategory");
            mLayoutCategory.setVisibility(View.GONE);
            mLayout_AddCategory.setVisibility(View.GONE);
            mLayout_ModifyCategory.setVisibility(View.VISIBLE);
            mLayout_Sub_Delete.setVisibility(View.GONE);
            mLayout_Spinner_ModifySubCategory.setVisibility(View.GONE);
            mLayout_Category_Delete.setVisibility(View.VISIBLE);
            mRb_Category.setText(getResources().getString(R.string.delete_category));
            mRb_Subcategory.setText(getResources().getString(R.string.delete_subctegory));


            if(mCategoryList!=null){
                if(mCategoryList.size()>0){
                    mCategoryName=new ArrayList<String>();
                    for(int i=0;i<mCategoryList.size();i++){
                        mCategoryName.add(mCategoryList.get(i).getCategoryName());
                    }

                    ArrayAdapter<String> unitMeasureAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,mCategoryName);
                    mSpinner_Modify_Category.setAdapter(unitMeasureAdapter);
                }
            }
            mSubCategoryList=new ArrayList<>();
            Subcategory subcat=new Subcategory();
            subcat.setSubcategoryName("default");
            subcat.setId(-1L);
            mSubCategoryList.add(subcat);
          ArrayList subcategoryList=mDatabaseHelper.getSubCategories(String.valueOf("-1"));
            if(subcategoryList!=null){
                mSubCategoryList.addAll(subcategoryList);
            }


            if(mSubCategoryList!=null){
                if(mSubCategoryList.size()>0){
                    mSubCategoryName=new ArrayList<String>();
                    for(int i=0;i<mSubCategoryList.size();i++){
                        mSubCategoryName.add(mSubCategoryList.get(i).getSubcategoryName());
                    }

                    ArrayAdapter<String> unitMeasureAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,mSubCategoryName);
                    mSpinner_Modify_SubCategory.setAdapter(unitMeasureAdapter);
                }
            }


        }
        RadioGroup radioGroup = (RadioGroup)view. findViewById(R.id.rg_cat_sub);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if(checkedId==R.id.rb_category){
                    if(mMode==null){

                        mTxtCategory.setText(getResources().getString(R.string.item_category));
                        mLayoutCategory.setVisibility(View.GONE);
                    }
                    else if(mMode.equalsIgnoreCase("edit")){
                        mLayout_Spinner_ModifyCategory.setVisibility(View.VISIBLE);
                        mLayout_Spinner_ModifySubCategory.setVisibility(View.GONE);

                    }
                    else if(mMode.equalsIgnoreCase("delete")){
                        mLayout_Spinner_ModifySubCategory.setVisibility(View.GONE);
                        mLayout_Category_Delete.setVisibility(View.VISIBLE);

                    }

                }
                else if(checkedId==R.id.rb_subcategory){
                    if(mMode==null){
                        mTxtCategory.setText(getResources().getString(R.string.item_sub_category));
                        mLayoutCategory.setVisibility(View.VISIBLE);

                        mLayout_Spinner_ModifyCategory.setVisibility(View.GONE);
                        mLayout_Spinner_ModifySubCategory.setVisibility(View.GONE);

                        if(mCategoryList!=null){
                            if(mCategoryList.size()>0){
                                mCategoryName=new ArrayList<String>();
                                for(int i=0;i<mCategoryList.size();i++){
                                    mCategoryName.add(mCategoryList.get(i).getCategoryName());
                                }

                                ArrayAdapter<String> unitMeasureAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,mCategoryName);
                                mSpinner_Category.setAdapter(unitMeasureAdapter);
                            }
                        }
                    }
                    else if(mMode.equalsIgnoreCase("edit")){
                        mLayout_Spinner_ModifyCategory.setVisibility(View.GONE);
                        mLayout_Spinner_ModifySubCategory.setVisibility(View.VISIBLE);


                    }
                    else if(mMode.equalsIgnoreCase("delete")){
                        mLayout_Spinner_ModifySubCategory.setVisibility(View.VISIBLE);


                    }

                }
            }
        });
        mSpinner_Modify_Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mCategory=mCategoryList.get(position);
                if(mMode.equalsIgnoreCase("edit")){
                    mSubCategoryList=new ArrayList<Subcategory>();
                    Subcategory subcat=new Subcategory();
                    subcat.setSubcategoryName("default");
                    subcat.setId(-1L);
                    mSubCategoryList.add(subcat);
                    mSubCategoryList.addAll(mDatabaseHelper.getSubCategories(""));

                    if(mSubCategoryList!=null){
                        if(mSubCategoryList.size()>0){
                            mSubCategoryName=new ArrayList<String>();
                            for(int i=0;i<mSubCategoryList.size();i++){
                                mSubCategoryName.add(mSubCategoryList.get(i).getSubcategoryName());
                            }

                            ArrayAdapter<String> unitMeasureAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,mSubCategoryName);
                            mSpinner_Modify_SubCategory.setAdapter(unitMeasureAdapter);
                        }
                    }
                }
                else if(mMode.equalsIgnoreCase("delete")){
                    mSubCategoryList=new ArrayList<Subcategory>();

                    System.out.println("(String.valueOf(mCategoryList.get(position).getId()))"+(String.valueOf(mCategoryList.get(position).getId())));
                    mSubCategoryList.addAll(mDatabaseHelper.getSubCategories(String.valueOf(mCategoryList.get(position).getId())));

                    if(mSubCategoryList!=null){
                        if(mSubCategoryList.size()>0){
                            mSubCategoryName=new ArrayList<String>();
                            for(int i=0;i<mSubCategoryList.size();i++){
                                mSubCategoryName.add(mSubCategoryList.get(i).getSubcategoryName());
                            }

                            ArrayAdapter<String> unitMeasureAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,mSubCategoryName);
                            mSpinner_Modify_SubCategory.setAdapter(unitMeasureAdapter);
                        }
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinner_Modify_SubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(mSubCategoryList.size()>0){
                    mSubCategory=mSubCategoryList.get(position);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }

    public void saveCategory(){

        if(mMode==null){

            String categoryName=mEdt_Category.getText().toString();
            if(TextUtils.isEmpty(categoryName)){
                Toast.makeText(getActivity(),"Please Enter CategoryName name",Toast.LENGTH_LONG).show();
            }

            else {
                if (mTxtCategory.getText().toString().equalsIgnoreCase(getResources().getString(R.string.item_sub_category))) {
                    System.out.println("SubCategory Saved");
                    Subcategory subcategory = new Subcategory();
                    subcategory.setSubcategoryName(categoryName);
                    String selectedItem=mSpinner_Category.getSelectedItem().toString();
                    Category category=null;
                    for(int i=0;i<mCategoryList.size();i++){
                        if (mCategoryList.get(i).getCategoryName().equalsIgnoreCase(selectedItem)){
                            category=mCategoryList.get(i);
                        }
                    }
                    if (category == null) {

                        subcategory.setCategoryid(String.valueOf(category.getId()));
                    } else {
                        subcategory.setCategoryid(String.valueOf(category.getId()));
                    }

                    mDatabaseHelper.addSubCategory(subcategory);
                    InventoryActivity.popCurrentFragment();
                } else if (mTxtCategory.getText().toString().equalsIgnoreCase(getResources().getString(R.string.item_category))) {
                    System.out.println("CAtegory Saved");
                    Category category = new Category();
                    category.setCategoryName(categoryName);
                    mDatabaseHelper.addCategory(category);
                    InventoryActivity.popCurrentFragment();
                }
            }

        }

        else if(mMode.equalsIgnoreCase("edit")){
            String categoryName=mEdt_Modify_Category.getText().toString();
            String subcategoryStr=mEdt_Modify_SubCategory.getText().toString();
            System.out.println("categry Fragment::::"+categoryName);



                if(mLayout_Spinner_ModifyCategory.getVisibility()==View.VISIBLE && mLayout_Spinner_ModifySubCategory.getVisibility()==View.GONE){

                    if(TextUtils.isEmpty(categoryName)){
                        Toast.makeText(getActivity(),"Please Enter CategoryName name",Toast.LENGTH_LONG).show();
                    }
                    else{
                        String selectedItem=mSpinner_Modify_Category.getSelectedItem().toString();
                        Category category=null;
                        for(int i=0;i<mCategoryList.size();i++){
                            if (mCategoryList.get(i).getCategoryName().equalsIgnoreCase(selectedItem)){
                                category=mCategoryList.get(i);
                            }
                        }
                        long id=-1;
                        if(mCategory!=null){
                            id=mCategory.getId();
                        }

                        Category category1=new Category();
                        category1.setCategoryName(categoryName);
                        if(id==-1){
                            Toast.makeText(getActivity(),"Item cant be Edited",Toast.LENGTH_LONG).show();
                        }
                        else{
                            mDatabaseHelper.updateCategory(id,category1);
                            InventoryActivity.popCurrentFragment();
                        }
                    }



                }
                else if(mLayout_Spinner_ModifySubCategory.getVisibility()==View.VISIBLE && mLayout_Spinner_ModifySubCategory.getVisibility()==View.VISIBLE){

                    if(TextUtils.isEmpty(subcategoryStr)){
                        Toast.makeText(getActivity(),"Please Enter SubCategoryName name",Toast.LENGTH_LONG).show();
                    }
                    else{
                        String subcategoryName=mEdt_Modify_SubCategory.getText().toString();
                        String selectedItem=mSpinner_Modify_SubCategory.getSelectedItem().toString();
                        Subcategory subcategory=null;
                        for(int i=0;i<mSubCategoryList.size();i++){
                            if (mSubCategoryList.get(i).getSubcategoryName().equalsIgnoreCase(selectedItem)){
                                subcategory=mSubCategoryList.get(i);
                            }
                        }
                        long id=-1;
                        if(mCategory!=null){
                            id=mCategory.getId();
                        }
                        long subcatid=-1;
                        if(mSubCategory!=null){
                            subcatid=mSubCategory.getId();
                        }

                        Subcategory subcategory1=new Subcategory();
                        subcategory1.setSubcategoryName(subcategoryName);
                        subcategory1.setCategoryid(String.valueOf(id));
                        if(id==-1){
                            Toast.makeText(getActivity(),"Item cant be Edited",Toast.LENGTH_LONG).show();
                        }
                        else{
                            mDatabaseHelper.updateSubcategory(subcatid, subcategory1);
                            InventoryActivity.popCurrentFragment();
                        }

                    }

                }
        }
        else if(mMode.equalsIgnoreCase("delete")){

            if(mLayout_Category_Delete.getVisibility()==View.VISIBLE && mLayout_Spinner_ModifySubCategory.getVisibility()==View.GONE){
                System.out.println("CAtegory Deleted");
                String selectedItem=mSpinner_Modify_Category.getSelectedItem().toString();
                Category category=null;
                for(int i=0;i<mCategoryList.size();i++){
                    System.out.println("CAtegory Name"+mCategoryList.get(i).getCategoryName());
                    System.out.println("Category Id"+mCategoryList.get(i).getId());
                    if (mCategoryList.get(i).getCategoryName().equalsIgnoreCase(selectedItem)){
                        category=mCategoryList.get(i);

                    }
                }
                long id=-1;
                if(category!=null){
                    id=category.getId();
                }
                System.out.println("Id :::::Delete"+id);
                mDatabaseHelper.deleteCategory(Long.valueOf(id));
            }
            else if(mLayout_Category_Delete.getVisibility()==View.VISIBLE && mLayout_Spinner_ModifySubCategory.getVisibility()==View.VISIBLE){
                System.out.println("SubCAtegory Deleted");
                String selectedItem=mSpinner_Modify_SubCategory.getSelectedItem().toString();
                Subcategory subcategory=null;
                for(int i=0;i<mSubCategoryList.size();i++){
                    if (mSubCategoryList.get(i).getSubcategoryName().equalsIgnoreCase(selectedItem)){
                        subcategory=mSubCategoryList.get(i);
                    }
                }
                long id=-1;
                if(mCategory!=null){
                    id=mCategory.getId();
                }
                long subcatid=-1;
                if(mSubCategory!=null){
                    subcatid=mSubCategory.getId();
                }
                mDatabaseHelper.deleteSubcategory(subcatid);
            }
            InventoryActivity.popCurrentFragment();
        }

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.activity_itemdetail, menu);
        if(mMode!=null){
            if(mMode.equalsIgnoreCase("delete")){
                MenuItem menuItem=menu.findItem(R.id.save);
                menuItem.setIcon(R.drawable.ic_delete);
            }
        }



//
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id==android.R.id.home){
            ((InventoryActivity)getActivity()).onBackPressed();
        }
        else if (id == R.id.save) {
            saveCategory();
        }



        return super.onOptionsItemSelected(item);
    }
}
