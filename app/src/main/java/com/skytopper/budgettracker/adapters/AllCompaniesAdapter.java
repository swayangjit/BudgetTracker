package com.skytopper.budgettracker.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skytopper.budgettracker.activities.InventoryActivity;
import com.skytopper.budgettracker.activities.MainActivity;
import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.model.Company;
import com.skytopper.budgettracker.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

public class AllCompaniesAdapter extends RecyclerView.Adapter<AllCompaniesAdapter.InventoryViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private static Activity mContext;
    private ArrayList<Company> mCompanyList = null;

    public AllCompaniesAdapter(Activity context, ArrayList<Company> companyList) {
        mContext = context;
        mCompanyList = companyList;


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_main:

                break;
            case R.id.menu:

                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {

        return false;
    }

    public  class InventoryViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView icon;
        TextView title;
        TextView content;
        TextView directory;
        View menu;

        public InventoryViewHolder(View itemView,final int index) {
            super(itemView);
            view = itemView;
            icon = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, InventoryActivity.class);
                    PreferenceUtil.storePreference(mContext,"current_company_id",String.valueOf(mCompanyList.get(index).getId()));
                    PreferenceUtil.storePreference(mContext,"current_company_name",String.valueOf(mCompanyList.get(index).getCompanyName()));
                    mContext.startActivity(intent);
                    mContext.overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
                }
            });
        }
    }


    public void add(Company company) {
        mCompanyList.add(company);
        notifyDataSetChanged();
    }

    public void set(List<Company> companyList) {
        mCompanyList.clear();
        if (companyList != null)
            mCompanyList.addAll(companyList);
        notifyDataSetChanged();
    }

//

    public void remove(Long id, boolean notify) {
        for (int i = 0; i < mCompanyList.size(); i++) {
        if(mCompanyList.get(i).getId()==id){

            mCompanyList.remove(i);
        }
        }
        if (notify) notifyDataSetChanged();
    }

    public void clear() {
        mCompanyList.clear();
        notifyDataSetChanged();
    }

    public List<Company> getFiles() {
        return mCompanyList;
    }

    @Override
    public InventoryViewHolder onCreateViewHolder(ViewGroup parent, int index) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item_company, parent, false);
        return new InventoryViewHolder(v,index);
    }

    @Override
    public void onBindViewHolder(InventoryViewHolder holder, int index) {
        Company company = mCompanyList.get(index);
        holder.view.setTag("0:" + index);
        holder.title.setText(company.getCompanyName());
        holder.content.setText(company.getDatabaseName());
    }


    @Override
    public int getItemCount() {
        return mCompanyList.size();
    }


}
