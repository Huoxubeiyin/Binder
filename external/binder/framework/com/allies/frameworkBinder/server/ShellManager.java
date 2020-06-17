package com.allies.frameworkBinder.server;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by rememberme on 2018/7/17.
 * <p>
 * Email：891904833@qq.com
 * <p>
 * Description ：ShellManager 的具体逻辑
 *  1。 继承 Binder，实现 IShellManager 接口
 */
public class ShellManager extends Binder implements IShellManager {

    static final String SHELL_MANAGER = "ShellManager";

    public ShellManager() {
        // 构造时候，对服务描述符进行绑定
        this.attachInterface(this, DESCRIPTOR);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    /** 将 IBinder 转换为 IShellManager 接口 **/
    public static IShellManager asInterface(android.os.IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iInterface = obj.queryLocalInterface(DESCRIPTOR);
        if (((iInterface != null)&&(iInterface instanceof IShellManager))){
            return ((IShellManager) iInterface);
        }
        return null;
    }

    /** 服务端，接收远程 Proxy 请求，处理 onTransact 方法 **/
    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_doShellCommand: {
                // 请求验证
                data.enforceInterface(DESCRIPTOR);
                String str = data.readString();
                // 转接 Binder 请求到本地实现
                String ret = doShellCommand(str);
                reply.writeString(ret);
                return true;
            }}
        return super.onTransact(code, data, reply, flags);
    }

    /** ShellManager 具体逻辑实现 **/
    @Override
    public String doShellCommand(String str) {
        System.out.println("ShellManager doShellCommand： " + str);
        return "ShellManager: doShellCommand success!!!";
    }
}
