package com.whispon.internetfourum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by noriaki_oshita on 15/03/03.
 */
public class AddUser extends Activity{
    //ユーザー名に使う変数
    String username;
    String userpass;
    String userphone;
    String usermail;

    Button adduser;
    EditText name;
    EditText pass;
    EditText mail;
    EditText phone;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adduser);

        adduser = (Button) findViewById(R.id.btnAddUser);
        name = (EditText) findViewById(R.id.editname);
        pass = (EditText) findViewById(R.id.editpass);
        mail = (EditText) findViewById(R.id.editmail);
        phone = (EditText) findViewById(R.id.editphone);

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edittextから文字を入手してString型にしている。

                username = name.getText().toString();
                userpass = pass.getText().toString();
                usermail = mail.getText().toString();
                userphone = phone.getText().toString();

                Loginform loginform = new Loginform();
                loginform.onCreate(username,userpass,usermail,userphone);

            }
        });

    }

}
