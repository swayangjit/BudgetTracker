package com.skytopper.budgettracker.activities;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.database.DatabaseHelper;
import com.skytopper.budgettracker.database.DatabaseSingleton;
import com.skytopper.budgettracker.fragments.AllCompaniesFragment;
import com.skytopper.budgettracker.fragments.InventorySummaryListFragment;
import com.skytopper.budgettracker.fragments.SummaryFragment;
import com.skytopper.budgettracker.model.Category;
import com.skytopper.budgettracker.model.CategorySum;
import com.skytopper.budgettracker.model.Inventory;
import com.skytopper.budgettracker.model.Subcategory;
import com.skytopper.budgettracker.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.Calendar;

public class SummaryActivity extends ActionBarActivity {
    public  static ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private static SummaryActivity me = null;
    private   int mBackPressCount = 1;
    private  static FragmentManager mFragmentManager=null;
    private static Fragment mFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        mFragmentManager= getSupportFragmentManager();
        me = this;
        Bundle bundle=getIntent().getExtras();
        setupDrawer();
        if(bundle!=null){
            replaceFragmentWithSlideAnimation(null, new InventorySummaryListFragment(), true);
        }
        else{
            replaceFragmentWithSlideAnimation(null, new SummaryFragment(), true);
        }

    }
    private void setupDrawer() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setTitle("Summary");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onBackPressed()
    {
        System.out.println("::::"+fragments.size());
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
    public static Fragment getCurrentFragment()
    {
        if( fragments.size() > 0 )
        {
            return fragments.get(fragments.size() - 1);
        }

        return null;
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
        transaction.replace(R.id.summary_container,toFragment, toFragment.getClass().getName());
        if(isToBeAddedToBackStack)
        {
            transaction.addToBackStack(toFragment.getClass().getName());
        }
        transaction.commit();

        fragments.add(toFragment);
        mFragment = toFragment;
    }

}
