package cn.com.cdgame.aitest.modle;

import android.content.Context;

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/7/25 0025 - 1:04
 * 注释：
 */
public class ModleHandler {
    private static ModleHandler ourInstance;
    private JobModle jobModle;

    private ModleHandler(Context context) {
        jobModle = new JobModle(context.getApplicationContext());
    }

    public static JobModle Job() {
        return ourInstance.jobModle;
    }

    public static void initModle(Context context) {
        if (ourInstance == null)
            ourInstance = new ModleHandler(context);
    }


}
