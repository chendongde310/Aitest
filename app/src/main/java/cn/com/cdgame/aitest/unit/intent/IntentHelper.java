package cn.com.cdgame.aitest.unit.intent;

import android.content.Context;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.cdgame.aitest.unit.bean.Intent;
import cn.com.cdgame.aitest.unit.bean.Monster;

/**
 * 作者：陈东
 * 日期：2017/8/10 0010 - 23:03
 */

public class IntentHelper {
    static IntentHelper helper;
    public Map<String, String> pronouns = new HashMap<>(); //代词库
    Map<String, String> intentcache = new HashMap<>();//把所有的样本归类
    Map<String, Intent> intentMap; //意图集合
    List<Monster> monsters;//怪物数据（暂时放这里测试）
    Map<String, String> nlpMap = new HashMap<>();

    private IntentHelper(Context context) {
        try {
            loadMonsterData(context);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void initHelper(Context context) {
        if (helper == null)
            helper = new IntentHelper(context);
    }

    public static IntentHelper helper() {
        return helper;
    }

    public Intent getIntent(String text) {

        return convert(text);

    }

    public boolean isHave(String key, String vaule) {
        return pronouns.get(key).equals(nlpMap.get(vaule));
    }

    /**
     * 添加代词
     *
     * @param key
     * @param content
     */
    public void putPronoun(String key, String content) {
        CustomDictionary.insert(key);
        pronouns.put(key, content);
    }

    public Intent convert(String text, Intent intent) {
        Map<String, String> pronounsMap = new HashMap<>();
        for (Term term : HanLP.segment(text)) {
            if (pronouns.containsKey(term.word)) {
                pronounsMap.put(pronouns.get(term.word), term.word);
                text = text.replace(term.word, pronouns.get(term.word));
            }
        }
        if (intent != null) {
            for (Intent.Samples sam : intent.samples.values()) {
                for (Intent.Samples.Nlp nlp : sam.nlps.values()) {
                    if (pronounsMap.containsKey(nlp.key)) {
                        for (Intent.Slot slot : intent.slots) {
                            if (slot.name.equals(nlp.slot)) {
                                slot.value = pronounsMap.get(nlp.key);
                            }
                        }
                    }
                }
            }
        }
        return intent;
    }

    /**
     * 转义
     *
     * @param text
     * @return
     */
    public Intent convert(String text) {
        Map<String, String> pronounsMap = new HashMap<>();
        for (Term term : HanLP.segment(text)) {
            if (pronouns.containsKey(term.word)) {
                pronounsMap.put(pronouns.get(term.word), term.word);
                text = text.replace(term.word, pronouns.get(term.word));
            }
        }
        Intent intent = intentMap.get(intentcache.get(text));
        if (intent != null) {
            for (Intent.Samples sam : intent.samples.values()) {
                for (Intent.Samples.Nlp nlp : sam.nlps.values()) {
                    if (pronounsMap.containsKey(nlp.key)) {
                        for (Intent.Slot slot : intent.slots) {
                            if (slot.name.equals(nlp.slot)) {
                                slot.value = pronounsMap.get(nlp.key);

                            }
                        }
                    }
                }
            }
        }
        return intent;
    }

    public void loadMonsterIntent(Context context) throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(context.getApplicationContext().getAssets().open("unit/intent/monster.xml"));
        intentMap = new HashMap<>();
        List<Element> intentE = document.getRootElement().elements("intent");
        for (int i1 = 0; i1 < intentE.size(); i1++) {
            Element intents = intentE.get(i1);
            Intent intent = new Intent();
            intent.name = intents.attributeValue("name");
            intent.depict = intents.attributeValue("depict");
            intent.mode = intents.attributeValue("mode");
            intent.reply = intents.attributeValue("reply");
            intent.error = intents.attributeValue("error");
            List<Intent.Slot> slots = new ArrayList<>();
            List<Element> slotsE = intents.element("slot").elements("item");
            for (int i = 0; i < slotsE.size(); i++) {
                Element slotE = slotsE.get(i);
                Intent.Slot slot = new Intent.Slot();
                slot.name = slotE.attributeValue("name");
                slot.depict = slotE.attributeValue("depict");
                slot.error = slotE.attributeValue("error");
                slot.lib = slotE.attributeValue("lib");
                slot.needful = slotE.attributeValue("needful");
                List<String> clarifysList = new ArrayList<>();
                for (Element clarifysE : slotE.elements("a")) {
                    clarifysList.add(clarifysE.getTextTrim());
                }
                slot.clarifys.addAll(clarifysList);
                slots.add(slot);
            }
            intent.slots.addAll(slots);
            Map<String, Intent.Samples> samples = new HashMap<>();
            List<Element> samplessE = intents.element("samples").elements("sample");
            for (int i = 0; i < samplessE.size(); i++) {
                Element samplesE = samplessE.get(i);

                Intent.Samples sam = new Intent.Samples();
                sam.name = samplesE.attributeValue("name");
                Map<String, Intent.Samples.Nlp> nlps = new HashMap<>();
                List<Element> elements = samplesE.elements("nlp");
                for (int i2 = 0; i2 < elements.size(); i2++) {
                    Element nlpE = elements.get(i2);
                    Intent.Samples.Nlp nlp = new Intent.Samples.Nlp();
                    nlp.key = nlpE.getTextTrim();
                    nlp.slot = nlpE.attributeValue("slot");
                    nlps.put(nlp.key, nlp);
                    nlpMap.put(nlp.slot, nlp.key);
                }
                sam.nlps = nlps;
                samples.put(sam.name, sam);
                intentcache.put(sam.name, intent.name);
            }
            intent.samples.putAll(samples);
            intentMap.put(intent.name, intent);
        }
    }

    public Map<String, String> getNlpMap() {
        return nlpMap;
    }

    public void loadMonsterData(Context context) throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(context.getApplicationContext().getAssets().open("unit/data/monster.xml"));
        monsters = new ArrayList<>();
        for (Element mE : document.getRootElement().elements("item")) {
            Monster monster = new Monster();
            monster.id = mE.attributeValue("id");
            monster.name = mE.attributeValue("name");
            putPronoun(monster.name, "@monster_name");
            monster.loc = mE.attributeValue("loc");
            putPronoun(monster.loc, "@monster_loc");
            monster.lv = mE.attributeValue("lv");
            putPronoun(monster.lv, "@monster_lv");
            monsters.add(monster);
        }
        loadMonsterIntent(context);
    }

    public List<Monster> getMonsters() {
        return monsters;
    }
}
