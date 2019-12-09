package com.example.quiz.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.quiz.util.LogUtils;

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.e("onBind");
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e("onCreate");
    }


    //办证   （模拟activity调用service中的方法）
    public void banZheng(int money){
        if (money > 1000){
            Toast.makeText(getApplicationContext(),"明天来取证",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"这点钱还想办事",Toast.LENGTH_SHORT).show();
        }
    }

    //定义中间人对象

    public class MyBinder extends Binder {

        public void callBanZheng(int money){
            banZheng(money);
        }
    }
}
