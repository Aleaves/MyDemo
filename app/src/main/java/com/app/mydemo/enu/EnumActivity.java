package com.app.mydemo.enu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.mydemo.R;

/**
 * Created by Administrator on 2016/3/16.
 */

public class EnumActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enum);

       Light light=Light.RED;
        switch (light){
           case RED:
               break;
           case GREEN:
               break;
           case YELLOW:
               break;
       }

    }


    public enum Light{

        RED (1), GREEN (2), YELLOW (3);


        // 定义私有变量
        private int nCode ;

        private Light(int nCode){
            this.nCode=nCode;
        }

        @Override
        public String toString() {
            return String.valueOf(this.nCode);
        }
    }


}
