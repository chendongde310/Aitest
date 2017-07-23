package cn.com.cdgame.aitest.alice;

/**
 *
 * 作者：陈东
 * 日期：2017/7/22 0022 - 0:17
 * 请求
 */
public class Request {
    // static char[] SPLITTERS = {'.', '?', ';', '!'};
    String[] sentences;


    public Request(String input) {

        sentences = input.replace(".", ",").replace("?", ",").replace("!", ",").replace("？", ",")
                .replace("。", ",").replace("！", ",").replace("，", ",")
                .split(",");
    }


    public String[] getRequest() {

        return sentences;
    }


}
