package com.skytopper.budgettracker.fragments;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.activities.InventoryActivity;
import com.skytopper.budgettracker.activities.SummaryActivity;
import com.skytopper.budgettracker.database.DatabaseHelper;
import com.skytopper.budgettracker.database.DatabaseSingleton;
import com.skytopper.budgettracker.model.Category;
import com.skytopper.budgettracker.model.CategorySum;
import com.skytopper.budgettracker.model.Inventory;
import com.skytopper.budgettracker.model.Subcategory;
import com.skytopper.budgettracker.util.PreferenceUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by swayangjit on 4/27/2015.
 */
public class InventorySummaryDetailsFragment extends Fragment {
    private TextView mTxt_InventoryName=null;
    private TextView mTxt_InventoryUnit=null;
    private TextView mTxt_InventoryCategory=null;
    private TextView mTxt_InventorySubCategory=null;
    private TextView mTxt_InventoryCreatedDate=null;
    private TextView mTxt_InventoryQuantity=null;
    private TextView mTxt_InventoryCost=null;
    private TextView mTxt_InventoryValue=null;
    private Inventory mInventory=null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((SummaryActivity)getActivity()).setTitle("Inventory List Details");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_summary_details, container, false);
        mTxt_InventoryName=(TextView)view.findViewById(R.id.txt_inventory_name);
        mTxt_InventoryUnit=(TextView)view.findViewById(R.id.txt_inventory_unit);;
        mTxt_InventoryCategory=(TextView)view.findViewById(R.id.txt_inventory_category);
        mTxt_InventorySubCategory=(TextView)view.findViewById(R.id.txt_inventory_subcategory);
        mTxt_InventoryCreatedDate=(TextView)view.findViewById(R.id.txt_inventory_created_date);;
        mTxt_InventoryQuantity=(TextView)view.findViewById(R.id.txt_inventory_quantity);;
        mTxt_InventoryCost=(TextView)view.findViewById(R.id.txt_inventory_cost);;
        mTxt_InventoryValue=(TextView)view.findViewById(R.id.txt_inventory_value);;

        if(mInventory!=null){
            mTxt_InventoryName.setText(mInventory.getItemName());
            mTxt_InventoryUnit.setText(mInventory.getUnitofMeasure());
            mTxt_InventoryCategory.setText(mInventory.getCategory());
            mTxt_InventorySubCategory.setText(mInventory.getSubcategory());
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            try {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(Long.valueOf(mInventory.getCreatedDate()));
                System.out.println(formatter.format(calendar.getTime()));

                mTxt_InventoryCreatedDate.setText(formatter.format(calendar.getTime()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            mTxt_InventoryQuantity.setText(String.valueOf(mInventory.getQuantity()));
            mTxt_InventoryCost.setText(String.valueOf(mInventory.getCost()));
            mTxt_InventoryValue.setText(String.valueOf(mInventory.getValue()));
        }
        return view;
    }

public  void setInventoryItem(Inventory inventroy){
    mInventory=inventroy;
}
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id==android.R.id.home){
            ((SummaryActivity)getActivity()).onBackPressed();
        }




        return super.onOptionsItemSelected(item);
    }
}
