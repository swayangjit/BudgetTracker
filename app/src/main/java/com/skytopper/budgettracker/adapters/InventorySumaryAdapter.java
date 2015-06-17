package com.skytopper.budgettracker.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.skytoper.budgettracker.R;
import com.skytopper.budgettracker.activities.InventoryActivity;
import com.skytopper.budgettracker.activities.SummaryActivity;
import com.skytopper.budgettracker.database.DatabaseHelper;
import com.skytopper.budgettracker.database.DatabaseSingleton;
import com.skytopper.budgettracker.fragments.AddInventoryItems;
import com.skytopper.budgettracker.fragments.InventorySummaryDetailsFragment;
import com.skytopper.budgettracker.model.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventorySumaryAdapter extends RecyclerView.Adapter<InventorySumaryAdapter.InventoryViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private static Activity mContext;
    private ArrayList<Inventory> mInventoryList = null;
    private DatabaseHelper mDatabaseHelper=null;

    public InventorySumaryAdapter(Activity context, ArrayList<Inventory> inventoryList) {
        mContext = context;
        mInventoryList = inventoryList;
        mDatabaseHelper = DatabaseSingleton.getInstance().getHelper();

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
            directory = (TextView) itemView.findViewById(R.id.directory);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InventorySummaryDetailsFragment inventorySummaryFragment=new InventorySummaryDetailsFragment();
                    String[] split = ((String) view.getTag()).split(":");
                    int type = Integer.parseInt(split[0]);
                    final int index1= Integer.parseInt(split[1]);
                    inventorySummaryFragment.setInventoryItem(mInventoryList.get(index1));
                    SummaryActivity.replaceFragmentWithSlideAnimation(null, inventorySummaryFragment, true);
                }
            });


        }
    }


    public void add(Inventory file) {
        mInventoryList.add(file);
        notifyDataSetChanged();
    }

    public void set(List<Inventory> files) {
        mInventoryList.clear();
        if (files != null)
            mInventoryList.addAll(files);
        notifyDataSetChanged();
    }

//    public void update(File file) {
//        for (int i = 0; i < mFiles.size(); i++) {
//            if (mFiles.get(i).getPath().equals(file.getPath())) {
//                mFiles.set(i, file);
//                break;
//            }
//        }
//        notifyDataSetChanged();
//    }

    public void remove(Long id, boolean notify) {
        for (int i = 0; i < mInventoryList.size(); i++) {
        if(mInventoryList.get(i).getId()==id){

            mInventoryList.remove(i);
        }
        }
        if (notify) notifyDataSetChanged();
    }

    public void clear() {
        mInventoryList.clear();
        notifyDataSetChanged();
    }

    public List<Inventory> getFiles() {
        return mInventoryList;
    }

    @Override
    public InventoryViewHolder onCreateViewHolder(ViewGroup parent, int index) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_summary_list_inventory_items, parent, false);
        return new InventoryViewHolder(v,index);
    }

    @Override
    public void onBindViewHolder(InventoryViewHolder holder, int index) {
        Inventory file = mInventoryList.get(index);
        holder.view.setTag("0:" + index);
        holder.view.setOnLongClickListener(this);
        holder.title.setText(file.getItemName());
        holder.content.setText(file.getItemDescription());
    }


    @Override
    public int getItemCount() {
        return mInventoryList.size();
    }

    public static void setupTouchDelegate(Context context, final View menu) {
        final int offset = context.getResources().getDimensionPixelSize(R.dimen.menu_touchdelegate);
        assert menu.getParent() != null;
        ((View) menu.getParent()).post(new Runnable() {
            public void run() {
                Rect delegateArea = new Rect();
                menu.getHitRect(delegateArea);
                delegateArea.top -= offset;
                delegateArea.bottom += offset;
                delegateArea.left -= offset;
                delegateArea.right += offset;
                TouchDelegate expandedArea = new TouchDelegate(delegateArea, menu);
                ((View) menu.getParent()).setTouchDelegate(expandedArea);
            }
        });
    }
}
