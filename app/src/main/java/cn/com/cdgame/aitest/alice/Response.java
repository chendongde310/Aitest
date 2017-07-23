package cn.com.cdgame.aitest.alice;

import cn.com.cdgame.aitest.bean.Respond;

/**
 * 作者：陈东
 * 日期：2017/7/21 0021 - 23:11
 * 返回
 */

public class Response {

    StringBuffer stringBuffer;

    public Response(String[] requests) {
        stringBuffer = new StringBuffer();
        for (int i = 0; i < requests.length; i++) {
            Respond.Item item = AliceHandler.getInstance().matching(requests[i]);
            if (item != null) {
                String s = item.outputList.get(0).output;
                stringBuffer.append(s+(stringBuffer.length()>0?",":""));
            }
        }
    }


    public String toText() {
        return stringBuffer.toString();
    }
}
