package cn.com.cdgame.aitest;

/**
 * Author：陈东
 * Time：2017/7/21 - 下午2:44
 * Notes:
 */

public class Alice {

    public static Alice A9;


    public static Alice getFristAlice() {
        return A9;
    }

    public String respond(String input) {

        Response response = respond(new Request(input));
        return response.toText();
    }

    private Response respond(Request request) {
        Response response = new Response(request.getRequest());


        return response;
    }
}
