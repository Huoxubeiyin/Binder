//
// Created by 陈裕达 on 2020/5/9.
//

#ifndef BINDER_ISHELLMANAGER_H
#define BINDER_ISHELLMANAGER_H


#include <binder/IInterface.h>
#include <binder/IBinder.h>
#include <binder/Binder.h>
#include <binder/Parcel.h>
#include <utils/RefBase.h>


#define SHELL_MANAGER "allies.ShellManager"


using namespace android;

// 公共 Binder 请求接口，Bp* 和 Bn* 都要实现
class IShellManager : public IInterface {
public:

    // 函数宏，实现于 IInterface
    DECLARE_META_INTERFACE(ShellManager);
    // 公共的函数请求接口
    virtual int32_t doShellCommand(const char *args) = 0;

};

// Bn* 将请求接口传递到了IShellManager中，实现 IShellManager 将自动实现其内部逻辑
class BnShellManager : public BnInterface<IShellManager> {
public:
    virtual status_t onTransact(uint32_t code, const Parcel &data, Parcel *reply, uint32_t flags);
};


#endif //BINDER_ISHELLMANAGER_H
