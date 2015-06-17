package com.skytopper.budgettracker.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.activities.InventoryActivity;
import com.skytopper.budgettracker.fragments.AddInventoryItems;
import com.skytopper.budgettracker.fragments.InventoryItems;
import com.skytopper.budgettracker.fragments.NavigationDrawerFragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private Toolbar mToolbar;
    private boolean mVisibityStatus;
    private  static FragmentManager mFragmentManager=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTitle = getTitle();
        mFragmentManager= getSupportFragmentManager();
        setupDrawer();

    }

    private void setupDrawer() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mNavigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position,String name) {

        if(name.equalsIgnoreCase("Inventory Report")){
            Intent intent =new Intent(this,SummaryActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        }
       else  if(name.equalsIgnoreCase("Inventory Summary Report")){
            Intent intent =new Intent(this,SummaryActivity.class);
            intent.putExtra("show_inventory","true");
            startActivity(intent);
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        }
        else{
            Intent intent =new Intent(this,InventoryActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        }


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
    if(mVisibityStatus){
            for (int i = 0; i < menu.size(); i++){
                menu.getItem(i).setVisible(false);
            }


        }
        else{
        for (int i = 0; i < menu.size(); i++){
            menu.getItem(i).setVisible(true);
        }
    }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(mVisibityStatus){
            MenuItem mi = menu.add("New Item");
            mi.setIcon(R.drawable.ic_save);
            mi.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            invalidateOptionsMenu();
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
//            ((MainActivity) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
