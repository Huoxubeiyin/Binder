package com.allies.frameworkBinder.server;

import android.os.IInterface;
import android.os.RemoteException;

/**
 * Created by rememberme on 2018/7/17.
 * <p>
 * Email：891904833@qq.com
 * <p>
 * Description ：实现IInterface接口
 */
public interface IShellManager extends IInterface {

    // Binder 描述符，验证接口有效性
    static final java.lang.String DESCRIPTOR = "allies.ShellManager";
    // 接口公共方法，ShellManagerProxy 和 ShellManager 都要实现
    public String doShellCommand(String str) throws RemoteException;
    // 方法对应的 int 值
    static final int TRANSACTION_doShellCommand = android.os.IBinder.FIRST_CALL_TRANSACTION;
}
