package com.skytopper.budgettracker.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.activities.CompanyActivity;
import com.skytopper.budgettracker.activities.InventoryActivity;
import com.skytopper.budgettracker.adapters.AllCompaniesAdapter;
import com.skytopper.budgettracker.adapters.InventoryAdapter;
import com.skytopper.budgettracker.database.DatabaseHelper;
import com.skytopper.budgettracker.database.DatabaseSingleton;
import com.skytopper.budgettracker.model.Company;

import java.util.ArrayList;

/**
 * Created by swayangjit on 4/18/2015.
 */
public class AllCompaniesFragment extends Fragment {
    private DatabaseHelper mDatabaseHelper=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Inside onCreate");
        setHasOptionsMenu(true);
//        ((CompanyActivity) getActivity()).setTitle("Budget Tracker");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_all_companies, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDatabaseHelper = DatabaseSingleton.getInstance().getHelper();
        ((CompanyActivity) getActivity()).setTitle(getResources().getString(R.string.app_name));
        ((CompanyActivity) getActivity()).setupToolbar(true);

//        Company company=new Company();
//        company.setCompanyName("abc");
//        company.setDatabaseName("abc.db");
        ArrayList<Company> mCompanyList=new ArrayList<>();
//        mCompanyList.add(company);
        mCompanyList=mDatabaseHelper.getCompanies();
        AllCompaniesAdapter mAdapter = new AllCompaniesAdapter(getActivity(),mCompanyList);
        mRecyclerView.setAdapter(mAdapter);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_company, menu);

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
        else if (id == R.id.action_settings) {
            ((CompanyActivity) getActivity()).setTitle("Add Company");
            CompanyActivity.replaceFragmentWithSlideAnimation(this,new AddCompany(),true);
            return true;
        }
        else if (id == R.id.action_example) {

            CompanyActivity.replaceFragmentWithSlideAnimation(this, new AddCompany(), true);
        }
        else if (id == 1) {
//


        }


        return super.onOptionsItemSelected(item);
    }
}
