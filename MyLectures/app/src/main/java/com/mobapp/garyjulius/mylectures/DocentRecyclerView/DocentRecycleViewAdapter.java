package com.mobapp.garyjulius.mylectures.DocentRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.R;

import java.util.ArrayList;

/**
 * Created by Stefan on 18-05-15.
 */
public class DocentRecycleViewAdapter extends RecyclerView.Adapter<DocentViewHolder> {
    private ArrayList<Docent> list;
    private DocentClickListener clickListener;
    public DocentRecycleViewAdapter(ArrayList<Docent> docentList, DocentListFragment clickListener){
        list = docentList;
        this.clickListener = clickListener;
    }

    @Override
    public DocentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new DocentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DocentViewHolder holder, int position) {
        holder.assignData(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnDocentItemClick(holder.docent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.cardview_docent_item;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
