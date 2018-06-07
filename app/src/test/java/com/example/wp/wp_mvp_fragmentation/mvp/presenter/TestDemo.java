package com.example.wp.wp_mvp_fragmentation.mvp.presenter;

import org.junit.Test;

/**
 * Created by wangpeng .
 */
public class TestDemo {

    //翻转字符串
    @Test
    public void demo1() {
        String str = "wangpeng";
//        int len = str.length();
//        char[]array = str.toCharArray();
//        for (int i = 0; i < len / 2; i++) {
//            array[i] = str.charAt(len-1-i);
//            array[len-1-i] = str.charAt(i);
//        }
        StringBuffer buffer = new StringBuffer();
        for (int i = str.length() - 1; i >= 0; i--) {
            buffer.append(str.charAt(i));
        }
        System.out.print(buffer.toString());
    }
}