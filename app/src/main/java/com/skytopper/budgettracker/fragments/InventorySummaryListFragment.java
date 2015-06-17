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
import com.skytopper.budgettracker.activities.InventoryActivity;
import com.skytopper.budgettracker.activities.SummaryActivity;
import com.skytopper.budgettracker.adapters.InventoryAdapter;
import com.skytopper.budgettracker.adapters.InventorySumaryAdapter;
import com.skytopper.budgettracker.database.DatabaseHelper;
import com.skytopper.budgettracker.database.DatabaseSingleton;

import java.util.ArrayList;

/**
 * Created by swayangjit on 4/18/2015.
 */
public class InventorySummaryListFragment extends Fragment {
    private DatabaseHelper mDatabaseHelper=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Inside onCreate");
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_inventory_item_lists, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDatabaseHelper = DatabaseSingleton.getInstance().getHelper();

       ArrayList mInventoryList= mDatabaseHelper.getAllInventories();

        InventorySumaryAdapter mAdapter = new InventorySumaryAdapter(getActivity(),mInventoryList);
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

        ((SummaryActivity) getActivity()).setTitle("Inventory List");
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
