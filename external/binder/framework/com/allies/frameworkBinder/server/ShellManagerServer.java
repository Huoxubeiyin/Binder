package com.allies.frameworkBinder.server;


import android.os.Looper;
import android.os.ServiceManager;


/**
 * Created by rememberme on 2018/7/17.
 * <p>
 * Email：891904833@qq.com
 * <p>
 * Description ：
 */
public class ShellManagerServer {
    public static void main(String[] args) {
        System.out.println("ShellManager Start");
        //Looper 循环准备
        Looper.prepareMainLooper();
        //设置为前台优先级
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_FOREGROUND);
        //注册服务，运行具体的 ShellManager
        ServiceManager.addService("ShellManager", new ShellManager());
        // 执行循环
        Looper.loop();
    }
}
