package com.example.wp.wp_mvp_fragmentation.app.utils;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * StatusBar隐藏显示工具类
 */
public class SystemUiVisibilityUtil {
    public static void addFlags(View view, int flags) {
        view.setSystemUiVisibility(view.getSystemUiVisibility() | flags);
    }

    public static void clearFlags(View view, int flags) {
        view.setSystemUiVisibility(view.getSystemUiVisibility() & ~flags);
    }

    public static boolean hasFlags(View view, int flags) {
        return (view.getSystemUiVisibility() & flags) == flags;
    }

    /**
     * * 显示或隐藏StatusBar
     *
     * @param enable false 显示，true 隐藏
     */
    public static void hideStatusBar(Window window, boolean enable) {
        WindowManager.LayoutParams p = window.getAttributes();
        if (enable)
        //|=：或等于，取其一
        {
            p.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        } else
        //&=：与等于，取其二同时满足，     ~ ： 取反
        {
            p.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        window.setAttributes(p);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}
