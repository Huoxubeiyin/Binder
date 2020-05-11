// IAllipayInterface.aidl
package com.example.rememberme.alipay_server;
import com.example.rememberme.alipay_server.PayInfo;
// Declare any non-default types here with import statements

interface IAlipayInterface {
    PayInfo transact(in PayInfo i);
    void mTtransact(in PayInfo i);
}
