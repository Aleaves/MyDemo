package com.app.news.bean;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Administrator on 2016/3/29.
 */

public interface RestApi {

    @GET("stories/latest")
    Call<NewsList> getLatestNews();

    @GET("stories/before/{date}")
    Call<NewsList> getBeforeNews(@Path("date") String date);

    @GET("story/{id}")
    Call<NewsDetail> getNewsDetail(@Path("id")int id);


    @GET("stories/latest")
    Call<JSONObject> getNews();

}
