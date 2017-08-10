package cn.com.cdgame.aitest.unit.intent;

import android.content.Context;

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
 * Created by chen on 2017/8/10.
 */

public class IntentHelper {


    public Map<String, Intent> loadMonsterIntent(Context context) throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(context.getApplicationContext().getAssets().open("unit/intent/monster.xml"));
        Map<String, Intent> intentMap = new HashMap<>();
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
                }
                samples.put(sam.name, sam);
            }
            intent.samples.putAll(PronounHandler.loop(samples));
            intentMap.put(intent.name, intent);
        }
        return intentMap;
    }


    public void loadMonsterData(Context context) throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(context.getApplicationContext().getAssets().open("unit/data/monster.xml"));
        List<Monster> monsters = new ArrayList<>();
        for (Element mE : document.getRootElement().elements("item")) {
            Monster monster = new Monster();
            monster.id = mE.attributeValue("id");
            monster.name = mE.attributeValue("name");
            PronounHandler.put("@monster_name", monster.name);
            monster.loc = mE.attributeValue("loc");
            PronounHandler.put("@monster_loc", monster.loc);
            monster.lv = mE.attributeValue("lv");
            PronounHandler.put("@monster_lv", monster.lv);
            monsters.add(monster);
        }
        loadMonsterIntent(context);
    }


}
