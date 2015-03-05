package com.whispon.internetfourum;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by noriaki_oshita on 15/03/04.
 */
public class Login extends Activity {
    Button loginBtn;
    EditText acountName;
    EditText passWord;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginBtn = (Button) findViewById(R.id.btnlogin);
        acountName = (EditText) findViewById(R.id.acount);
        passWord = (EditText) findViewById(R.id.password);


    }
}
