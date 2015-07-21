#include <string.h>
#include <jni.h>
#include <stdio.h>
#include "com_util_Utils.h"
#include "file_operator.h"
#include<android/log.h>

/*
 * Class:     com_util_Utils
 * Method:    stringFromJNI
 * Signature: ()Ljava/lang/String;
 */
jstring JNICALL Java_com_util_Utils_stringFromJNI
  (JNIEnv *env, jclass thiz)
{
  return (*env)->NewStringUTF(env, "Hi, String from JNI,So beautifully.");
}

/*
 * Class:     com_util_Utils
 * Method:    unimplementedStringFromJNI
 * Signature: ()Ljava/lang/String;
 */
jstring JNICALL Java_com_util_Utils_unimplementedStringFromJNI
  (JNIEnv *env, jclass thiz)
{
  return (*env)->NewStringUTF(env, "Java_com_util_Utils_unimplementedStringFromJNI");
}

/*
 * Class:     com_util_Utils
 * Method:    calc
 * Signature: (II)I
 */
jint JNICALL Java_com_util_Utils_calc
  (JNIEnv *env, jclass thiz, jint a, jint b)
{
  return a + b;
}

/*
 * Class:     com_util_Utils
 * Method:    writeContentToFile
 * Signature: (Ljava/lang/String;)Z
 */
jboolean JNICALL Java_com_util_Utils_writeContentToFile
  (JNIEnv *env, jclass thiz, jstring strInfo)
{
    const char *nativeString = (*env)->GetStringUTFChars(env, strInfo, 0);
    int len=strlen(nativeString);

    int file = file_open("/sdcard/ndk_file_test.txt", O_CREAT|O_RDWR);
    file_write(file, nativeString, len);
    file_close(file);

    (*env)->ReleaseStringUTFChars(env, strInfo, nativeString);
    return 1;
}