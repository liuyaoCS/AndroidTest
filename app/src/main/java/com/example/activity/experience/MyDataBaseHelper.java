package com.example.activity.experience;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rmss on 2015/8/19.
 */
public class MyDataBaseHelper extends SQLiteOpenHelper{
    public static final String DB_NAME = "people.db";
    public static final String TABLE_NAME = "people";
    public static final String COL_NAME="name";
    public static final  String COL_SEX="sex";
    public static final int VERSION = 1;

    private String CREATE_TABLE="create table if not exists people(" +
            "_id integer primary key autoincrement," +
            COL_NAME+" varchar(10) not null," +
            COL_SEX+" varchar(10) not null)";

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
