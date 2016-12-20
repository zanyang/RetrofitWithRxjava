/**
 * This is free and unencumbered software released into the public domain.
 * <p/>
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 * <p/>
 * For more information, please refer to <http://unlicense.org/>
 */

package com.sangebaba.util;

import android.util.Log;

/**
 * @author Mustafa Ferhan Akman
 *         <p/>
 *         Create a simple and more understandable Android logs.
 * @date 21.06.2012
 */

public class DebugLog {

    static String className;
    static String methodName;
    static int lineNumber;

    private DebugLog() {
        /* Protect from instantiations */
    }

    public static void e(String message) {

        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));
    }


    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    private static String createLog(String log) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        buffer.append(methodName);
        buffer.append(":");
        buffer.append(lineNumber);
        buffer.append("]");
        buffer.append(log);

        return buffer.toString();
    }

    public static void i(String message) {

        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message) + " " + Thread.currentThread().getName());
    }

    public static void d(String message) {

        getMethodNames(new Throwable().getStackTrace());
        Log.d(className, createLog(message) + " " + Thread.currentThread().getName());
    }

    public static void v(String message) {

        getMethodNames(new Throwable().getStackTrace());
        Log.v(className, createLog(message) + " " + Thread.currentThread().getName());
    }

    public static void w(String message) {

        getMethodNames(new Throwable().getStackTrace());
        Log.w(className, createLog(message) + " " + Thread.currentThread().getName());
    }

    public static void wtf(String message) {

        getMethodNames(new Throwable().getStackTrace());
        Log.wtf(className, createLog(message) + " " + Thread.currentThread().getName());
    }

}
