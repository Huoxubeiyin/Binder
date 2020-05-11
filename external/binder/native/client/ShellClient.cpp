//
// Created by 陈裕达 on 2020/5/9.
//

#include "../common/IShellManager.h"

#include <binder/IInterface.h>
#include <binder/IServiceManager.h>
#include <binder/Parcel.h>
#include <utils/RefBase.h>
#include <utils/StrongPointer.h>
#include <utils/String16.h>


#include <stdio.h>

using namespace android;

#define SHELL_MANAGER "allies.ShellManager"

int main(){

    // 通过 sm，获取 ShellManagerName 服务，返回的是通用的 IBinder 接口的强引用
    sp<IBinder> binder = defaultServiceManager()->getService(String16(SHELL_MANAGER));
    if (binder == nullptr) {
        printf("getService %s is null! \n", SHELL_MANAGER);
        return -1;
    }

    // 通过 IShellManage 的接口 asInterface 将通用的 IBinder 接口转换成 IShellManager 接口
    sp<IShellManager> iShellManager = IShellManager::asInterface(binder);
    printf("ShellClient doShellCommand pwd \n");
    // 调用 doShellCommand
    int32_t ret = iShellManager->doShellCommand("pwd");
    printf("ShellManager return %d \n", ret);

    return 0;

}

