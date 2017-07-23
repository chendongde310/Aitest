package cn.com.cdgame.aitest.alice;

import android.text.TextUtils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.cdgame.aitest.App;
import cn.com.cdgame.aitest.bean.Condition;
import cn.com.cdgame.aitest.bean.Respond;

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/7/22 0022 - 14:26
 * 注释：
 */
public class AliceHandler {
    private static AliceHandler ourInstance;
    Respond aliceR;

    private AliceHandler() {
        try {
            loadXml();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    public static AliceHandler getInstance() {
        if (ourInstance == null) {
            ourInstance = new AliceHandler();
        }
        return ourInstance;
    }

    private void loadXml2() throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(App.app.getAssets().open("A9.xml"));
        aliceR = new Respond();
        for (Element e : document.getRootElement().elements("respond")) {
            Respond.Item item = new Respond.Item();
            item.input = e.attributeValue("input");
            item.type = e.attributeValue("type");
            for (Element out : e.elements("output")) {
                Respond.Item.Output output = new Respond.Item.Output();
                output.output = out.getTextTrim();
                output.condition = new Condition(out.attributeValue("condition"));
                item.outputList.add(output);
            }
            aliceR.items.add(item);
        }
    }

    public void loadXml() throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(App.app.getAssets().open("A9.xml"));
        aliceR = new Respond();
        List<Element> es = document.getRootElement().elements("respond").get(0).elements("item");
        for (int i = 0; i < es.size(); i++) {
            Respond.Item item = new Respond.Item();
            item.input = es.get(i).attributeValue("input");
            CustomDictionary.insert(item.input);
            item.type = es.get(i).attributeValue("type");
            List<Element> out = es.get(i).elements("output");
            for (int j = 0; j < out.size(); j++) {
                Respond.Item.Output output = new Respond.Item.Output();
                output.output = out.get(j).getTextTrim();
                output.condition = new Condition(out.get(j).attributeValue("condition"));
                item.outputList.add(output);
            }
            aliceR.items.add(item);
        }



    }

//[^x00-xff]

    public Respond.Item matching(String request) {
        List<Term> terms = HanLP.segment(request);
        System.out.println(terms);
        for (int i = 0; i < aliceR.items.size(); i++) {
           // Logger.d(aliceR.items.get(i).input + "-----" + terms.get(0).word);
            if (!TextUtils.isEmpty(aliceR.items.get(i).input)){
                Pattern p = Pattern.compile(aliceR.items.get(i).input);
                Matcher m = p.matcher(terms.get(0).word);
                if (m.find()) {
                    return aliceR.items.get(i);
                }
            }
        }
        return null;
    }


}
