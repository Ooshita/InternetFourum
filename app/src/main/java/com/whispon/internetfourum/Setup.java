package com.whispon.internetfourum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by noriaki_oshita on 15/03/06.
 */
public class Setup extends ActionBarActivity {
    Button setup;
    private static final int REQUEST_GALLERY = 0;
    ImageView imageView;
    ImageView ivback;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);
        imageView =(ImageView) findViewById(R.id.back);
        ivback = (ImageView) findViewById(R.id.ivback);

        setup = (Button) findViewById(R.id.btnChoise);

        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_GALLERY);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO 自動生成されたメソッド・スタブ
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            try {
                BufferedInputStream inputStream = new BufferedInputStream(getContentResolver().openInputStream(data.getData()));
                Bitmap image = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(image);
                ivback.setImageBitmap(image);

            } catch (Exception e) {

            }
        }


    }
}

