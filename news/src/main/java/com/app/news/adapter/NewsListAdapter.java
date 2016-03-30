package com.app.news.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.news.NewsDetailActivity;
import com.app.news.R;
import com.app.news.bean.News;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/28.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>{

    private Context mContext;
    private List<News> mNewsList;
    private long lastPos = -1;

    public NewsListAdapter(Context context,List<News> newsList){
        this.mContext=context;
        this.mNewsList=newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final News news = mNewsList.get(position);
        if (news == null) {
            return;
        }
        holder.mTvTitle.setText(news.getTitle());
        List<String> images = news.getImages();
        if (images != null && images.size() > 0) {
            Glide.with(mContext).load(images.get(0)).placeholder(R.drawable.ic_placeholder).into(holder.mIvNews);
        }
        if (!news.isRead()) {
            holder.mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.color_unread));
        } else {
            holder.mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.color_read));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailActivity.start(mContext,news);
            }
        });

        startAnimator(holder.mCvItem, position);
    }

    @Override
    public int getItemCount() {
        return mNewsList == null ? 0 : mNewsList.size();
    }

    private void startAnimator(View view,long position){
        if(position>lastPos){
            view.startAnimation(AnimationUtils.loadAnimation(this.mContext,R.anim.item_bottom_in));
            lastPos = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(NewsViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.mCvItem.clearAnimation();
    }

    public void changeData(List<News> newsList) {
        mNewsList = newsList;
        notifyDataSetChanged();
    }

    public void addData(List<News> newsList) {
        if (mNewsList == null) {
            changeData(newsList);
        } else {
            mNewsList.addAll(newsList);
            notifyDataSetChanged();
        }
    }

    public List<News> getmNewsList() {
        return mNewsList;
    }


    class NewsViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.cv_item)
        CardView mCvItem;

        @Bind(R.id.iv_news)
        ImageView mIvNews;

        @Bind(R.id.tv_title)
        TextView mTvTitle;


        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
