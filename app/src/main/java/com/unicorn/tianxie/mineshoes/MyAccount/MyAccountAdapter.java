package com.unicorn.tianxie.mineshoes.MyAccount;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unicorn.tianxie.mineshoes.R;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by tian xie on 3/22/2019.
 */

public class MyAccountAdapter extends RecyclerView.Adapter {

    public interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private static final String TAG = MyAccountAdapter.class.getSimpleName();
    private List<String> mNameList;
    private List<String> mDesList;
    private List<String> mPicUrlList;

    public MyAccountAdapter() {
        mNameList = Arrays.asList("Customer Service", "Rewards", "Account Info", "Order History", "Order History", "Order History", "Order History", "Order History", "Order History", "Order History");
        mDesList = Arrays.asList("xxxxxxxx", "xxx", "xxxxxxxxxxxxx", "xx xxxxxxxx", "xx xxxxxxxx", "xx xxxxxxxx", "xx xxxxxxxx", "xx xxxxxxxx", "xx xxxxxxxx", "xx xxxxxxxx");
        mPicUrlList = Arrays.asList("R.id", "", "", "", "", "", "", "", "", "");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder, i: " + i);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_account_list_cells, null);
        return new MyAccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder, i: " + i + ", viewHolder: " + viewHolder);
        MyAccountViewHolder holder = (MyAccountViewHolder) viewHolder;
        holder.position = i;
        holder.nameTv.setText(mNameList.get(i));
        holder.desTv.setText(mDesList.get(i));
        //holder.iconTv.setImageDrawable(Drawable.createFromPath(mPicUrlList.get(i)));
    }

    @Override
    public int getItemCount() {
        return mNameList.size();
    }

    class MyAccountViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public View rootView;
        public TextView nameTv;
        public TextView desTv;
        public ImageView iconTv;
        public int position;

        public MyAccountViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.my_account_list_cell_name);
            desTv = (TextView) itemView.findViewById(R.id.my_account_list_cell_des);
            iconTv = (ImageView) itemView.findViewById(R.id.my_account_list_cell_icon);
            rootView = itemView.findViewById(R.id.my_account_list_cell);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(null != onRecyclerViewListener){
                return onRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }
}
