package com.example.rememberme.alipay_server;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

// 使用Messager接口实现单一跨进程通信，主线程统一处理
public class MessagerService extends Service {

    private static final String TAG = "MessagerService";

    class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            if (data != null) {
                String dataString = data.getString("data");
                Log.d(TAG, "handleMessage: " + dataString);
                Toast.makeText(getApplicationContext(),
                        "地瓜收到，地瓜收到",
                        Toast.LENGTH_LONG)
                        .show();
            }
            super.handleMessage(msg);
        }
    }

    // Message维持本地mHandler实例对象
    final Messenger messenger = new Messenger(new mHandler());

    public MessagerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return messenger.getBinder();
    }
}
