package com.example.wangyi.ProxyTest;

public class ApiImpl implements Api {
    @Override
    public String getName() {
        return "123";
    }

    @Override
    public void setName(String s) {
        System.out.println("自己打印名字："+s);
    }
}
