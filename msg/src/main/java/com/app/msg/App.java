package com.app.msg;

import android.app.Application;
import android.content.Context;

/**
 * Created by llb on 2016/3/28.
 */
public class App extends Application{

    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext=this;
    }

    public static Context getContext(){
        return mApplicationContext;
    }


}
