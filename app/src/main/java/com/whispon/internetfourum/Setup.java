package com.whispon.internetfourum;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.BufferedInputStream;


/**
 * Created by noriaki_oshita on 15/03/06.
 */
public class Setup extends ActionBarActivity {
    Button setup;
    private static final int REQUEST_GALLERY = 0;
    ImageView imageView;
    //背景画像を変更するためのコンストラクタ
    LinearLayout mainLayout;
    LinearLayout setupLayout;
    RelativeLayout subLayout;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);
        imageView =(ImageView) findViewById(R.id.back);

        setup = (Button) findViewById(R.id.btnChoise);
        //背景画像を変更するためのコンストラクタ
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        subLayout = (RelativeLayout) findViewById(R.id.subLayout);
        setupLayout = (LinearLayout) findViewById(R.id.setupLayout);

        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                //intent.setTypeAndNormalize("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_GALLERY);
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO 自動生成されたメソッド・スタブ
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            try {
                BufferedInputStream inputStream = new BufferedInputStream(getContentResolver().openInputStream(data.getData()));
                Bitmap image = BitmapFactory.decodeStream(inputStream);
                //imageView.setImageBitmap(image);
                //Drawable drawable = new BitmapDrawable(getResources(),image);
                Drawable drawable = new BitmapDrawable(getResources(),image);
                mainLayout.setBackground(drawable);
                setupLayout.setBackground(drawable);
                subLayout.setBackground(drawable);


            } catch (Exception e) {
                Log.d("debug","画像読み込み失敗");
            }
        }


    }
}

