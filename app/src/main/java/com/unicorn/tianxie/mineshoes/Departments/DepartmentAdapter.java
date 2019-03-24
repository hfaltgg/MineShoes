package com.unicorn.tianxie.mineshoes.Departments;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unicorn.tianxie.mineshoes.MyAccount.MyAccountAdapter;
import com.unicorn.tianxie.mineshoes.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tian xie on 3/24/2019.
 */

public class DepartmentAdapter extends RecyclerView.Adapter {

    private MyAccountAdapter.OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(MyAccountAdapter.OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private static final String TAG = DepartmentAdapter.class.getSimpleName();
    private List<String> mNameList;

    public DepartmentAdapter() {
        mNameList = Arrays.asList("xxxxx xxxxx", "xxxxxxx", "xxxxx xxxx", "xxxxxx xxxxxx");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder, i: " + i);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.department_list_cells, null);
        return new DepartmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder, i: " + i + ", viewHolder: " + viewHolder);
        DepartmentViewHolder holder = (DepartmentViewHolder) viewHolder;
        holder.position = i;
        holder.nameTv.setText(mNameList.get(i));
    }

    @Override
    public int getItemCount() {
        return mNameList.size();
    }

    class DepartmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public View rootView;
        public TextView nameTv;
        public int position;

        public DepartmentViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.department_list_cell_name);
            rootView = itemView.findViewById(R.id.department_list_cell);
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
