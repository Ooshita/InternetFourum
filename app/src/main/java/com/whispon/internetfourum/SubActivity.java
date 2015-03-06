package com.whispon.internetfourum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by noriaki_oshita on 15/02/25.
 */
public class SubActivity extends ActionBarActivity {
    TextView scheduleTV;
    TextView contentsTV;
    Button okButton;
    Button cancelButton;
    RelativeLayout subLayout;
    private InputMethodManager inputMethodManager;
    int id = -1;
    ImageView imageBack;

   Boolean forUpdate = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);

        scheduleTV = (TextView) findViewById(R.id.editText);
        contentsTV = (TextView) findViewById(R.id.editText2);
        okButton = (Button) findViewById(R.id.addHusen);
        cancelButton = (Button) findViewById(R.id.cancelBtn);
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        subLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOkRequest();

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processCancelRequest();
            }
        });

        imageBack = (ImageView) findViewById(R.id.back);

        Intent intent = getIntent();
        if (intent!=null) {
            int id = intent.getIntExtra("id", -1);
            if (id!=-1) {
                this.id = id;
                forUpdate = true;
                String[] nameAndPhone = new String[2];
                MyDBHelper.getData(id, nameAndPhone);
                scheduleTV.setText(nameAndPhone[0]);
                contentsTV.setText(nameAndPhone[1]);
            } else {
                this.id = -1;
                forUpdate = false;
            }
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    void processOkRequest() {
        if (forUpdate == true) {
            String schedule = scheduleTV.getText().toString();
            String contents = contentsTV.getText().toString();
            MyDBHelper.updateData(id, schedule, contents);
        } else {
            String schedule = scheduleTV.getText().toString();
            String contents = contentsTV.getText().toString();
            MyDBHelper.insertData(schedule, contents);
        }
        finish();
    }
//キーボードを隠す処理
    void processCancelRequest() {
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //キーボードを隠す
        inputMethodManager.hideSoftInputFromWindow(subLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        //背景にフォーカスを移す
        subLayout.requestFocus();

        return false;
    }
}