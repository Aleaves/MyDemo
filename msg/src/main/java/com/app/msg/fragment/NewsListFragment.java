package com.app.msg.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.app.msg.R;
import com.app.msg.adapter.AutoLoadOnScrollListener;
import com.app.msg.adapter.NewsListAdapter;
import com.app.msg.bean.News;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/3/28.
 */

public class NewsListFragment extends BaseFragment implements PullToRefreshView.OnRefreshListener{


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    public static NewsListFragment newInstance(){
        return new NewsListFragment();
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

        init();

    }

    private void init(){

    }

    @Override
    public void onRefresh() {

    }
}
