package com.examples.rehan.excluzo.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.examples.rehan.excluzo.Models.User;

/**
 * Created by rehan r on 02-09-2016.
 */
public class LoginPreferences {

    SharedPreferences preferences;
    Context context;
    public static final String USERID = "userid";
    public static final String USERTABLE = "usertable";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String MOBILE = "mobile";
    public static final String ADDRESS = "address";
    public static final String AGE = "AGE";
    public static final String GENDER = "gender";
    public static final String IS_LOGGED_IN = "isloggedin";

    public LoginPreferences(Context context){
        preferences = context.getSharedPreferences(USERTABLE,context.MODE_PRIVATE);
        this.context = context;
    }

    public void loginUser(User user){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERID,user.getUserid());
        editor.putString(USERNAME,user.getUsername());
        editor.putString(EMAIL,user.getEmail());
        editor.putString(PASSWORD,user.getPassword());
        editor.putString(MOBILE,user.getMobile());
        editor.putString(ADDRESS,user.getAddress());
        editor.putInt(AGE,user.getAge());
        editor.putInt(GENDER,user.getGender());
        editor.putBoolean(IS_LOGGED_IN,true);
        editor.apply();
    }

    public void logoutUser(){
        if(preferences.getBoolean(IS_LOGGED_IN,false)){
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
        }
    }
    public User getUser() {
        String userId = preferences.getString(USERID,"");
        String firstName = preferences.getString(USERNAME,"");
        String email = preferences.getString(EMAIL,"");
        String password = preferences.getString(PASSWORD,"");
        String mobile = preferences.getString(MOBILE,"");
        String address = preferences.getString(ADDRESS,"");
        int age = preferences.getInt(AGE,-1);
        int gender = preferences.getInt(GENDER,-1);
        return new User(userId,firstName,email,password,mobile,age,gender,address);
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(IS_LOGGED_IN,false);
    }
}
