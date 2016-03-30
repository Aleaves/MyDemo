package com.app.news.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.news.NewsDetailActivity;
import com.app.news.R;
import com.app.news.bean.News;
import com.app.news.bean.NewsDetail;
import com.app.news.utils.HtmlUtil;
import com.app.news.utils.RetrofitManager;
import com.bumptech.glide.Glide;
import butterknife.Bind;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
/**
 * Created by Administrator on 2016/3/29.
 */
public class NewsDetailFragment extends BaseFragment{

    @Bind(R.id.iv_header)
    ImageView mIvHeader;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_source)
    TextView mTvSource;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.wv_news)
    WebView mWvNews;
    //@Bind(R.id.nested_view)
    //NestedScrollView mNestedView;
   /* @Bind(R.id.tv_load_empty)
    TextView mTvLoadEmpty;
    @Bind(R.id.tv_load_error)
    TextView mTvLoadError;
    @Bind(R.id.pb_loading)
    ContentLoadingProgressBar mPbLoading;*/

    private News mNews;

    public static Fragment newInstance(News news) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(NewsDetailActivity.KEY_NEWS, news);
        Fragment fragment = new NewsDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mNews = getArguments().getParcelable(NewsDetailActivity.KEY_NEWS);
        setHasOptionsMenu(true);
        init();
        loadData();
    }

    private void init(){
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void loadData(){
        Call<NewsDetail> newsDetail= RetrofitManager.getInstance().getRestApi().getNewsDetail(mNews.getId());

        newsDetail.enqueue(new Callback<NewsDetail>() {

            @Override
            public void onResponse(Response<NewsDetail> response, Retrofit retrofit){

                Glide.with(getActivity())
                        .load(response.body().getImage())
                        .into(mIvHeader);
                mTvTitle.setText(response.body().getTitle());
                mTvSource.setText(response.body().getImage_source());
                String htmlData = HtmlUtil.createHtmlData(response.body());
                mWvNews.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);

            }

            @Override
            public void onFailure(Throwable t) {

            }

        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detail, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.i("==========","back");
                return true;
            case R.id.menu_action_share:
                share();
                return true;
            case R.id.menu_action_about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_from) + mNews.getTitle() + "，http://daily.zhihu.com/story/" + mNews.getId());
        startActivity(Intent.createChooser(intent, mNews.getTitle()));
    }

}
