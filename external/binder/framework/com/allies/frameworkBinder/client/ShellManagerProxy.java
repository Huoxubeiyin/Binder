package com.allies.frameworkBinder.client;


import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by rememberme on 2018/7/17.
 * <p>
 * Email：891904833@qq.com
 * <p>
 * Description ：客户端 Binder 的代理类实现
 */
public class ShellManagerProxy implements IShellManager {

    private android.os.IBinder mRemote;  //代表BpBinder

    public ShellManagerProxy(android.os.IBinder remote) {
        mRemote = remote;
    }

    public java.lang.String getInterfaceDescriptor() {
        return DESCRIPTOR;
    }

    // Client 请求会首先走到这里，进行远程 Binder 调用开始
    @Override
    public String doShellCommand(String str) throws RemoteException {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        String ret;
        try {
            // Binder下的数据传输都需要通过 DESCRIPTOR 来校验的
            _data.writeInterfaceToken(DESCRIPTOR);
            // 写入 Parcel
            _data.writeString(str);
            // 通过 Binder 底层驱动返回的代理执行请求
            mRemote.transact(TRANSACTION_doShellCommand, _data, _reply, 0);
            ret = _reply.readString();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
        return ret;
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }
}
