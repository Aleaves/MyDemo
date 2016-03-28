package com.app.mydemo.recItem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.app.mydemo.R;

/**
 * Created by Administrator on 2016/3/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private LayoutInflater mLayoutInflater;
    private Context context;
    private String [] titles;

    public enum ITEM_TYPE {

        ITEM1,
        ITEM2

    }

    public RecyclerViewAdapter(Context context,String [] titles){
        this.titles=titles;
        this.context=context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==ITEM_TYPE.ITEM1.ordinal()){
            return new Item1ViewHolder(mLayoutInflater.inflate(R.layout.item1,parent,false));
        }else{
            return new Item2ViewHolder(mLayoutInflater.inflate(R.layout.item2,parent,false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Item1ViewHolder) {
            ((Item1ViewHolder) holder).mTextView.setText(titles[position]);
        } else if (holder instanceof Item2ViewHolder) {
            ((Item2ViewHolder) holder).mTextView.setText(titles[position]);
        }
    }

    @Override
    public int getItemCount() {
        return titles==null?0:titles.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2==0?ITEM_TYPE.ITEM1.ordinal():ITEM_TYPE.ITEM2.ordinal();
    }


    public static class Item1ViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;
        public Item1ViewHolder(View itemView) {
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.tv_item1_text);
        }

    }

    public static class Item2ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public Item2ViewHolder(View itemView){
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.tv_item2_text);
        }
    }

}
