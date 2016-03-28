package com.app.mydemo.mvp;

import com.app.mydemo.mvp.bean.UserBean;
import com.app.mydemo.mvp.bean.UserBiz;

/**
 * Created by Administrator on 2016/3/15.
 */

public class LoginPresenterImpl implements LoginPresenter,OnLoginListener{

    private LoginView loginView;
    private UserBiz userBiz;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView=loginView;
        userBiz=new UserBizImpl(this);
    }


    @Override
    public void login() {
        UserBean userBean=new UserBean();
        userBean.setUsername(loginView.getUsername());
        userBean.setPassword(loginView.getPassword());
        userBiz.login(userBean);
    }

    @Override
    public void clear() {
        loginView.clearPassword();
        loginView.clearUsername();
    }

    @Override
    public void loginStatus(boolean status) {
        String msg;
        if (status)
            msg = "login succeed";
        else
            msg = "login failed";
        loginView.showMsg(msg);
    }

}
