package cn.com.cdgame.aitest.unit.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：陈东
 * Time：2017/8/10 - 下午2:58
 * Notes:意图
 */
public class Intent {
    public String name;  //意图名称
    public String depict;//意图描述
    public String error;//错误返回
    public String mode; //触发模式（text:回复；function:函数；guide:引导至其他意图）
    public String reply; //mode的内容
    public List<Slot> slots; //词槽集
    public Map<String, Intent.Samples> samples; //样本库

    public Intent() {
        name = "Not_Intent";
        depict = "没有意图";
        error = "";
        mode = "text";
        reply = "";
        slots = new ArrayList<>();
        samples = new HashMap<>();
    }

    /**
     * 词槽
     */
    public static class Slot {
        public String name; //词槽名称
        public String depict; //词槽描述
        public String error; //错误返回
        public String lib; //词槽引用词典
        public String needful; //需求阶段。（从0开始依次获取需求，如果所有0阶段需求满足就结束获取，不满足就再获取1阶段的需求，，1部满足再2阶梯，以此类推）
        public List<String> clarifys = new ArrayList<>(); //澄清话语


    }

    /**
     * 对话样本
     */
    public static class Samples {
        public String name;
        public Map<String, Nlp> nlps = new HashMap<>();

        public static class Nlp {
            public String key;
            public String slot;
        }
    }


}
