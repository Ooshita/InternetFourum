package com.whispon.internetfourum;

import android.app.Application;
import android.util.Log;

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

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        //Parse.initialize(this, "CJ16cXd7zEvOCf61V2BS9BoGePQpA4IXcqViNC0w", "fJicEIyMy4ErOetLX7DgubsZRo5xlWJ1VtwhpvIl");        // Save the current Installation to Parse.

        ParseUser user = new ParseUser();
        user.setUsername("my name");
        user.setPassword("my pass");
        user.setEmail("email@example.com");

        // other fields can be set just like with ParseObject
        user.put("phone", "650-555-0000");

        user.signUpInBackground(new SignUpCallback() {
            public void done(com.parse.ParseException e) {
                if (e == null) {
                    Log.d(TAG, "サインアップ成功");
                } else {
                    Log.e(TAG, "エラーによりサインアップ失敗");
                }
            }
        });
    }
}
