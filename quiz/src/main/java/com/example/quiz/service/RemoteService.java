package com.example.quiz.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class RemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void methodService() {
        System.out.println("我是远程服务的方法");
    }

    private class MyBinder extends Binder implements IService {

        @Override
        public void callMethodService() {
            methodService();
        }
    }


}
