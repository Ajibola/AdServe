package net.texsoftware.adservelibrary.utils;

import android.os.Build;
import android.util.Log;

import net.texsoftware.adservelibrary.BuildConfig;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static final long eventLogId = 0x313b8bdd364674cdL;

    // Used to format dates into a standard format
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss.SSS");

    public static void out(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e("<" + eventLogId + ">", setUpMessageString(msg));
        }
    }

    public static void err(Throwable ex) {
        err(ex.getMessage(), ex);
    }

    public static void err(String msg, Throwable ex) {
        if (BuildConfig.DEBUG) {
            Log.e("<" + eventLogId + ">", setUpMessageString(msg));
        }
    }

    private static String setUpMessageString(String msg) {
        Date timestamp = new Date();
        StringBuffer sb = new StringBuffer();

        try {
            sb.append("***");
            sb.append("AdServe Library");
            sb.append(" - ");
            sb.append(timestamp.getTime());
            sb.append(": ");
            sb.append(msg);
        } catch (Exception ex) {
            Logger.err(ex);
        }
        return sb.toString();
    }
}
