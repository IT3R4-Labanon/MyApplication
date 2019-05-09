package com.example.myapplication.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserSession {
        public static String isUserLoggedIn(Context context) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

            return prefs.getString("isUserLoggedIn", null);
        }

        public static void setUserLoggedIn(Context context, Boolean isLoggedIn)
        {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isUserLoggedIn", isLoggedIn);
            editor.commit();
        }

        public static boolean logout(Context context) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            return editor.commit();
        }

        public static void saveUsernameAndPassword(Context context, String username, String password)
        {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.commit();
        }
}
