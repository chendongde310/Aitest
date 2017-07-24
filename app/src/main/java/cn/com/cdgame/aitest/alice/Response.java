package cn.com.cdgame.aitest.alice;

import android.text.TextUtils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.cdgame.aitest.bean.Respond;

/**
 * 作者：陈东
 * 日期：2017/7/21 0021 - 23:11
 * 返回
 */

public class Response {

    StringBuffer stringBuffer;

    public Response(String[] requests,Respond aliceR) {
        stringBuffer = new StringBuffer();
        for (int i = 0; i < requests.length; i++) {
            Respond.Item item =matching(requests[i],aliceR);
            if (item != null) {

                String s = item.outputList.get(random(item.outputList.size())).output;

                stringBuffer.append(escape(s) + (stringBuffer.length() > 0 ? "," : ""));
            }
        }
    }

    /**
     * 转义
     *
     * @param s
     */
    private String escape(String s) {
        return s.replace("@call", "朋友");
    }


    private int random(int size) {
        int index = (int) (Math.random() * size);
        if (index >= size) {
            index = size - 1;
        }
        return index;
    }


    public String toText() {
        return stringBuffer.toString();
    }



    /**
     * 匹配
     * @param request
     * @param aliceR
     * @return
     */
    public Respond.Item matching(String request, Respond aliceR) {
        List<Term> terms = HanLP.segment(request);
        System.out.println(terms);
        for (int i = 0; i < aliceR.items.size(); i++) {
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
