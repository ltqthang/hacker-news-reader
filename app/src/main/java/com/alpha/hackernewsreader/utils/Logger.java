package com.alpha.hackernewsreader.utils;

import com.alpha.hackernewsreader.BuildConfig;

/**
 * @author Thang
 * @since 6/5/17 10:56
 */
public class Logger {
    public static void log(Throwable throwable) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace();
        } else {
            // Report to Fabric or somewhere else
        }
    }
}
