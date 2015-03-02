package com.whispon.internetfourum;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;

/**
 * Created by noriaki_oshita on 15/03/02.
 */
public class Loginform extends Application{

    public static final String TAG = Loginform.class.getSimpleName();

    //@Override
    public String onCreate(String a,String b,String c,String d) {
        super.onCreate();

        // Add your initialization code here
        //Parse.initialize(this, "CJ16cXd7zEvOCf61V2BS9BoGePQpA4IXcqViNC0w", "fJicEIyMy4ErOetLX7DgubsZRo5xlWJ1VtwhpvIl");        // Save the current Installation to Parse.

        ParseUser user = new ParseUser();
        user.setUsername(a);
        user.setPassword(b);
        user.setEmail(c);


        // other fields can be set just like with ParseObject
        user.put("phone", d);


        //ユーザー登録が済んでいたらえらーになる
        user.signUpInBackground(new SignUpCallback() {

            public void done(com.parse.ParseException e) {
                if (e == null) {
                    Log.d(TAG, "サインアップ成功");
                } else {
                    Log.e(TAG, "エラーによりサインアップ失敗");
                }
            }
        });


        ParseUser.logInInBackground(a, b, new LogInCallback() {
            public void done(ParseUser user, com.parse.ParseException e) {
                if (user != null) {
                    Log.d(TAG, "ログイン成功");
                } else {
                    Log.e(TAG, "エラーによりログイン失敗");
                    Intent intentAddUser = new Intent();
                    intentAddUser.setClassName("com.whispon.internetfourum","com.whispon.internetfourum.AddUser");
                    startActivity(intentAddUser);
                }
            }
        });

        Intent intentAddUser = new Intent();
        intentAddUser.setClassName("com.whispon.internetfourum","com.whispon.internetfourum.AddUser");
        startActivity(intentAddUser);

        return a;

    }
}
