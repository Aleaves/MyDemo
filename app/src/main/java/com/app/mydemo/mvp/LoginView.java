package com.app.mydemo.mvp;

/**
 * Created by Administrator on 2016/3/15.
 */

public interface LoginView {

    public String getUsername();
    public String getPassword();
    public void clearUsername();
    public void clearPassword();
    public void showMsg(String msg);

}
