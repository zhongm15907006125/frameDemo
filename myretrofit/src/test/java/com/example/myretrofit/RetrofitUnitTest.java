package com.example.myretrofit;

import com.example.myretrofit.annotation.GET;
import com.example.myretrofit.annotation.Query;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RetrofitUnitTest {

    interface HOST{
        @GET("/ip/ipNew")
        Call get(@Query("ip") String ip);
    }

    Retrofit retrofit = new Retrofit.Builder().baseUrl("")
            .callFavtory(new Call.Factory() {
                @Override
                public Call newCall(Request request) {
                    return null;
                }
            }).create();

}
