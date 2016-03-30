package com.app.news;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.app.news.bean.News;
import com.app.news.bean.NewsList;
import com.app.news.bean.RestApi;
import com.app.news.bean.TopNews;
import com.app.news.fragment.NewsListFragment;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class NewsListActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_list;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        Fragment fragment = NewsListFragment.newInstance();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_container, fragment, NewsListFragment.TAG);
        transaction.commit();

        //net();
    }

    private void net(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        RestApi restApi=retrofit.create(RestApi.class);
        Call<NewsList> searchResult=restApi.getLatestNews();
        searchResult.enqueue(new Callback<NewsList>(){
            @Override
            public void onResponse(Response<NewsList> response, Retrofit retrofit) {
                Log.i("============",response.body().getDate());
                List<News> list=response.body().getStories();
                for (int i=0;i<list.size();i++){
                    Log.i("==========",list.get(i).getTitle()+"===="+list.get(i).getGa_prefix()+"===="+list.get(i).getId());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("==============",t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_about:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
