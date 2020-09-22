//
// Created by mac on 2020-09-21.
//

#ifndef FF001_JAVALISTENER_H
#define FF001_JAVALISTENER_H

#include <jni.h>
class JavaListener {

public:

    JavaVM *jvm;
    jobject jobj;
    _JNIEnv *jniEnv;


    jmethodID jmid;



public:
    JavaListener(JavaVM *vm,_JNIEnv *env,jobject obj);
    ~JavaListener();

    /**
     * 1:主线程
     * 0:子线程
     * @param threadType
     * @param code
     * @param msg
     */
    void onError(int threadType,int code, const char *msg);

};


#endif //FF001_JAVALISTENER_H

#pragma clang diagnostic pop