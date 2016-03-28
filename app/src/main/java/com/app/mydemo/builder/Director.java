package com.app.mydemo.builder;

/**
 * Created by Administrator on 2016/3/24.
 */

public class Director {

    private  Builder mBuilder=null;

    public Director(Builder mBuilder){
        this.mBuilder=mBuilder;
    }

    public void construct(String board,String display){
        mBuilder.buildBoard(board);
        mBuilder.buildDisplay(display);
        mBuilder.buildOs();
    }

}
