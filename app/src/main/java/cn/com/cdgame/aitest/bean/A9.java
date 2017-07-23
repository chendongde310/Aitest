package cn.com.cdgame.aitest.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/7/22 0022 - 0:39
 * 注释：
 */
public class A9 {

    String name;
    String sex;
    Map<String, String> respond;


    public A9(String name) {
        this.name = name;
        this.sex = "女";
        respond = new HashMap<>();
        respond.put("你好","你好");
        respond.put("再见","再见");
        respond.put("早上好","早上好");
    }
}
