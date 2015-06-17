package com.skytopper.budgettracker.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.customviews.AnimatedExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by swayangjit on 4/18/2015.
 */
 public class ExpandableAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
    private LayoutInflater inflater;

    List<String> headerList;
    HashMap<String,List<String>> childList;

    public ExpandableAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<String> headerList,HashMap<String,List<String>> childList) {
        this.headerList=headerList;
        this.childList=childList;
    }

    @Override
    public Object  getChild(int groupPosition, int childPosition) {
        return  this.childList.get(this.headerList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLstChild, View singleItemInChild,ViewGroup parentView) {
//

        final String childText = (String) getChild(groupPosition, childPosition);
        ChildHolder holder;
        if (singleItemInChild == null) {
            singleItemInChild = inflater.inflate(R.layout.list_item, null);
            holder = new ChildHolder();
            holder.title = (TextView) singleItemInChild.findViewById(R.id.textTitle);

            singleItemInChild.setTag(holder);
        }
        else{
            holder = (ChildHolder) singleItemInChild.getTag();
        }

        holder.title.setText(childText);



        return singleItemInChild;


    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        if(childList!=null){
            if(childList.get(this.headerList.get(groupPosition))!=null){
                return this.childList.get(this.headerList.get(groupPosition)).size();
            }
            else{
                return 0;
            }

        }
        else{
            return 0;
        }

    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headerList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.headerList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View eachItemInHeader, ViewGroup parentView) {
        String headerTitle = (String) getGroup(groupPosition);
        GroupHolder holder;
        if (eachItemInHeader == null) {
            eachItemInHeader = inflater.inflate(R.layout.group_item, null);
            holder = new GroupHolder();
            holder.title = (TextView) eachItemInHeader.findViewById(R.id.textTitle);
            holder.img_Arrow = (ImageView) eachItemInHeader.findViewById(R.id.img_drawer);

            eachItemInHeader.setTag(holder);
        }
        else{
            holder = (GroupHolder) eachItemInHeader.getTag();
        }

        holder.title.setText(headerTitle);
        if(groupPosition==0){
            holder.img_Arrow.setBackgroundResource(R.drawable.ic_create_inventory);
        }
        else {
            holder.img_Arrow.setBackgroundResource(R.drawable.ic_fa_bar_chart_o);
        }
//        if(getChildrenCount(groupPosition)==0){
//            holder.img_Arrow.setVisibility(View.GONE);
//        }
//        else{
//            holder.img_Arrow.setVisibility(View.VISIBLE);
            if(isExpanded){
                eachItemInHeader.setBackgroundResource(R.drawable.listitem_selected);
            }
            else{
                eachItemInHeader.setBackgroundResource(R.drawable.listitem_selector);
            }
//        }


        return eachItemInHeader;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

    private static class GroupItem {
        String title;
        List<ChildItem> items = new ArrayList<ChildItem>();
    }

    private static class ChildItem {
        String title;
        String hint;
    }

    private static class ChildHolder {
        TextView title;
        TextView hint;
    }

    private static class GroupHolder {
        TextView title;
        ImageView img_Arrow;
    }

}

