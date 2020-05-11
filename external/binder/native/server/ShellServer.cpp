//
// Created by 陈裕达 on 2020/5/9.
//


#include "../common/IShellManager.h"

#include <binder/IInterface.h>
#include <binder/IServiceManager.h>
#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/Parcel.h>
#include <utils/RefBase.h>
#include <utils/String16.h>
#include <utils/StrongPointer.h>

#include <stdio.h>

using namespace android;

#define SHELL_MANAGER "allies.ShellManager"

// IShellManager 的实现类，最终实现接口逻辑
class ShellManager : public BnShellManager {
public:
    ShellManager() {
        printf("ShellManager create! \n");
    }

    ~ShellManager() {
        printf("ShellManager destory! \n");
    };

    int32_t doShellCommand(const char* args){

        if (strcmp("pwd",args)==0){
            printf("ShellManager doShellCommand is %s \n",args);
            return 0;
        } else {
        	return -1;
        }
    }
};


int main(int argc, char **argv) {

    // 加入sm中
    defaultServiceManager()->addService(String16(SHELL_MANAGER),new ShellManager());
    // 开启线程池
    ProcessState::self()->startThreadPool();
    // 加入线程池
    IPCThreadState::self()->joinThreadPool();

    return 0;
}