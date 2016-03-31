package com.app.mvp;

/**
 * Created by Administrator on 2016/3/31.
 */

public interface LoginView {

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
