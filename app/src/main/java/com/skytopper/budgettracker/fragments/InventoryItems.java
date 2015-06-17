package com.skytopper.budgettracker.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
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
import android.view.animation.AnimationUtils;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.activities.InventoryActivity;
import com.skytopper.budgettracker.adapters.InventoryAdapter;
import com.skytopper.budgettracker.database.DatabaseHelper;
import com.skytopper.budgettracker.database.DatabaseSingleton;

import java.util.ArrayList;

/**
 * Created by swayangjit_gwl on 4/18/2015.
 */
public class InventoryItems extends Fragment implements View.OnClickListener {
    private DatabaseHelper mDatabaseHelper=null;
    private FloatingActionButton mFab_Inventory=null;
    private FloatingActionButton mFab_Unit_of_measure=null;
    private FloatingActionButton mFab_Category=null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Inside onCreate");
        setHasOptionsMenu(true);
        ((InventoryActivity) getActivity()).setHomeAsEnabled(true);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_inventory:
                InventoryActivity.replaceFragmentWithSlideAnimation(null,new AddInventoryItems(),true);
                break;
            case R.id.fab_unit_of_measure:
                InventoryActivity.replaceFragmentWithSlideAnimation(null,new AddUnitofMeasure(),true);
                break;
            case R.id.fab_category:
                InventoryActivity.replaceFragmentWithSlideAnimation(null,new AddCategoryFragment(),true);
                break;
            default:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_inventory_item_lists, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ((InventoryActivity) getActivity()).setHomeAsEnabled(true);
        mDatabaseHelper = DatabaseSingleton.getInstance().getHelper();

        ArrayList mInventoryList= mDatabaseHelper.getAllInventories();
        final FloatingActionMenu menu2 = (FloatingActionMenu)view. findViewById(R.id.menu2);
        mFab_Inventory = (FloatingActionButton)view. findViewById(R.id.fab_inventory);
        mFab_Inventory.setOnClickListener(this);
        mFab_Unit_of_measure = (FloatingActionButton) view.findViewById(R.id.fab_unit_of_measure);
        mFab_Unit_of_measure.setOnClickListener(this);
        mFab_Category = (FloatingActionButton) view.findViewById(R.id.fab_category);
        mFab_Category.setOnClickListener(this);

        InventoryAdapter mAdapter = new InventoryAdapter(getActivity(),mInventoryList);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        System.out.println("Inside onAttach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((InventoryActivity) getActivity()).setTitle("Inventory Items");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
        menu.clear();
        inflater.inflate(R.menu.menu_inventory, menu);
        menu.findItem(R.id.menu_search).setChecked(true);
        menu.findItem(R.id.menu_search).setVisible(true);
        menu.findItem(R.id.add).setVisible(true);
        final MenuItem search = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search.collapseActionView();
                System.out.println("Text Search Completed::::"+query);
                search.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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
            ((InventoryActivity)getActivity()).onBackPressed();
        }
        else if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.add) {

            InventoryActivity.replaceFragmentWithSlideAnimation(null,new AddInventoryItems(),true);
        }
        else if (id == 1) {
//            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
//            if(fragment instanceof  AddInventoryItems){
//                AddInventoryItems addInventoryFragment=(AddInventoryItems)fragment;
//                addInventoryFragment.saveInventoryItem();
//                Toast.makeText(this, "Save Clicked AddInventoryItems", Toast.LENGTH_LONG).show();
//            }else if(fragment instanceof AddUnitofMeasure){
//                AddUnitofMeasure addInventoryFragment=(AddUnitofMeasure)fragment;
//                addInventoryFragment.saveUnitofMeasure();
//                Toast.makeText(this,"Save Clicked AddUnitofMeasure",Toast.LENGTH_LONG).show();
//            }else if(fragment instanceof AddCategoryFragment){
//                AddCategoryFragment addCategoryFragment=(AddCategoryFragment)fragment;
//                addCategoryFragment.saveCategory();
//                Toast.makeText(this,"Save Clicked AddUnitofMeasure",Toast.LENGTH_LONG).show();
//            }


        }


        return super.onOptionsItemSelected(item);
    }
}
