package com.app.news.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.app.news.R;
import com.app.news.adapter.AutoLoadOnScrollListener;
import com.app.news.adapter.NewsListAdapter;
import com.app.news.bean.News;
import com.app.news.bean.NewsList;
import com.app.news.utils.RetrofitManager;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.List;

import butterknife.Bind;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by llb on 2016/3/28.
 */
public class NewsListFragment extends BaseFragment implements PullToRefreshView.OnRefreshListener{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.rcv_news_list)
    RecyclerView mRcvNewsList;
    @Bind(R.id.ptr_news_list)
    PullToRefreshView mPtrNewsList;
    @Bind(R.id.tv_load_empty)
    TextView mTvLoadEmpty;
    @Bind(R.id.tv_load_error)
    TextView mTvLoadError;
    @Bind(R.id.pb_loading)
    ContentLoadingProgressBar mPbLoading;

    private Snackbar mLoadLatestSnackbar;
    private Snackbar mLoadBeforeSnackbar;

    private NewsListAdapter mNewsListAdapter;

    private String curDate;
    private AutoLoadOnScrollListener mAutoLoadListener;

    private String date;

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        init();
        loadLatestNews();
    }

    private void init(){
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        mPtrNewsList.setOnRefreshListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRcvNewsList.setLayoutManager(linearLayoutManager);
        mRcvNewsList.setHasFixedSize(true);
        mRcvNewsList.setItemAnimator(new DefaultItemAnimator());
        mNewsListAdapter = new NewsListAdapter(getActivity(),null);
        mRcvNewsList.setAdapter(mNewsListAdapter);
        mAutoLoadListener=new AutoLoadOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {

                loadBeforeNews();

            }
        };

        mRcvNewsList.addOnScrollListener(mAutoLoadListener);

    }

    private void loadLatestNews(){
        showProgress();
        Call<NewsList> searchResult=RetrofitManager.getInstance().getRestApi().getLatestNews();
        searchResult.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Response<NewsList> response, Retrofit retrofit) {
                hideProgress();
                mPtrNewsList.setRefreshing(false);
                date=response.body().getDate();
                mNewsListAdapter.changeData(response.body().getStories());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void loadBeforeNews(){
        Call<NewsList> searchResult=RetrofitManager.getInstance().getRestApi().getBeforeNews(date);
        searchResult.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Response<NewsList> response, Retrofit retrofit) {
                mAutoLoadListener.setLoading(false);
                date=response.body().getDate();
                mNewsListAdapter.addData(response.body().getStories());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void onRefresh() {
        loadLatestNews();
    }

    public void showProgress() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mPbLoading.setVisibility(View.GONE);
    }

}
