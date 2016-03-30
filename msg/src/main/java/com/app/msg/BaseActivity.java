package com.app.msg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.msg.utils.SwipeBackActivity;
import com.app.msg.utils.SwipeBackLayout;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/28.
 */
public abstract class BaseActivity extends AppCompatActivity{
    //protected SwipeBackLayout mSwipeBackLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //mSwipeBackLayout = getSwipeBackLayout();
        //mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        ButterKnife.bind(this);
        afterCreate(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);
}
