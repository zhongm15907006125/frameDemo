package com.example.wangyi.ProxyTest;

import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void test(){
        Api api = new ApiImpl();
        Api apiProxy = (Api) Proxy.newProxyInstance(Api.class.getClassLoader(),new Class[]{Api.class},new ProxyApi(api));
        apiProxy.setName("123");
    }
}
