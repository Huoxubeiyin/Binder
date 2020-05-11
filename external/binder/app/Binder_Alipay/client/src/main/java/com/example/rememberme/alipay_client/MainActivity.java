package com.example.rememberme.alipay_client;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rememberme.alipay_server.IAlipayInterface;
import com.example.rememberme.alipay_server.PayInfo;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ALIPAY_CLIENT";
    private IAlipayInterface alipayInterface;
    private Messenger clientMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AIDL多线程跨进程通信
        Intent intent = new Intent();
        //通过意图过滤器建立关系
        intent.setAction("android.intent.action.alipayserver");
        //自从Android5.0开始通过setAction的方式已经失效，必须要传入应用包名
        intent.setPackage("com.example.rememberme.alipay_server");
        //绑定服务
        bindService(intent, new MyConn(), Service.BIND_AUTO_CREATE);

        Button alipay = findViewById(R.id.alipay);
        alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // 生成请求数据信息
                    PayInfo payInfo = new PayInfo();
                    payInfo.setMoney(7000)
                            .setLocal("广东省深圳市宝安区西乡大道...")
                            .setErrorInfo("")
                            .setDesc("用户请求支付宝服务支付接口：商品：苹果X（128g），红色国行")
                            .setData("2018/7/12/16:35")
                            .setOk(false);

                    // 调用远程服务接口支付
                    PayInfo callback = alipayInterface.transact(payInfo);

                    Log.d(TAG, "onClick: " + callback.toString());

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });


        // Messager单线程跨进程消息通信
        Intent intent1 = new Intent();
        //通过意图过滤器建立关系
        intent1.setAction("android.intent.action.messagerserver");
        //自从Android5.0开始通过setAction的方式已经失效，必须要传入应用包名
        intent1.setPackage("com.example.rememberme.alipay_server");
        //绑定服务
        bindService(intent1, new MessagerConn(), Service.BIND_AUTO_CREATE);

        Button messager = findViewById(R.id.messgaer);
        messager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 封装数据
                Bundle bundle = new Bundle();
                bundle.putString("data", "地瓜地瓜，我是土豆！收到请回答");
                Message message = Message.obtain();
                message.setData(bundle);
                try {
                    // 主线程发送消息
                    clientMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private class MessagerConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 通过绑定服务，会传服务端onbinder传递参数，使用Messager再次包装
            clientMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            Log.d(TAG, "onServiceConnected: " + componentName.getPackageName());
            Log.d(TAG, "onServiceConnected: " + "远程服务连接成功...");
            // 这里采用asInterface方式，不再强转形式
            alipayInterface = IAlipayInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: " + "远程服务已断开...");
            alipayInterface = null;
        }

        @Override
        public void onBindingDied(ComponentName name) {
            Log.d(TAG, "onBindingDied: " + "远程服务死亡通知...");
        }
    }
}
