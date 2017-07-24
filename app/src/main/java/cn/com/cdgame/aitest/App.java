package cn.com.cdgame.aitest;

import android.app.Application;

import cn.com.cdgame.aitest.modle.ModleHandler;

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/7/22 0022 - 2:23
 * 注释：
 */
public class App extends Application {

    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        ModleHandler.initModle(this);
    }
}
