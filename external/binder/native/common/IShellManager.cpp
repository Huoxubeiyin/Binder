//
// Created by 陈裕达 on 2020/5/9.
//

#define  LOG_TAG "IShellManager"

#include "IShellManager.h"

#include <stdio.h>

using namespace android;

enum {
    DO_SHELL_COMMAND = IBinder::FIRST_CALL_TRANSACTION,
};

// 实现 Binder 跨进程请求的的远程代理 Proxy
class BpShellManager : public BpInterface<IShellManager> {
public:
    BpShellManager(const sp <IBinder> &remote);

    int32_t doShellCommand(const char *args);
};

// Proxy 构造类，接受一个 IBinder 的强引用，必须继承自 BpInterface
BpShellManager::BpShellManager(const sp <IBinder> &remote) : BpInterface(remote) {
    printf("BpShellManager construct! \n");
}

// Proxy 实现的请求接口，此接口需要在IShellManager中声明为纯虚函数，即 Bp* 和 Bn* 要连同此接口
int32_t BpShellManager::doShellCommand(const char *args) {
    printf("BpShellManager::doShellCommand %s \n", args);
    Parcel data, reply;
    data.writeInterfaceToken(String16(IShellManager::getInterfaceDescriptor()));
    data.write(args, strlen(args));
    remote()->transact(DO_SHELL_COMMAND, data, &reply);
    return reply.readInt32();
}

// 必须实现 IInterface 中的宏函数，内部实现了很多底层的函数宏
IMPLEMENT_META_INTERFACE(ShellManager, "allies.ShellManager");

// Bn* 类，即ShellManager的本地代理，负责接受 Proxy 类的请求，并将其传递到真正实现 IShellManager 接口的服务类
status_t BnShellManager::onTransact(uint32_t code, const Parcel &data, Parcel *reply, uint32_t flags){
    switch(code)
    {
        case DO_SHELL_COMMAND:{
            CHECK_INTERFACE(IShellManager, data, reply);
            const char *read = data.readCString();
            printf("BnShellManager onTransact %s \n", read);
            int32_t ret = doShellCommand(read);
            reply->writeInt32(ret);
            return NO_ERROR;
        }
        default:{
            return BBinder::onTransact(code, data, reply, flags);
        }
    }

}

