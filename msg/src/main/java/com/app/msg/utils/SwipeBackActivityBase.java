package com.app.msg.utils;

/**
 * Created by Administrator on 2016/3/28.
 */
public interface SwipeBackActivityBase {

    public abstract SwipeBackLayout getSwipeBackLayout();

    public abstract void setSwipeBackEnable(boolean enable);

    public abstract void scrollToFinishActivity();

}
