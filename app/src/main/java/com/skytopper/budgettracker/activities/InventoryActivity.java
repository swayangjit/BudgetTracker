package com.skytopper.budgettracker.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.fragments.InventoryItems;
import com.skytopper.budgettracker.fragments.NavigationDrawerFragment;
import java.util.ArrayList;

public class InventoryActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks{
    private Toolbar mToolbar;
    private static Fragment mFragment;
    public  static ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private static 	InventoryActivity 		me = null;
    private   		int					mBackPressCount = 1;
    private  static FragmentManager mFragmentManager=null;
    private boolean mVisibityStatus;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ActionBar mActionBar=null;
    private ActionBarDrawerToggle mActionbarToggle=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        setupDrawer();
        mFragmentManager= getSupportFragmentManager();
        me = this;
        replaceFragmentWithSlideAnimation(null, new InventoryItems(), true);

    }


    private void setupDrawer() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        mActionBar=getSupportActionBar();
        mNavigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mActionbarToggle= mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
    }

    public void setHomeAsEnabled(boolean status){
        mActionbarToggle.setDrawerIndicatorEnabled(status);
    }
    public void setVisibityStatus(boolean status){
        mVisibityStatus=status;
    }

//  @Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_inventory, menu);
    return true;
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                onBackPressed();
                break;
            case R.id.action_settings:
                // Do Activity menu item stuff here
                return true;
            case R.id.action_example:
                // Not implemented here
                return false;
            default:
                break;
        }

        return false;
    }

@Override
public void onNavigationDrawerItemSelected(int position,String name) {


    if (name.equalsIgnoreCase("Inventory Report")) {
        Intent intent = new Intent(this, SummaryActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);

    } else if (name.equalsIgnoreCase("Inventory Summary Report")) {
        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra("show_inventory", "true");
        startActivity(intent);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
    } else {
        Intent intent = new Intent(this, InventoryActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        finish();
    }
}
    @Override
    public void onBackPressed()
    {
        if( fragments.size() > 0 )
        {
            Fragment frag = getCurrentFragment();
            fragments.remove(fragments.size() - 1);
            if( fragments.size() > 0 )
            {

            }
        }

        if( getSupportFragmentManager().getBackStackEntryCount() == 1 )
        {
                finish();
        }
        else
        {
            me.getSupportFragmentManager().popBackStackImmediate();
        }
    }


    public static void replaceFragmentWithSlideAnimation(Fragment fromFragment, Fragment toFragment, boolean isToBeAddedToBackStack)
    {
        if( !isToBeAddedToBackStack && mFragmentManager.getBackStackEntryCount() > 0 )
        {
            if( fragments.size() > 0 )
            {
                fragments.remove(fragments.get(fragments.size() - 1));
            }
            mFragmentManager.popBackStack();
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.animator.frag_enter, R.animator.frag_exit);
        transaction.replace(R.id.container,toFragment, toFragment.getClass().getName());
        if(isToBeAddedToBackStack)
        {
            transaction.addToBackStack(toFragment.getClass().getName());
        }
        transaction.commit();

        fragments.add(toFragment);
        mFragment = toFragment;
    }



    public InventoryActivity() {
        super();
    }
    public static Fragment getCurrentFragment()
    {
        if( fragments.size() > 0 )
        {
            return fragments.get(fragments.size() - 1);
        }

        return null;
    }

    public static void popCurrentFragment()
    {
        if( fragments.size() > 0 )
        {
            Fragment frag = getCurrentFragment();

        }

        me.getSupportFragmentManager().popBackStackImmediate();

        if( fragments.size() > 0 )
        {
            fragments.remove(fragments.size() - 1);
        }
    }

}
