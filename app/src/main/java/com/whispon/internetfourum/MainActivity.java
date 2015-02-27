package com.whispon.internetfourum;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;



public class MainActivity extends Activity {
    ListView listView;
    Button addButton;
    ArrayList<String> nameList = new ArrayList<String>(); //HUSEN list
    ArrayAdapter<String> adapter; //name list and listView for connecting

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ListView) findViewById(R.id.listView);
        addButton = (Button) findViewById(R.id.addBtn);


        //リストビューをクリックした時の処理を設定
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openSubActivityForUpdate(position);
            }
        });
        //リストビューを長押しした時の処理を設定
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteConfirm(position);
                return true;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SubIntent = new Intent();
                SubIntent.setClassName("com.whispon.internetfourum", "com.whispon.internetfourum.SubActivity");
                startActivity(SubIntent);
            }
        });

        //名前の一覧(ArrayList)をリストビューと関連付ける
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, nameList);
        listView.setAdapter(adapter);

        //DBの初期化
        MyDBHelper.init(this);
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

    //このActivityが最初に表示される前と、再表示される前に実行される
    @Override
    public void onResume() {
        super.onResume();
        //DBからデータ読み出して名前リストにセット
        cleanSetUpListView();
    }

    //リストビューにDBからデータを取り出してIDと名前の一覧をセットアップする
    void cleanSetUpListView() {
        adapter.clear();
        ArrayList<String> names = MyDBHelper.listUpIdAndName();
        for (String s : names) {
            adapter.add(s);
        }
    }

    //終了処理
    @Override
    public void onDestroy() {
        super.onDestroy();
        MyDBHelper.destroy();
    }

    //新しいデータを追加するためにSubActivityを開く
    void openSubActivityForNewData() {
        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        MainActivity.this.startActivity(intent);
    }

    //既存のデータの書き換えのためにSubActivityを開く
    void openSubActivityForUpdate(int position) {
        String s = nameList.get(position);
        int id = Integer.parseInt(s.substring(0, s.indexOf(":")));

        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        intent.putExtra("id", id);
        MainActivity.this.startActivity(intent);
    }

    //データ削除の確認をとってOKなら削除する
    void deleteConfirm(int position) {
        AlertDialog.Builder adBuilder = new AlertDialog.Builder(this);
        adBuilder.setTitle("削除確認");
        adBuilder.setMessage("このデータを削除しますか？");
        final int p = position;
        adBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                deleteData(p);
            }
        });
        adBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ;//DO NOTHING
            }
        });
        adBuilder.create().show();

    }

    //実際にデータを削除する処理
    void deleteData(int position) {
        String s = nameList.get(position);
        Log.d("debug", "deleteData(). s=" + s);
        int id = Integer.parseInt(s.substring(0, s.indexOf(":")));
        Log.d("debug", "deleteData(). id=" + id);
        MyDBHelper.deleteData(id);
        cleanSetUpListView();
    }

}
