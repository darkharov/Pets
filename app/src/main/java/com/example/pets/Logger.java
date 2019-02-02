package com.example.pets;

import android.util.Log;

public class Logger {

    public static void e(Object o, String msg) {
        if (BuildConfig.DEBUG) {

            String tag = o instanceof String
                    ? (String) o
                    : getDeclaredClass(o).getSimpleName();

            Log.e(tag, msg);
        }
    }

    private static String comupteTag(Object o) {
        return (o instanceof Class ? (Class) o : getDeclaredClass(o))
                .getSimpleName();
    }

    private static Class<?> getDeclaredClass(Object o) {

        Class<?> clazz = o.getClass();

        return clazz.isAnonymousClass()
                ? clazz.getSuperclass()
                : clazz;
    }
}
