package com.example.wp.wp_mvp_fragmentation.mvp.presenter;

import org.junit.Test;

/**
 * Created by wangpeng .
 */
public class TestDemo {
    int[] arr = new int[]{564, 23, 42, 546, 2, 3, 4, 213};

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

    @Test
    public void demo2() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i;j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "--");
        }
    }
}