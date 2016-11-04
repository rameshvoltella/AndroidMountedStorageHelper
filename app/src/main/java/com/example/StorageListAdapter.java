package com.example;

/**
 * Created by munnaz on 5/8/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ramzi.mountlib.storageutils.model.RootInfo;

import java.util.ArrayList;
import java.util.List;



public class StorageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<RootInfo> storageList;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView documentTextView;

        public MyViewHolder(View view) {
            super(view);
            documentTextView=(TextView)view.findViewById(R.id.storage_text_view);
        }
    }



    public StorageListAdapter(Context context, ArrayList<RootInfo> storageList) {
        this.mContext = context;
        this.storageList = storageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.storage_row, parent, false);

            return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {
            MyViewHolder folderExploreBaseViewHolder=(MyViewHolder)holder;

            RootInfo documentInfo = storageList.get(position);
            folderExploreBaseViewHolder.documentTextView.setText(documentInfo.title);
        }

    }

    @Override
    public int getItemCount() {
        return storageList.size();
    }



   
}

