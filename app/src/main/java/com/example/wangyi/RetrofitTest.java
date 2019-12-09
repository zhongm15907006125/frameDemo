package com.example.wangyi;

import com.example.myretrofit.Retrofit;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class RetrofitTest {

    public static void test() {
//        retrofit2.Call
//        http://whois.pconline.com.cn/ipJson.jsp?ip=xxx.xxx.xxx.xxx&json=true
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://whois.pconline.com.cn").create();
        ApiServer apiServer = retrofit.create(ApiServer.class);

        Call call = apiServer.get("192.168.1.103","true");

        try {
            Response execute = call.execute();
            System.out.println(execute.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
