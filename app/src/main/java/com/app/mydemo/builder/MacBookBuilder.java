package com.app.mydemo.builder;

/**
 * Created by Administrator on 2016/3/24.
 */

public class MacBookBuilder extends Builder{
    Computer mComputer=new MacBook();

    @Override
    protected void buildBoard(String board) {
        mComputer.setBoard(board);
    }

    @Override
    protected void buildDisplay(String display) {
        mComputer.setDisplay(display);
    }

    @Override
    protected void buildOs() {
        mComputer.setOs();
    }
}
