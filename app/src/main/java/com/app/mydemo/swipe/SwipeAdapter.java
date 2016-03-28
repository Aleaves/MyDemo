package com.app.mydemo.swipe;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.mydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/14.
 */

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.ViewHolder>{

    private Context context;
    private List<String> lists=new ArrayList<String>();
    public SwipeAdapter(Context context,List<String> lists) {
        this.context=context;
        this.lists=lists;
    }

    public void addData(String str,int position){
        lists.add(position,str);
        notifyItemInserted(position);
    }

    public void addData(String str){
        lists.add(str);
        notifyDataSetChanged();
    }

    public void deleteData(int position){
        lists.remove(position);
        notifyItemRemoved(position);
    }

    public void itemRangeInserted(String str,int positionStart,int itemCount){
        for(int i=positionStart;i<positionStart+itemCount;i++){
            lists.add(positionStart,str);
        }
        notifyItemRangeInserted(positionStart, positionStart + itemCount - 1);
    }

    public void itemRangeRemoved(int positionStart,int itemCount){
        for(int i=positionStart;i<positionStart+itemCount;i++){
            lists.remove(positionStart);
        }
        notifyItemRangeRemoved(positionStart,positionStart+itemCount-1);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_swipe,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(lists.get(position));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.tv);
        }
    }
}
