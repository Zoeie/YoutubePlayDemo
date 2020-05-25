package com.examples.yotubelistdemo.utils;

import android.util.Log;

import com.chad.library.BuildConfig;

/**
 * @author: cjl
 * @date: 2018/6/28 15:45
 * @desc: 日志工具类  打印出的日志格式：方法名(类名:行号)
 */

public abstract class LogUtil {

    private static String className;  //类名
    private static String methodName; //方法名
    private static int lineNumber;   //行数
    private static final int LEVEL_VERBOSE = 1;
    private static final int LEVEL_DEBUG = 2;
    private static final int LEVEL_INFO = 3;
    private static final int LEVEL_WARN = 4;
    private static final int LEVEL_ERROR = 5;
    private static boolean debug = true;

    private static void getLogInfo(StackTraceElement[] traceElements) {
        className = traceElements[1].getFileName();
        methodName = traceElements[1].getMethodName();
        lineNumber = traceElements[1].getLineNumber();
    }

    public static void v(String message) {
        getLogInfo(new Throwable().getStackTrace());
        String log = createLog(message, LEVEL_VERBOSE);
        if (debug) {
            Log.v(className, log);
        }
    }

    public static void d(String message) {
        getLogInfo(new Throwable().getStackTrace());
        String log = createLog(message, LEVEL_DEBUG);
        if (debug) {
            Log.d(className, log);
        }
    }

    public static void i(String message) {
        getLogInfo(new Throwable().getStackTrace());
        String log = createLog(message, LEVEL_INFO);
        if (debug) {
            Log.i(className, log);
        }
    }

    public static void w(String message) {
        getLogInfo(new Throwable().getStackTrace());
        String log = createLog(message, LEVEL_WARN);
        if (debug) {
            Log.w(className, log);
        }
    }

    public static void e(String message) {
        getLogInfo(new Throwable().getStackTrace());
        String log = createLog(message, LEVEL_ERROR);
        if (debug) {
            Log.e(className, log);
        }
    }

    private static String createLog(String log, int level) {
        //TODO 写入本地log
        return methodName + "(" + className + ":" + lineNumber + ")" + log;
    }

}
