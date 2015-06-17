package com.skytopper.budgettracker.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.fragments.AllCompaniesFragment;

import java.util.ArrayList;

public class CompanyActivity extends ActionBarActivity {
    private Toolbar mToolbar;
    private static Fragment mFragment;
    public  static ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private static CompanyActivity me = null;
    private   int	mBackPressCount = 1;
    private  static FragmentManager mFragmentManager=null;
    private boolean mVisibityStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        mFragmentManager= getSupportFragmentManager();
        me = this;
        replaceFragmentWithSlideAnimation(null, new AllCompaniesFragment(), true);
    }

    public void setupToolbar(boolean status) {
        if(status) {
            mToolbar = (Toolbar) findViewById(R.id.app_bar);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        else{
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

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
        transaction.replace(R.id.company_container,toFragment, toFragment.getClass().getName());
        if(isToBeAddedToBackStack)
        {
            transaction.addToBackStack(toFragment.getClass().getName());
        }
        transaction.commit();

        fragments.add(toFragment);
        mFragment = toFragment;
    }


    public CompanyActivity() {
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
            //getFragmentManager().popBackStackImmediate();
            Fragment frag = getCurrentFragment();

        }

        me.getSupportFragmentManager().popBackStackImmediate();

        if( fragments.size() > 0 )
        {
            fragments.remove(fragments.size() - 1);
        }
    }

}
