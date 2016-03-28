package com.app.mydemo.builder;

/**
 * Created by Administrator on 2016/3/24.
 */
public class MacBook extends Computer{

    public MacBook(){};

    @Override
    protected void setOs() {
        mOS="Windows 7";
    }
}
