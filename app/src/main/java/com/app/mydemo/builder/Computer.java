package com.app.mydemo.builder;

/**
 * Created by llb on 2016/3/24.
 */

public abstract class Computer {

    protected String mBoard;

    protected String mDisplay;

    protected String mOS;


    public Computer(){};

    protected void setBoard(String board){
        mBoard=board;
    }

    protected void setDisplay(String display){
        mDisplay=display;
    }

    protected abstract void setOs();



}
