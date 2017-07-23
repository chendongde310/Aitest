package cn.com.cdgame.aitest.alice;

/**
 * Author：陈东
 * Time：2017/7/21 - 下午2:44
 * Notes: 这是一个基础的人工智障，如果要创建新的人物，就继承他，他是所有智障的母亲
 *
 *
 */

public class Alice {



    public String respond(String input) {
        Response response = respond(new Request(input));
        return response.toText();
    }

    private Response respond(Request request) {
        Response response = new Response(request.getRequest());

        return response;
    }





}
