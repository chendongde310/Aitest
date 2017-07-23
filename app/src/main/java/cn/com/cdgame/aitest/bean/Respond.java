package cn.com.cdgame.aitest.bean;


import java.util.ArrayList;
import java.util.List;

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/7/22 0022 - 2:29
 * 注释：回复
 */
public class Respond {

    public List<Item> items = new ArrayList<>();


    public static class Item {
        public String input;
        public String type;
        public List<Output> outputList = new ArrayList<>();


        public static class Output {

            public Condition condition;
            public String output;
        }
    }


}
