package com.whispon.internetfourum;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by noriaki_oshita on 15/03/05.
 */
public class ChatApplication extends Application {
    public static final String YOUR_APPLICATION_ID = "CJ16cXd7zEvOCf61V2BS9BoGePQpA4IXcqViNC0w";
    public static final String YOUR_CLIENT_KEY = "fJicEIyMy4ErOetLX7DgubsZRo5xlWJ1VtwhpvIl";
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
        // Register your parse models here
        ParseObject.registerSubclass(Message.class);
        // Existing initialization happens after all classes are registered
        //Parse.initialize(this, "CJ16cXd7zEvOCf61V2BS9BoGePQpA4IXcqViNC0w", "fJicEIyMy4ErOetLX7DgubsZRo5xlWJ1VtwhpvIl");
    }
}
