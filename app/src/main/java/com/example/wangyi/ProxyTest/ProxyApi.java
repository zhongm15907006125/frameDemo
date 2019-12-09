package com.example.wangyi.ProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyApi implements InvocationHandler {
    Api api;

    public ProxyApi(Api api) {
        this.api = api;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("getName")) {
            System.out.println("先执行代理的getName方法");
                method.invoke(api,args);
        }else if (method.getName().equals("setName")){
            System.out.println("先执行代理的setName方法");
//            method.invoke(proxy,args);
            method.invoke(api,args);
        }
        System.out.println(proxy.getClass().getSimpleName());
        return "456";
    }
}
