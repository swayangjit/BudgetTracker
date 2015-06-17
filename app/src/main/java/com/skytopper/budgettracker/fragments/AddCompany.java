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
import com.skytopper.budgettracker.activities.CompanyActivity;
import com.skytopper.budgettracker.activities.InventoryActivity;
import com.skytopper.budgettracker.calculator.CalculatorPopup;
import com.skytopper.budgettracker.calculator.ViewSize;
import com.skytopper.budgettracker.database.DatabaseHelper;
import com.skytopper.budgettracker.database.DatabaseSingleton;
import com.skytopper.budgettracker.model.Category;
import com.skytopper.budgettracker.model.Company;
import com.skytopper.budgettracker.model.Inventory;
import com.skytopper.budgettracker.model.Subcategory;
import com.skytopper.budgettracker.model.UnitofMeasure;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by swayangjit on 4/18/2015.
 */
public class AddCompany extends Fragment  implements View.OnClickListener{

    private int year;
    private int month;
    private int day;
    private EditText mEdt_CompanyName=null;
    private EditText mEdt_Address1=null;
    private EditText mEdt_Address2=null;
    private EditText mEdt_EmailId=null;
    private EditText mEdt_PhoneNumber=null;
    private EditText mEdt_TaxRegistration1=null;
    private EditText mEdt_TaxRegistration2=null;
    private TextView mTxt_FinancialYear=null;

    private DatabaseHelper mDatabaseHelper=null;
    private Company mCompany=null;
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
            mTxt_FinancialYear.setText(date);


        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((CompanyActivity) getActivity()).setTitle("Add Company");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_add_company, container, false);


        ((CompanyActivity) getActivity()).setupToolbar(false);

        mDatePickerDialog=new DatePickerDialog(getActivity(), mDatePickerListener, year, month, day);

        mEdt_CompanyName=(EditText)view.findViewById(R.id.edt_company_name);
        mEdt_Address1=(EditText)view.findViewById(R.id.edt_address1);
        mEdt_Address2=(EditText)view.findViewById(R.id.edt_address2);
        mEdt_EmailId=(EditText)view.findViewById(R.id.edt_emailid);
        mEdt_PhoneNumber=(EditText)view.findViewById(R.id.edt_phone_number);
        mEdt_TaxRegistration1=(EditText)view.findViewById(R.id.edt_tax_reg1);
        mEdt_TaxRegistration2=(EditText)view.findViewById(R.id.edt_tax_reg2);
        mTxt_FinancialYear=(TextView)view.findViewById(R.id.txt_financial_year);

        Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);
        mTxt_FinancialYear.setText(day+""+"-"+(month+1)+"-"+year);
        mDatabaseHelper = DatabaseSingleton.getInstance().getHelper();
        return view;
    }



    public void saveInventoryItem(){
        InventoryActivity.popCurrentFragment();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

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

        //noinspection SimplifiableIfStatement
        if(id==android.R.id.home){
            ((CompanyActivity)getActivity()).onBackPressed();
        }
        else if (id == R.id.save) {
            saveCompany();
        }



        return super.onOptionsItemSelected(item);
    }

    private void saveCompany(){
        String companyName=mEdt_CompanyName.getText().toString();

        if(TextUtils.isEmpty(companyName)){
            Toast.makeText(getActivity(),"CompanyName can't be empty",Toast.LENGTH_LONG).show();
        }
        else{

            Company company =new Company();

            company.setCompanyName(companyName);
            String date=mTxt_FinancialYear.getText().toString();
            Calendar calendar=Calendar.getInstance();

            String[] dateArray=date.split("-");
            calendar.set(Integer.valueOf(dateArray[1]), Integer.valueOf(dateArray[0]), Integer.valueOf(dateArray[2]));
            company.setDatabaseName(companyName + ".db");
            company.setFinancialYear(String.valueOf(calendar.getTimeInMillis()));
            company.setAddress1(mEdt_Address1.getText().toString());
            company.setAddress2(mEdt_Address2.getText().toString());
            company.setEmailId(mEdt_EmailId.getText().toString());
            company.setPhoneNumber(mEdt_PhoneNumber.getText().toString());
            company.setTaxRegd1(mEdt_TaxRegistration1.getText().toString());
            company.setTaxRegd2(mEdt_TaxRegistration2.getText().toString());

            company.setCompanySignature(String.valueOf(""));

            company.setCompanyLogo(String.valueOf(""));

            if(mCompany!=null && mMode!=null) {
                if (mMode.equalsIgnoreCase("edit")) {
                    mDatabaseHelper.updateCompany(mCompany.getId(), company);
                }
            }
            else{
                mDatabaseHelper.addCompany(company);
            }


            CompanyActivity.popCurrentFragment();
        }


    }

    public void setInventoryItem(Company company,String mode){
        mCompany=company;
        mMode=mode;
    }

}
