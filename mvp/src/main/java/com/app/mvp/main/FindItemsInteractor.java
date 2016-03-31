package com.app.mvp.main;

import java.util.List;

/**
 * Created by Administrator on 2016/3/31.
 */
public interface FindItemsInteractor {

    interface OnFinishedListener {
        void onFinished(List<String> items);
    }

    void findItems(OnFinishedListener listener);

}
