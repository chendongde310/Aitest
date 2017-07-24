package cn.com.cdgame.aitest.alice;

import android.content.Context;

/**
 * 作者：陈东
 * 日期：2017/7/24 0024 - 2:51
 * 注释：这是一个真正的实体，A9,她是基于Alice创造出来的
 */
public class A9  {

    Alice A9;

    public A9(Context context ) {
        A9 = new Alice.Bulider(context)
                .loadDataXml("A9.xml")
                .build() ;
    }

    public Alice getA9() {
        return A9;
    }



}
