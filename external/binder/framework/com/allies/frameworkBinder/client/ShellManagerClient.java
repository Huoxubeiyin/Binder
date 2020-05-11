package com.allies.frameworkBinder.client;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;


/**
 * Created by rememberme on 2018/7/17.
 * <p>
 * Email：891904833@qq.com
 * <p>
 * Description ：
 */
public class ShellManagerClient {

    public static void main(String[] args) throws RemoteException {
        System.out.println("Client start");
        // 通过 ServiceManager 返回的 Service 是一个 IBinder 类型的对象
        IBinder binder = ServiceManager.getService("ShellManager");
        // 将 IBinder 对象通过 ShellManagerProxy 转化为 IShellManager
        IShellManager shellManager = new ShellManagerProxy(binder);
        // 通过 IShellManager 对象调用接口的方法
        String ret = shellManager.doShellCommand("am start-service ...");
        System.out.println("ShellManagerClient return " + ret);
    }
}
