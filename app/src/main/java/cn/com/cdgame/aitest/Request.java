package cn.com.cdgame.aitest;

/**
 * Created by chen on 2017/7/21.
 */

public class Request {
    static char[] SPLITTERS = {'.', '?', ';', '!'};
    String[] sentences;
    private char[] chars;

    public Request(String input) {
        chars = input.toCharArray();
        sentences = input.split("\\.\\|\\?\\|;\\|!");

    }


    public String[] getRequest() {


        return sentences;
    }


}
