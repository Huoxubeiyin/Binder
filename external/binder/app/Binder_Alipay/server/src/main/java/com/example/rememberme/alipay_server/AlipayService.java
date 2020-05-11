package com.example.rememberme.alipay_server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Binder 服务主体
 */
public class AlipayService extends Service {

    private static final String TAG = "AlipayService";

    public AlipayService() {

        Log.d(TAG, "AlipayService: " + "服务已启动...");
    }

    // 绑定服务时候，新建Alipay服务
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TAG, "onBind: " + "服务已绑定...");
        return new Alipay();
    }


    // 继承自 AIDL 生成中间类 IAlipayInterface.Stub，实现远程代理类 Proxy 的请求
    class Alipay extends IAlipayInterface.Stub {
        @Override
        public PayInfo transact(PayInfo i) throws RemoteException {
            // client端请求本服务aidl支付接口
            return doPay(i);

        }
    }

    // 支付具体逻辑
    private PayInfo doPay(PayInfo i) {

        Log.d(TAG, "doPay: " + "支付服务已受理，支付成功！！！");
        Log.d(TAG, "请求数据: " + i.toString());

        PayInfo payInfo = new PayInfo();
        payInfo.setDesc("返回数据：" + i.getDesc())
                .setData("支付请求完成时间：" + "2018/7/12/16:38")
                .setErrorInfo("")
                .setLocal(i.getLocal())
                .setMoney(i.getMoney())
                .setOk(true);

        return payInfo;

    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

}
