package cn.com.cdgame.aitest.alice;

import android.content.Context;

import com.hankcs.hanlp.dictionary.CustomDictionary;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.util.List;

import cn.com.cdgame.aitest.bean.Condition;
import cn.com.cdgame.aitest.bean.Respond;

/**
 * Author：陈东
 * Time：2017/7/21 - 下午2:44
 * Notes: 这是一个基础的人工智障，如果要创建新的人物，就继承他，他是所有智障的母亲
 */

public class Alice {
    Context context;
    String name;
    String gender;
    String age;
    Job job;
    Emotion emotion;
    Friendliness friendliness;


    public Alice(Bulider b) {
        context = b.context;
        name = b.name;
        gender = b.gender;
        age = b.age;
        job = b.job;
        emotion = b.emotion;
        friendliness = b.friendliness;

    }

    public static class Bulider {
        Context context;
        String id;
        String name = "Alice Base";
        String gender = "男";
        String age;
        Job job = Job.base();
        Emotion emotion = Emotion.base();
        Friendliness friendliness = Friendliness.base();
        Respond aliceR;

        public Bulider(Context context) {
            this.context = context;
            this.id = "Alice" + System.currentTimeMillis();
        }

        public Bulider loadDataXml(String dataXmlPath) {
            try {
                SAXReader reader = new SAXReader();
                Document document = reader.read(context.getAssets().open(dataXmlPath));
                aliceR = new Respond();
                Element data = document.getRootElement().element("data");
                name  = data.elementText("name");
                gender  = data.elementText("gender");
                age  = data.elementText("age");
                job  = Job.loadXml(context,data.elementText("job"));
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
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new NullPointerException("加载xml数据失败,请检查dataXmlPath");
            }
            return this;
        }

        public Bulider name(String name) {
            this.name = name;
            return this;
        }

        public Bulider gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Bulider age(String age) {
            this.age = age;
            return this;
        }

        public Bulider job(Job job) {
            this.job = job;
            return this;
        }

        public Bulider emotion(Emotion emotion) {
            this.emotion = emotion;
            return this;
        }

        public Bulider friendliness(Friendliness friendliness) {
            this.friendliness = friendliness;
            return this;
        }


        public Alice build() {
            return new Alice(this);
        }


        private void loadXml() throws IOException, DocumentException {

        }


    }


}
