package com.app.news.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/29.
 */
public class ApiBean<T> implements Serializable {
    //结果
    public int code;
    //数据
    public T data;
}
