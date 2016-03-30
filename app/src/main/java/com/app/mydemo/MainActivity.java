package com.app.mydemo;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.app.mydemo.builder.Builder;
import com.app.mydemo.builder.Director;
import com.app.mydemo.builder.MacBookBuilder;
import com.app.mydemo.design.DesignActivity;
import com.app.mydemo.enu.EnumActivity;
import com.app.mydemo.permission.PermissionActivity;
import com.app.mydemo.recItem.RecActivity;
import com.app.mydemo.swipe.SwipeRefreshActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Builder builder=new MacBookBuilder();
        Director director=new Director(builder);
        director.construct("123","456");

    }

    @OnClick(R.id.ll_swipe)
    void toSwipe(){
        Intent intent=new Intent(this,SwipeRefreshActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_enum)
    void toEnum(){
        Intent intent=new Intent(this,EnumActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_permission)
     void toPermission(){
        Intent intent=new Intent(this, PermissionActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_design)
    void toDesign(){
        Intent intent=new Intent(this, DesignActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_rec)
    void toRec(){
        Intent intent=new Intent(this, RecActivity.class);
        startActivity(intent);
    }

}
