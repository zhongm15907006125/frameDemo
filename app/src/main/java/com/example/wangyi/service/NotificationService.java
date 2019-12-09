package com.example.wangyi.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.wangyi.MainActivity;
import com.example.wangyi.R;

public class NotificationService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Notification notification = new Notification(R.drawable.ic_launcher_background, "通知栏的标题", System.currentTimeMillis());
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,new Intent(this, MainActivity.class),0);
//        notification.
    }
}
