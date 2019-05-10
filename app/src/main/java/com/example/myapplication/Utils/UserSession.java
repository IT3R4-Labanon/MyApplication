package com.example.myapplication.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.myapplication.Models.User;
import com.google.gson.annotations.SerializedName;

public class UserSession {
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("contact_number")
    private String contactNumber;
    @SerializedName("company_name")
    private String companyName;
    @SerializedName("token")
    private String authToken;
    @SerializedName("emp_id_digits")
    private int empIdDigits;
    @SerializedName("avatar")
    private String profilePicture;
    private String email;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public static String getToken(Context context)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString("AUTHTOKEN", null);
    }

    public static String getFirstName(Context context)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString("FIRST_NAME", "");
    }

    public static String getLastName(Context context)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString("LAST_NAME", "");
    }

    public static String getContactNumber(Context context)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString("CONTACT_NUMBER", "");
    }

    public static String getCompany(Context context)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString("COMPANY_NAME", "");
    }

    public static String getCompanyEmail(Context context)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString("COMPANY_EMAIL", "");
    }

    public static String getProfilePicture(Context context)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString("PROFILE_PICTURE", "");
    }

    public static int getEmployeeIdDigits(Context context)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getInt("EMP_ID_DIGITS", 0);
    }

    public static boolean clearSession(Context context)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        return editor.commit();
    }

    public static int getUserId(Context context)
    {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getInt("USER_ID", 0);
    }

    public boolean saveUserSession(Context context)
    {
        Debugger.printO(this);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("COMPANY_NAME", companyName);
        editor.putString("AUTHTOKEN", getAuthToken());
        editor.putString("COMPANY_EMAIL", getUser().getEmail());
        editor.putInt("USER_ID", getUser().getId());
        return editor.commit();
    }

//        public static String isUserLoggedIn(Context context) {
//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//
//            return prefs.getString("isUserLoggedIn", null);
//        }
//
//        public static void setUserLoggedIn(Context context, Boolean isLoggedIn)
//        {
//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putBoolean("isUserLoggedIn", isLoggedIn);
//            editor.commit();
//        }
//
//        public static boolean logout(Context context) {
//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.clear();
//            return editor.commit();
//        }
//
//        public static void saveUsernameAndPassword(Context context, String username, String password)
//        {
//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putString("username", username);
//            editor.putString("password", password);
//            editor.commit();
//        }
}
