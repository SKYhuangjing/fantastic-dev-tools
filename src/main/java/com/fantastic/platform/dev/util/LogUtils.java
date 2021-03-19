package com.fantastic.platform.dev.util;

public class LogUtils {

    public static String getStackMsg(Exception e) {
        StringBuilder sb = new StringBuilder("\n");
        StackTraceElement[] stackArray = e.getStackTrace();
        StackTraceElement[] var3 = stackArray;
        int var4 = stackArray.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            StackTraceElement element = var3[var5];
            sb.append(element.toString()).append("\n");
        }

        return sb.toString();
    }
}
