package cn.com.cdgame.aitest.unit.bean;

import android.text.TextUtils;

import com.hankcs.hanlp.HanLP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.cdgame.aitest.unit.intent.IntentHelper;

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

    public int needful; //当前需求阶段,从0开始。
    public Map<String, String> dataMap; //已知的代词
    private boolean isContextMode;//上下文模式
    private Slot contextSlot; //当前上下文词槽

    public Intent() {
        name = "Not_Intent";
        depict = "没有意图";
        error = "不知道";
        mode = "text";
        reply = "";
        slots = new ArrayList<>();
        samples = new HashMap<>();

        needful = 0;
        isContextMode = false;
    }

    public String run(String request, IntentCallback intentCallback) {
        if (isContextMode) {
            if ("不知道".equals(request)) {
                needful++;

            } else {
                List<String> list = HanLP.extractKeyword(request, 1);
                if (list != null && list.size() > 0) {
                    contextSlot.value = list.get(0);
                } else {
                    contextSlot.value = request;
                }

            }
            isContextMode = false;
            // IntentHelper.helper().convert(request, this);
            return run(request, intentCallback);
        } else {
            boolean flag = true ;
            for (Slot slot : slots) {
                if (String.valueOf(needful).equals(slot.needful)) {
                    flag = false;
                    if (TextUtils.isEmpty(slot.value)) {
                        isContextMode = true;
                        contextSlot = slot;
                        return slot.clarifys.get(random(slot.clarifys.size()));
                    }
                }
            }
            intentCallback.end();
            if(flag){
                return error;
            }
            if ("text".equals(mode)) {
                return reply;
            } else if ("function".equals(mode)) { //
                return runMethod(reply);
            } else if ("guide".equals(mode)) {
                return reply;
            } else {
                return error;
            }

        }
    }

    /**
     * 不应该在这里面写这个方法
     *
     * @param reply
     */
    @Deprecated
    private String runMethod(String reply) {
        if ("query_monster_info".equals(reply)) {
            return queryMonsterInfo();
        }
        return error;
    }

    /**
     * 查询怪物信息
     * fixme
     */
    private String queryMonsterInfo() {
        for (Monster monster : IntentHelper.helper().getMonsters()) {

        }
        return "查到了怪物信息******";
    }

    private int random(int size) {
        int index = (int) (Math.random() * size);
        if (index >= size) {
            index = size - 1;
        }
        return index;
    }

    public interface IntentCallback {
        void end();
    }

    /**
     * 词槽
     */
    public static class Slot {
        public String name; //词槽名称
        public String depict; //词槽描述
        public String error; //错误返回
        public String lib; //词槽引用词典
        public String needful; //需求阶段。（从0开始依次获取需求，如果所有0阶段需求满足就结束获取，不满足就再获取1阶段的需求，，1不满足再2阶梯，以此类推）
        public List<String> clarifys = new ArrayList<>(); //澄清话语

        public String value;//词槽填充的值
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
