package com.example.wp.wp_mvp_fragmentation.mvp.presenter;

import org.junit.Test;

import java.util.jar.JarEntry;

/**
 * Created by wangpeng .
 */
public class TestDemo {
    @Test
    public void demo1() {
        String str = "wangpeng";
        int len = str.length();
        char[] arr = str.toCharArray();
        for (int i = 0; i < len / 2; i++) {
            arr[i] = str.charAt(len - 1 - i);
            arr[len - 1 - i] = str.charAt(i);
        }
//       StringBuffer bf = new StringBuffer();
//        for (int i = str.length()-1; i >=0 ; i--) {
//            bf.append(str.charAt(i));
//        }
        System.out.print(new String(arr));
    }

    @Test
    public void demo2() {
//冒泡排序
        int[] array = new int[]{21, 43, 2, 54, 23, 12, 11};
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "--");
        }
    }

    @Test
    public void demo3() {
//选择排序
        int[] array = new int[]{21, 43, 2, 54, 23, 12, 11};
        int minIndex = 0;
        for (int i = 0; i < array.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }

            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "--");
        }
    }
}