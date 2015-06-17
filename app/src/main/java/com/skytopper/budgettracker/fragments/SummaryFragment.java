package com.skytopper.budgettracker.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by swayangjit on 4/27/2015.
 */
public class SummaryFragment extends Fragment implements View.OnClickListener{
    private TextView mTxt_ClosingDate=null;
    private TextView mTxt_CompanyName=null;
    private LinearLayout mLayout_Summary=null;

    private int year;
    private int month;
    private int day;
    private String mCurrentDateString="";
    private DatabaseHelper mDatabaseHelper=null;
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

            String date=strDay +"-"+ mon + "-" +year;

            Calendar calender=Calendar.getInstance();
            calender.set(year,mon,day);
            mCurrentDateString=String.valueOf(calender.getTimeInMillis());
            mTxt_ClosingDate.setText(getResources().getString(R.string.closing_inventory) + " " + day + "" + "-" + (month + 1) + "-" + year);
            loadSummaries();

        }
    };

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.layout_show_calendar:
                mDatePickerDialog.show();
                break;
            case R.id.layout_show_export:
//                AddItemsDialogFragment dFragment = new AddItemsDialogFragment();
//                // Show DialogFragment
//                dFragment.show(getFragmentManager(), "Dialog Fragment");
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        mDatabaseHelper = DatabaseSingleton.getInstance().getHelper();
        mTxt_ClosingDate=(TextView)view.findViewById(R.id.txt_closing_date);
        mTxt_CompanyName=(TextView)view.findViewById(R.id.txt_company_name);
        mLayout_Summary=(LinearLayout)view.findViewById(R.id.layout_summary);

        Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);
        Calendar calender=Calendar.getInstance();
        calender.set(year,month+1,day);
        mCurrentDateString=String.valueOf(calender.getTimeInMillis());
        String companyName=PreferenceUtil.getPreference(getActivity(), "current_company_name", "");
        mTxt_CompanyName.setText(companyName);
        mTxt_ClosingDate.setText(getResources().getString(R.string.closing_inventory) + " " + day + "" + "-" + (month + 1) + "-" + year);

        ((LinearLayout)view.findViewById(R.id.layout_show_calendar)).setOnClickListener(this);
        ((LinearLayout)view.findViewById(R.id.layout_show_export)).setOnClickListener(this);


        mDatePickerDialog=new DatePickerDialog(getActivity(), mDatePickerListener, year, month, day);
        loadSummaries();

        return view;
    }
    private void loadSummaries(){


        System.out.println("Company Id" + PreferenceUtil.getPreference(getActivity(), "current_company_id", ""));
        ArrayList<Category> categoryList=mDatabaseHelper.getAllCategories();

        if(categoryList!=null){
            for(int j=0;j<categoryList.size();j++){
                ArrayList<CategorySum> categorySumList=mDatabaseHelper.getCategory(PreferenceUtil.getPreference(getActivity(), "current_company_id", ""), categoryList.get(j).getCategoryName());
                System.out.println("Size"+categorySumList.size());
                if(categorySumList!=null){
                    mLayout_Summary.removeAllViews();
                    if(categorySumList.size()>0){
                        for(int i=0;i<categorySumList.size();i++){
                            CategorySum categorySum=categorySumList.get(i);
                            View view_summary = getActivity().getLayoutInflater().inflate(R.layout.layout_summary, null);
                            TextView txt_CategoryName=(TextView)view_summary.findViewById(R.id.txt_category_name);
                            TextView txt_TotalUnit=(TextView)view_summary.findViewById(R.id.txt_total_unit);
                            TextView txt_TotalValue=(TextView)view_summary.findViewById(R.id.txt_total_value);
                            LinearLayout layout_SubSummary=(LinearLayout)view_summary.findViewById(R.id.layout_subsummary);

                            txt_CategoryName.setText(categorySum.getCategoryName().toUpperCase());
                            txt_CategoryName.setTextColor(getResources().getColor(R.color.black1));
                            txt_CategoryName.setTypeface(null, Typeface.BOLD);
                            txt_TotalUnit.setText(String.valueOf(categorySum.getTotalNoofUnit()));
                            txt_TotalUnit.setTextColor(getResources().getColor(R.color.black1));
                            txt_TotalUnit.setTypeface(null, Typeface.BOLD);
                            txt_TotalValue.setText(String.valueOf(categorySum.getTotalValue()));
                            txt_TotalValue.setTextColor(getResources().getColor(R.color.black1));
                            txt_TotalValue.setTypeface(null, Typeface.BOLD);
                            mLayout_Summary.addView(view_summary);


                            Category category=mDatabaseHelper.getCategory(categorySum.getCategoryName());
                            ArrayList<Subcategory> subcategories=mDatabaseHelper.getSubCategories(String.valueOf(category.getId()));
                                layout_SubSummary.removeAllViews();
                                for(int k=0;k<subcategories.size();k++){
                                    Subcategory subcategory=subcategories.get(k);
                                    ArrayList<CategorySum> SubcategorySumList=mDatabaseHelper.getSubCategory(PreferenceUtil.getPreference(getActivity(), "current_company_id", ""), categoryList.get(j).getCategoryName(), subcategory.getSubcategoryName());
                                    CategorySum subcategorySum=SubcategorySumList.get(i);
                                    View view_Subsummary =getActivity(). getLayoutInflater().inflate(R.layout.layout_summary, null);
                                    TextView txt_SubCategoryName=(TextView)view_Subsummary.findViewById(R.id.txt_category_name);
                                    TextView txt_SubTotalUnit=(TextView)view_Subsummary.findViewById(R.id.txt_total_unit);
                                    TextView txt_SubTotalValue=(TextView)view_Subsummary.findViewById(R.id.txt_total_value);
                                    LinearLayout layout_invSummary=(LinearLayout)view_Subsummary.findViewById(R.id.layout_subsummary);
                                    txt_SubCategoryName.setText(subcategorySum.getCategoryName().toUpperCase());
                                    txt_SubCategoryName.setPaintFlags(txt_SubCategoryName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                                    txt_SubTotalUnit.setText(String.valueOf(subcategorySum.getTotalNoofUnit()));
                                    txt_SubTotalUnit.setPaintFlags(txt_SubTotalUnit.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                                    txt_SubTotalValue.setText(String.valueOf(subcategorySum.getTotalValue()));
                                    txt_SubTotalValue.setPaintFlags(txt_SubTotalValue.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                                    layout_SubSummary.addView(view_Subsummary);
                                    ArrayList<Inventory> inventoryList=mDatabaseHelper.getInventories(subcategory.getSubcategoryName(),mCurrentDateString);
                                        layout_invSummary.removeAllViews();
                                        if(inventoryList.size()==0){
                                            layout_SubSummary.removeView(view_Subsummary);
                                            mLayout_Summary.removeView(view_summary);
                                        }
                                        for(int l=0;l<inventoryList.size();l++){
                                            Inventory inventory=inventoryList.get(l);
                                            View view_invsummary = getActivity().getLayoutInflater().inflate(R.layout.layout_summary, null);
                                            TextView txt_invCategoryName=(TextView)view_invsummary.findViewById(R.id.txt_category_name);
                                            TextView txt_invTotalUnit=(TextView)view_invsummary.findViewById(R.id.txt_total_unit);
                                            TextView txt_invTotalValue=(TextView)view_invsummary.findViewById(R.id.txt_total_value);
                                            TextView txt_Rate_Unit=(TextView)view_invsummary.findViewById(R.id.txt_rate_unit);

                                            txt_invCategoryName.setText(inventory.getItemName());
                                            txt_invTotalUnit.setText(String.valueOf(inventory.getQuantity())+" "+inventory.getUnitofMeasure());
                                            txt_invTotalValue.setText(String.valueOf(inventory.getValue()));
                                            layout_invSummary.addView(view_invsummary);

                                            txt_Rate_Unit.setVisibility(View.VISIBLE);
                                            txt_Rate_Unit.setText(String.valueOf(inventory.getCost()));
                                        }


                                }


                        }
                    }
                }
            }
        }


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
