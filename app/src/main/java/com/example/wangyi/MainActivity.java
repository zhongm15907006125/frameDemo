package com.example.wangyi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.quiz.service.MyService;
import com.example.quiz.util.LogUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    View mNotificationService;
    MySrtviceConnect mySrtviceConnect = new MySrtviceConnect();
    MyService.MyBinder myBinder;
    MyVicewModel myVicewModel;
    Button mDebug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myVicewModel = ViewModelProviders.of(this).get(MyVicewModel.class);
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mySrtviceConnect, BIND_AUTO_CREATE);
        LogUtils.e("bindService");
        mDebug =  findViewById(R.id.test_debug);
        mNotificationService = findViewById(R.id.notification_service);
        mNotificationService.setOnClickListener(this);
        mDebug.setOnClickListener(this);
    }

    public void bindService(View view) {
        myBinder.callBanZheng(100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notification_service:
                break;
            case R.id.test_debug:
                add();
                Log.e("MainActivity","跳出执行方法");
                Log.e("MainActivity","1241541512");
//                end();
                break;
        }
    }

    private void end() {
        int i3213 = 11;
        int qweqw = 11;
        int qweqwe = 11;
        getData();
        Log.e("TAG","12312312312");
        Log.e("MainActivity","end");
        getData();
    }

    private void getData(){

    }

    private void add() {
        int i3213 = 11;
        int qweqw = 11;
        int qweqwe = 11;
        end();
        for (int i = 0; i < 10; i++) {
            Log.e("MainActivity","i = "+i);
        }
    }


    //定义一个类用于监听服务的状态
    private class MySrtviceConnect implements ServiceConnection {

        //当服务链接成功调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            myBinder = (MyService.MyBinder) service;
        }

        //失去连接调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mySrtviceConnect);
    }
}
