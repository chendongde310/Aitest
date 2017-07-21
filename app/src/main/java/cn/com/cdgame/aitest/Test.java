package cn.com.cdgame.aitest;

import com.hankcs.hanlp.HanLP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by chen on 2017/7/21.
 */

public class Test {


    public static final String END = "bye";

    public static String input() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("you say>");
        String input = "";
        try {
            input = in.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return input;
    }

    public static void main(String[] args) throws Exception {
        HanLP.Config.ShowTermNature = false;

        Alice bot = Alice.getFristAlice();
        System.err.println("A9>" + HanLP.segment("你好"));
        while (true) {
            String input = Test.input();
            if (Test.END.equalsIgnoreCase(input))
                break;

            System.err.println("A9>" + bot.respond(input));
        }

    }


}
