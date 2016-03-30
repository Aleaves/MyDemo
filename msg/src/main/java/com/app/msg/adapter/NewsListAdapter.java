package com.app.msg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.msg.R;
import com.app.msg.bean.News;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by llb on 2016/3/28.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>{

    private Context mContext;
    private List<News> mNewsList;
    private long lastPos=-1;

    public NewsListAdapter(Context context,List<News> mNewsList){
        this.mContext=context;
        this.mNewsList=mNewsList;
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
