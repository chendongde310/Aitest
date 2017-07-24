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
}
