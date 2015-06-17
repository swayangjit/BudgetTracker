package com.skytopper.budgettracker.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.activities.InventoryActivity;

/**
 * Created by swayangjit on 4/22/2015.
 */
public class AddItemsDialogFragment extends DialogFragment implements View.OnClickListener{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment_add_category, container,false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

//



        ((LinearLayout)rootView.findViewById(R.id.layout_add_measure)).setOnClickListener(this);
        ((LinearLayout)rootView.findViewById(R.id.layout_add_category)).setOnClickListener(this);
        ((LinearLayout)rootView.findViewById(R.id.layout_modify_category)).setOnClickListener(this);
        ((LinearLayout)rootView.findViewById(R.id.layout_delete_category)).setOnClickListener(this);


//
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.layout_add_measure:
                this.dismiss();
                InventoryActivity.replaceFragmentWithSlideAnimation(this,new AddUnitofMeasure(),true);
//                MainActivity.addragment(new AddUnitofMeasure());
                break;
            case R.id.layout_add_category:
                this.dismiss();
                InventoryActivity.replaceFragmentWithSlideAnimation(this,new AddCategoryFragment(),true);
                break;
            case R.id.layout_modify_category:
                AddCategoryFragment addcategoryFragment=new AddCategoryFragment();
                Bundle bundle = new Bundle();
                bundle.putString("mode", "edit");
                addcategoryFragment.setArguments(bundle);

                this.dismiss();
                InventoryActivity.replaceFragmentWithSlideAnimation(this,addcategoryFragment,true);

                break;
            case R.id.layout_delete_category:
                AddCategoryFragment addcategoryFragmentDelete=new AddCategoryFragment();
                Bundle bundleDelete = new Bundle();
                bundleDelete.putString("mode", "delete");
                addcategoryFragmentDelete.setArguments(bundleDelete);

                this.dismiss();
                InventoryActivity.replaceFragmentWithSlideAnimation(this,addcategoryFragmentDelete,true);
                break;


        }

    }
}
