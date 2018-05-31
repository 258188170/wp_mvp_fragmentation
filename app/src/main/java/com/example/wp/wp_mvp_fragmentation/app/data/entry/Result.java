package com.example.wp.wp_mvp_fragmentation.app.data.entry;

/**
 * 按后台返回格式封装
 * 没用到
 * @param <T>
 */
public class Result<T> {

    int code;
    String msg;
    String message;
    T data;


}
