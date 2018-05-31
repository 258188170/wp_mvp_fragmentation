package com.example.wp.wp_mvp_fragmentation.app.config.lifecyclesOptioins;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wp.wp_mvp_fragmentation.BuildConfig;
import com.jess.arms.base.delegate.AppLifecycles;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.simple.eventbus.EventBus;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;
import timber.log.Timber;

/**
 * Created by wangpeng on 2018/5/31.
 */
public class MyAppLifecycles implements AppLifecycles {

    // LeakCanary观察器
    private RefWatcher mRefWatcher;

    @Override
    public void attachBaseContext(Context base) {
        //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    @Override
    public void onCreate(Application application) {
        initTimber();
        initLeakCanary(application);
        initFragmentation();
        initARouter(application);
    }

    private void initARouter(Application application) {
        if (BuildConfig.LOG_DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
            ARouter.printStackTrace(); // 打印日志的时候打印线程堆栈
        }
        ARouter.init(application);
    }

    private void initFragmentation() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出  默认NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                // 开发环境：true时，遇到异常："Can not perform this action after onSaveInstanceState!"时，抛出，并Crash;
                // 生产环境：false时，不抛出，不会Crash，会捕获，可以在handleException()里监听到
                .debug(BuildConfig.DEBUG)
                // 生产环境时，捕获上述异常（避免crash），会捕获
                // 建议在回调处上传下面异常到崩溃监控服务器
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }

    private void initLeakCanary(Application application) {
        //leakCanary内存泄露检查
        mRefWatcher = BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED;
    }

    private void initTimber() {
        if (BuildConfig.LOG_DEBUG) {
            //Timber日志打印
            Timber.plant(new Timber.DebugTree());
        }
    }

    public RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    public void setRefWatcher(RefWatcher refWatcher) {
        mRefWatcher = refWatcher;
    }

    @Override
    public void onTerminate(Application application) {

    }
}
