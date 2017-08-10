package cn.com.cdgame.aitest.unit.intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.cdgame.aitest.unit.bean.Intent;

/**
 * Author：陈东
 * Time：2017/8/10 - 下午4:20
 * Notes:代词  （@标记的单词）
 */

public class PronounHandler {
    private static final PronounHandler ourInstance = new PronounHandler();
    private static Map<String, List<String>> pronouns = new HashMap<>();

    private PronounHandler() {
    }

    public static PronounHandler getInstance() {
        return ourInstance;
    }


    public static void put(String key, String content) {
        List<String> contents;
        if (pronouns.containsKey(key)) {
            contents = pronouns.get(key);
            for (String c : contents) {
                if (c.equals(content)) {
                    return;
                }
            }
            contents.add(content);
        } else {
            contents = new ArrayList<>();
            contents.add(content);
            pronouns.put(key, contents);
        }
    }


    public static Map<String, Intent.Samples> loop(Map<String, Intent.Samples> samplesMap) {
        Map<String, Intent.Samples> map = new HashMap<>();
        for (Intent.Samples samples:samplesMap.values()){

            for (Intent.Samples.Nlp nlp:samples.nlps.values()) {
                if(pronouns.containsKey(nlp.key)) {
                    for (String content : pronouns.get(nlp.key)) {
                        Intent.Samples.Nlp newsnlp = new Intent.Samples.Nlp();
                        newsnlp.key = content ;
                        newsnlp.slot = nlp .slot;
                    }
                }
            }
        }
        return samplesMap;
    }


}
