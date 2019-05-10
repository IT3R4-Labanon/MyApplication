package com.example.myapplication.Utils;

import android.util.Log;

import com.google.gson.Gson;

public class Debugger {
    private static final String TAG = "DEV";

    public static String printO(Object obj)
    {
        Gson gson = new Gson();
        System.out.println(gson.toJson(obj));
        Log.d(TAG,gson.toJson(obj));
        return gson.toJson(obj);
    }

    public static void logD(String message)
    {
        Log.d(TAG, message);
    }

    public static void printError(Exception err)
    {
        Log.d(TAG, "Line No :" + err.getStackTrace()[0].getLineNumber());
        Log.d(TAG, err.getMessage());
        Log.d(TAG, err.toString());
        err.printStackTrace();
    }

    public static void logDint(int value) { Log.d(TAG, String.valueOf(value)); }

}
