package com.app.mydemo.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.mydemo.mvp.bean.UserBean;

/**
 * Created by llb on 2016/3/15.
 */

public class LoginActivity extends AppCompatActivity implements LoginView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Class clazz=Class.forName("com.app.mydemo.mvp.bean.UserBean");
            UserBean bean=(UserBean)clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void clearUsername() {

    }

    @Override
    public void clearPassword() {

    }

    @Override
    public void showMsg(String msg) {

    }

}
