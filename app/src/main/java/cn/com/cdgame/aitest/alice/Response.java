package cn.com.cdgame.aitest.alice;

import android.text.TextUtils;

import com.hankcs.hanlp.HanLP;

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

    public Response(String[] requests,Alice alice) {
        stringBuffer = new StringBuffer();
        for (int i = 0; i < requests.length; i++) {
            Respond.Item item =matching(requests[i],alice.aliceR);
            if (item != null) {

                String s = item.outputList.get(random(item.outputList.size())).output;

                stringBuffer.append((stringBuffer.length() > 0 ? "," : "") + escape(s,alice) );
            }
        }
    }

    /**
     * 转义
     *
     * @param s
     * @param alice
     */
    private String escape(String s, Alice alice) {
        return s.replace("@call", "朋友")
                .replace("@name",alice.name)
                .replace("@job",alice.job.getJobName());
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
      //  List<Term> terms = HanLP.segment(request);
//        System.out.println(HanLP.segment(request));

        for (int i = 0; i < aliceR.items.size(); i++) {
            if (!TextUtils.isEmpty(aliceR.items.get(i).input)){
                Pattern p = Pattern.compile(aliceR.items.get(i).input);
                List<String> keyList = HanLP.extractKeyword(request,1);
                Matcher m =  p.matcher(keyList.size()>0?keyList.get(0):request);
                if (m.find()) {
                    return aliceR.items.get(i);
                }
            }
        }
        return null;
    }
}
