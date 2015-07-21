package com.util;

/**
 * Created by xblia2 on 7/15/15.
 */

/**
 * javah -d jni -classpath ../../build/intermediates/classes/debug/ com.util.Utils
 */

public class Utils {

    /* A native method that is implemented by the
     * 'hello-jni' native library, which is packaged
     * with this application.
     */
    public static native String  stringFromJNI();

    /* This is another native method declaration that is *not*
     * implemented by 'hello-jni'. This is simply to show that
     * you can declare as many native methods in your Java code
     * as you want, their implementation is searched in the
     * currently loaded native libraries only the first time
     * you call them.
     *
     * Trying to call this function will result in a
     * java.lang.UnsatisfiedLinkError exception !
     */
    public static native String  unimplementedStringFromJNI();

    /* this is used to load the 'hello-jni' library on application
     * startup. The library has already been unpacked into
     * /data/data/com.example.hellojni/lib/libhello-jni.so at
     * installation time by the package manager.
     */

    public static native int calc(int a, int b);

    public static native boolean writeContentToFile(String strInfo);

    static {
        System.loadLibrary("hello-jni");
    }
}
