package com.app.mydemo.swipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.app.mydemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by llb on 2016/3/14.
 */

public class SwipeRefreshActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;
    @Bind(R.id.btn3)
    Button btn3;
    @Bind(R.id.btn4)
    Button btn4;
    @Bind(R.id.btn5)
    Button btn5;
    @Bind(R.id.btn6)
    Button btn6;
    @Bind(R.id.btn7)
    Button btn7;
    @Bind(R.id.btn8)
    Button btn8;
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.tv_state)
    TextView tv_state;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private SwipeAdapter mAdapter;
    int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        ButterKnife.bind(this);
        setLinstener();
        setRecyclerView(LinearLayoutManager.VERTICAL);

    }

    private void setLinstener(){
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                updateState(newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                String s = "";
                if (type == 0) {
                    s = "可见Item数量：" + linearLayoutManager.getChildCount() + "\n"
                            + "可见Item第一个Position：" + linearLayoutManager.findFirstVisibleItemPosition() + "\n"
                            + "可见Item最后一个Position：" + linearLayoutManager.findLastVisibleItemPosition();
                } else if (type == 1) {
                    s = "可见Item数量：" + gridLayoutManager.getChildCount() + "\n"
                            + "可见Item第一个Position："
                            + gridLayoutManager.findFirstVisibleItemPosition()
                            + "\n" + "可见Item最后一个Position："
                            + gridLayoutManager.findLastVisibleItemPosition();
                } else if (type == 2) {
                    s = "可见Item数量："
                            + staggeredGridLayoutManager.getChildCount();
                }
                tv.setText(s);
            }

        });
    }

    private void setRecyclerView(int oritation){
        recyclerView.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(this);
        switch (oritation){
            case LinearLayoutManager.VERTICAL:
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                break;
            case LinearLayoutManager.HORIZONTAL:
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                break;
            default:
                break;
        }
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter=new SwipeAdapter(this,getData());
        recyclerView.setAdapter(mAdapter);
    }

    private void setGridLayoutRecyclerView(){
        gridLayoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter=new SwipeAdapter(this,getData());
        recyclerView.setAdapter(mAdapter);
    }

    private void setStaggeredGridLayoutRecyclerView(){
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter=new SwipeAdapter(this,getData());
        recyclerView.setAdapter(mAdapter);
    }


    private void updateState(int scrollState){
        String stateName = "Undefined";
        switch (scrollState){
            case RecyclerView.SCROLL_STATE_IDLE:
                stateName="IDLE";
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                stateName="DRAGGING";
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                stateName="Flinging";
                break;
        }
        tv_state.setText("滑动状态：" + stateName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                type=0;
                setRecyclerView(LinearLayoutManager.VERTICAL);
                break;
            case R.id.btn2:
                type=0;
                setRecyclerView(LinearLayoutManager.HORIZONTAL);
                break;
            case R.id.btn3:
                mAdapter.addData("我的增加的item",1);
                break;
            case R.id.btn4:
                mAdapter.deleteData(1);
                break;
            case R.id.btn5:
                mAdapter.itemRangeInserted("我是连续增加的item",1,5);
                break;
            case R.id.btn6:
                mAdapter.itemRangeRemoved(1,5);
                break;
            case R.id.btn7:
                type=1;
                setGridLayoutRecyclerView();
                break;
            case R.id.btn8:
                type=2;
                setStaggeredGridLayoutRecyclerView();
                break;
        }
    }

    public List<String> getData(){
        List<String> lists=new ArrayList<String>();
        for (int i=0;i<100;i++){
            lists.add("我的世界"+i);
        }
        return lists;
    }
}
