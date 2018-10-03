package com.eveati.sajid.teslar;

import android.content.Context;

/**
 * Created by pramay on 9/5/2018.
 */

class SPUser {

    public static final String USERNAME= "user_id";
    public static final String USER_ID= "user_name";
    public static final String USER_SOURCE= "user_source";
    public static final String FIRST_TIME= "first";



    public static long getLongValue(Context context, String key) {

        return context.getSharedPreferences("USER", Context.MODE_PRIVATE)
                .getLong(key, 0);
    }
    public static void setLongValue(Context context, String key, Long value) {

        context.getSharedPreferences("USER", Context.MODE_PRIVATE).edit()
                .putLong(key, value).commit();
    }
    public static String getValue(Context context, String key) {

        return context.getSharedPreferences("USER", Context.MODE_PRIVATE)
                .getString(key, "");
    }

    public static void setValue(Context context, String key, String value) {

        context.getSharedPreferences("USER", Context.MODE_PRIVATE).edit()
                .putString(key, value).commit();
    }

    public static int getIntValue(Context context, String key) {

        return context.getSharedPreferences("USER", Context.MODE_PRIVATE)
                .getInt(key, 0);
    }

    public static void setIntValue(Context context, String key, int value) {

        context.getSharedPreferences("USER", Context.MODE_PRIVATE).edit()
                .putInt(key, value).commit();
    }

    public static boolean getBooleanValue(Context context, String key) {

        return context.getSharedPreferences("USER", Context.MODE_PRIVATE)
                .getBoolean(key, false);
    }

    public static void setBooleanValue(Context context, String key,
                                       boolean value) {

        context.getSharedPreferences("USER", Context.MODE_PRIVATE).edit()
                .putBoolean(key, value).commit();
    }


    public static float getFloatValue(Context context, String key) {

        return context.getSharedPreferences("USER", Context.MODE_PRIVATE)
                .getFloat(key, 0.0f);
    }

    public static void setFloatValue(Context context, String key,
                                     float value) {

        context.getSharedPreferences("USER", Context.MODE_PRIVATE).edit()
                .putFloat(key, value).commit();
    }

    public static boolean clear(Context context) {

        return context.getSharedPreferences("USER", Context.MODE_PRIVATE).edit().clear().commit();
    }
}
