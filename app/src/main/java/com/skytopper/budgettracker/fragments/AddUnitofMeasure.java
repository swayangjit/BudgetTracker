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
import android.widget.EditText;
import android.widget.Toast;

import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.activities.InventoryActivity;
import com.skytopper.budgettracker.database.DatabaseHelper;
import com.skytopper.budgettracker.database.DatabaseSingleton;
import com.skytopper.budgettracker.model.UnitofMeasure;

/**
 * Created by swayangjit on 4/22/2015.
 */
public class AddUnitofMeasure extends Fragment{
    private DatabaseHelper mDatabaseHelper=null;
    private EditText mEdt_Unit_Name=null;
    private EditText mEdt_Symbol=null;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_add_unit_measure, container, false);
        mEdt_Unit_Name=(EditText)view.findViewById(R.id.edt_unit);
        mEdt_Symbol=(EditText)view.findViewById(R.id.edt_symbol);
        mDatabaseHelper = DatabaseSingleton.getInstance().getHelper();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((InventoryActivity) getActivity()).setVisibityStatus(true);
        ((ActionBarActivity) getActivity()).supportInvalidateOptionsMenu();
        ((InventoryActivity) getActivity()).setTitle("Add Unit of Measure");

    }

    public void saveUnitofMeasure(){

        String unitName=mEdt_Unit_Name.getText().toString();
        String symbol=mEdt_Symbol.getText().toString();

        if(TextUtils.isEmpty(unitName)){
            Toast.makeText(getActivity(),"Please Enter Unit name",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(symbol)){
            Toast.makeText(getActivity(),"Please Enter Symbol",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(unitName) && TextUtils.isEmpty(symbol)){
            Toast.makeText(getActivity(),"Please Enter Unit name and symbol",Toast.LENGTH_LONG).show();
        }
        else{
            UnitofMeasure unitofMeasure=new UnitofMeasure();
            unitofMeasure.setUnitName(unitName);
            unitofMeasure.setUnitDescription(symbol);
            mDatabaseHelper.addUnitofMeasure(unitofMeasure);
            InventoryActivity.popCurrentFragment();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((InventoryActivity) getActivity()).setHomeAsEnabled(false);
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
            ((InventoryActivity)getActivity()).onBackPressed();
        }
        else if (id == R.id.save) {
            saveUnitofMeasure();
        }



        return super.onOptionsItemSelected(item);
    }
}
