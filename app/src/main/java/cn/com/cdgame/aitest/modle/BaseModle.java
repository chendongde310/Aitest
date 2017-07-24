package cn.com.cdgame.aitest.modle;

import android.content.Context;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：陈东
 * 日期：2017/7/24 0024 - 23:21
 */
public abstract class BaseModle<T> {

    public SAXReader reader = new SAXReader();


    private Map<String, T> datas = new HashMap<>();


    public BaseModle(Context context) {
        if (getDataPath() != null) {
            for (String aJOB_PATH : getDataPath()) {
                loadXml(getHostPath() + "/" + aJOB_PATH + ".xml", context);
            }
        }
    }


    protected abstract String getHostPath();


    abstract String[] getDataPath();


    void loadXml(String aJOB_PATH, Context context) {
        try {
            Document document = reader.read(context.getApplicationContext().getAssets().open(aJOB_PATH));
            List<Element> items = document.getRootElement().elements("item");
            for (Element e : items) {
                datas.put(e.attributeValue("id"), Xml2Bean(e));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new NullPointerException("加载xml数据失败,请检查jobPath");
        }
    }

    /**
     * 解析每个item
     *
     * @param e
     * @return
     */
    protected abstract T Xml2Bean(Element e);


    public T load(String id) {
        return datas.get(id);
    }
}
