package com.example.wp.wp_mvp_fragmentation.mvp.presenter;

import org.junit.Test;

/**
 * Created by wangpeng .
 */
public class TestDemo {

    @Test
    public void demo1() {
        String str = "wangpeng";
        int len = str.length();
        char[] array = str.toCharArray();
        for (int i = 0; i < len / 2; i++) {
            array[i] = str.charAt(len - 1 - i);
            array[len - 1 - i] = str.charAt(i);
        }
        System.out.print(new String(array));
    }
}