package com.app.mydemo.mvp;

import com.app.mydemo.mvp.bean.UserBean;
import com.app.mydemo.mvp.bean.UserBiz;

/**
 * Created by Administrator on 2016/3/15.
 */

public class UserBizImpl implements UserBiz{

    private OnLoginListener listener;

    public UserBizImpl(OnLoginListener listener){
        this.listener=listener;
    }


    @Override
    public void login(UserBean login) {
        boolean status = false;
        String username,password;
        username = login.getUsername();
        password = login.getPassword();
        if (username != null && "asdf".equals(username))
            if (password != null && "123".equals(password))
                status = true;
        listener.loginStatus(status);
    }
}
