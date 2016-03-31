package com.app.mvp.main;

import java.util.List;

/**
 * Created by llb on 2016/3/31.
 */
public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<String> items);

    void showMessage(String message);

}
