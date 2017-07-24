package cn.com.cdgame.aitest.modle;

import android.content.Context;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.util.List;

/**
 * Author：陈东
 * Time：2017/7/24 - 下午4:35
 * Notes:职业模型
 */

public class JobModle {


    public static void init(Context context ) {
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(context.getAssets().open("job/" +  "战士" + ".xml"));
            List<Element> items = document.getRootElement().elements("item");
            for (Element e : items) {

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new NullPointerException("加载xml数据失败,请检查jobPath");
        }

    }


}
