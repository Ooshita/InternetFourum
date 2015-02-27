package com.whispon.internetfourum;

/**
 * Created by noriaki_oshita on 15/02/27.
 */
        import java.util.ArrayList;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.database.Cursor;

//DBを管理するためのクラス
public class MyDBHelper extends SQLiteOpenHelper {
    static final String DB = "schedules_db.db";
    static final int DB_VERSION = 1;

    static MyDBHelper helper;
    static SQLiteDatabase db;

    //DBを初期化する。1つ作って共有するようにする。
    public static void init(Context c) {
        if (helper==null) {
            helper = new MyDBHelper(c);
            db = helper.getWritableDatabase();
        }
    }

    //コンストラクタはprivateにしておく
    private MyDBHelper(Context c) {
        super(c,DB,null,DB_VERSION);
    }

    //DBの作成
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table contents ( id integer primary key autoincrement, schedule text not null, contents text not null );";
        db.execSQL(CREATE_TABLE);
    }

    //DBのアップグレード。
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "drop table contents;";
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    //DBを閉じる処理
    public static void destroy() {
        //TODO
    }

    //DBからid:nameの一覧をリストアップする
    public static ArrayList<String> listUpIdAndName() {
        ArrayList<String> results = new ArrayList<String>();
        Cursor cursor = db.query("contents", new String[] {"id","schedule"}, null,null,null,null,"id ASC");
        cursor.moveToFirst();
        for (int i=0;i<cursor.getCount();i++) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String schedule = cursor.getString(cursor.getColumnIndex("schedule"));
            results.add(id+":"+schedule);
            cursor.moveToNext();
        }
        return results;
    }

    //DBに新しいデータを入れる。
    public static void insertData(String schedule, String contents) {
        ContentValues values = new ContentValues();
        values.put("schedule", schedule);
        values.put("contents", contents);
        db.insert("contents", null, values);
    }

    //指定したidのレコードのデータをDBから取り出す
    public static void getData(int id, String[] out) {
        Cursor cursor = db.query("contents", new String[] {"schedule","contents"}, "id = "+id, null, null, null, null);
        cursor.moveToFirst();
        String schedule = cursor.getString(cursor.getColumnIndex("schedule"));
        String contents = cursor.getString(cursor.getColumnIndex("contents"));
        out[0] = schedule;
        out[1] = contents;
    }

    //DBのデータを更新
    public static void updateData(int id,String schedule,String contents) {
        ContentValues values = new ContentValues();
        values.put("schedule", schedule);
        values.put("contents", contents);
        db.update("contents", values, "id = "+id, null);
    }

    //DBからデータを消す
    public static void deleteData(int id) {
        db.delete("contents", "id = "+id, null);
    }
}

