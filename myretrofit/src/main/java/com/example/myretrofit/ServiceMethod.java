package com.example.myretrofit;

import com.example.myretrofit.annotation.Field;
import com.example.myretrofit.annotation.GET;
import com.example.myretrofit.annotation.POST;
import com.example.myretrofit.annotation.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import okhttp3.Call;
import okhttp3.HttpUrl;

class ServiceMethod {

    final Call.Factory callFactory; //Okhttp的实现接口
    final HttpUrl httpUrl;//接口请求地址
    private String httpMethod;   //方法的请求方式   get post
    private String relativeUrl;   //方法注解的值
    private ParameterHandler[] parameterHandlers;//方法参数的数组。每个对象包括参数值，参数注解值
    private boolean hasBody;   //是否有请求体


    private ServiceMethod(Builder builder) {
        this.callFactory = builder.retrofit.getCallFavtory();
        this.httpUrl = builder.retrofit.getBaseUrl();
        this.httpMethod = builder.httpMethod;
        this.relativeUrl = builder.relativeUrl;
        this.parameterHandlers = builder.parameterHandlers;
        this.hasBody = hasBody;
    }

    public Call toCall(Object[] args) {
//拼装
        RequestBuilder requestBuilder = new RequestBuilder(httpMethod, httpUrl, relativeUrl, hasBody);
        ParameterHandler[] parameterHandlers = this.parameterHandlers;
        //要做一个检测，如果收集到的参数值数量长度和service里定义的不一样 那样肯定是不行的
        int argumentCount = args != null ? args.length : 0;
        if (argumentCount != parameterHandlers.length){
            throw new IllegalArgumentException("收集参数错误！");
        }
        for (int i = 0; i < argumentCount; i++) {
             parameterHandlers[i].apply(requestBuilder, (String) args[i]);
        }
        return callFactory.newCall(requestBuilder.build());
    }

    static final class Builder {

        private Retrofit retrofit;

        Method method;  //带注解的方法

        Annotation[] methodAnnotations; // 一个方法的所有注解

        Annotation[][] paramsAnnotationArray; //参数的注解，多个参数，一个参数多个注解

        private ParameterHandler[] parameterHandlers;//方法参数的数组。每个对象包括参数值，参数注解值

        private String httpMethod;   //方法的请求方式   get post

        private String relativeUrl;   //方法注解的值

        private boolean hasBody;   //是否有请求体

        //构建时存入数据
        Builder(Retrofit retrofit, Method method) {
            this.retrofit = retrofit;
            this.method = method;
            //方法的所有注解
            this.methodAnnotations = method.getAnnotations();
            //方法参数的所有注解
            this.paramsAnnotationArray = method.getParameterAnnotations();
        }

        ServiceMethod build() {
            //遍历方法的每一个注解  存在get或者post
            for (Annotation methodAnnotation : methodAnnotations) {
                parseMethodAnnotation(methodAnnotation);
            }
            int parameterCount = paramsAnnotationArray.length;  //实际上代表的是外层  参数的个数
            //初始化参数对象数组
            parameterHandlers = new ParameterHandler[parameterCount];
            for (int i = 0; i < parameterCount; i++) {
                Annotation[] parameterAnnotations = paramsAnnotationArray[i];   //每一个参数的对应的注解
                if (parameterAnnotations == null) {
                    throw new IllegalArgumentException("参数注解不存在");
                }
                parameterHandlers[i] = parsePrarmeter(parameterAnnotations);
            }

            return new ServiceMethod(this);
        }

        /**
         * 解析每个参数的所有注解
         *
         * @param parameterAnnotations 参数的注解数组
         * @return
         */
        private ParameterHandler parsePrarmeter(Annotation[] parameterAnnotations) {
            ParameterHandler result = null;
            for (Annotation parameterAnnotation : parameterAnnotations) {
                ParameterHandler parameterHandler = parseParameterAnnotation(parameterAnnotation);
                if (parameterHandler == null){
                    continue;
                }
                result = parameterHandler;
            }
            return result;
        }

        /**
         * @param parameterAnnotation 而解析
         * @return
         */
        private ParameterHandler parseParameterAnnotation(Annotation parameterAnnotation) {
            if (parameterAnnotation instanceof Query) {
                Query query = (Query) parameterAnnotation;
                //参数名
                String name = query.value();
                return new ParameterHandler.Query(name);
            } else if (parameterAnnotation instanceof Field) {
                Field query = (Field) parameterAnnotation;
                String name = query.value();
                return new ParameterHandler.Field(name);
            }
            return null;
        }

        private void parseMethodAnnotation(Annotation methodAnnotation) {
            if (methodAnnotation instanceof GET) {
                //解析get方法。最后一个参数是看是否有请求体，get请求是没有请求体的
                parseHttpMethodPath("GET", ((GET) methodAnnotation).value(), false);
            } else if (methodAnnotation instanceof POST) {
                parseHttpMethodPath("POST", ((POST) methodAnnotation).value(), true);
            }
        }

        private void parseHttpMethodPath(String type, String value, boolean hasBody) {
            //将请求的方式赋值
            this.httpMethod = type;
            //("/api/login")
            this.relativeUrl = value;
            this.hasBody = hasBody;
        }
    }
}
