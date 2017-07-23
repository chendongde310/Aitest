package cn.com.cdgame.aitest.bean;

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/7/22 0022 - 14:06
 * 注释：
 */
public class Condition {
    public String type; //分类
    public String judge; //判断

    public Condition(String condition) {
        if (condition != null) {
            String[] split = condition.split("_");
            type = split[0];
            judge = split[1];
        }
    }
}
