package com.unicorn.tianxie.mineshoes.Favorites;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unicorn.tianxie.mineshoes.MyAccount.MyAccountAdapter;
import com.unicorn.tianxie.mineshoes.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tian xie on 3/23/2019.
 */

public class FavoritesAdapter extends RecyclerView.Adapter {

    private MyAccountAdapter.OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(MyAccountAdapter.OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private static final String TAG = FavoritesAdapter.class.getSimpleName();
    private List<String> mNameList;
    private List<String> mDesList;
    private List<String> mPicUrlList;

    public FavoritesAdapter() {
        mNameList = Arrays.asList("xxxxx xxxxx", "xxxxxxx", "xxxxx xxxx", "xxxxxx xxxxxx");
        mDesList = Arrays.asList("xxxxxxxxxxx", "xxxxx", "xxxxxxxxxxxxx", "xx xxxxxxxx");
        mPicUrlList = Arrays.asList("", "", "", "");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder, i: " + i);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorites_list_cells, null);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder, i: " + i + ", viewHolder: " + viewHolder);
        FavoritesViewHolder holder = (FavoritesViewHolder) viewHolder;
        holder.position = i;
        holder.nameTv.setText(mNameList.get(i));
        holder.desTv.setText(mDesList.get(i));
        //holder.iconTv.setImageDrawable(Drawable.createFromPath(mPicUrlList.get(i)));
    }

    @Override
    public int getItemCount() {
        return mNameList.size();
    }

    class FavoritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public View rootView;
        public TextView nameTv;
        public TextView desTv;
        public ImageView iconTv;
        public int position;

        public FavoritesViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.favorites_list_cell_name);
            desTv = (TextView) itemView.findViewById(R.id.favorites_list_cell_des);
            iconTv = (ImageView) itemView.findViewById(R.id.favorites_list_cell_icon);
            rootView = itemView.findViewById(R.id.favorites_list_cell);
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
