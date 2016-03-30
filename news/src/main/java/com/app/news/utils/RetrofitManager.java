package com.app.news.utils;

import com.app.news.bean.RestApi;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Administrator on 2016/3/29.
 */
public class RetrofitManager {

    public static RetrofitManager retrofitManager=null;

    public static RestApi restApi;

    public static RetrofitManager getInstance(){
        if(retrofitManager==null){
            synchronized (RetrofitManager.class){
                if(retrofitManager==null){
                    retrofitManager=new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    private RetrofitManager(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        restApi=retrofit.create(RestApi.class);
    }

    public RestApi getRestApi(){
        return restApi;
    }
}
