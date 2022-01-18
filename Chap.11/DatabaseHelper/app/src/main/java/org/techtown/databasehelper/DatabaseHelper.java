package org.techtown.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static int DB_VERSION=1;

    public DatabaseHelper(@Nullable Context context,String dbname) {
        super(context, dbname, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //DB생성
        String sql="create table if not exists person (_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
