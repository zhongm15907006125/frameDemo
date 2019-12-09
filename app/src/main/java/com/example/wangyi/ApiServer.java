package com.example.wangyi;

import com.example.myretrofit.annotation.GET;
import com.example.myretrofit.annotation.Query;

import okhttp3.Call;


public interface ApiServer {

    @GET("/ipJson.jsp")
    Call get(@Query("ip") String ip, @Query("json")String json);
}
