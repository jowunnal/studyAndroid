package org.techtown.mission;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class bookDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="bookDB.db";
    public static final int VERSION_DB=1;

    public bookDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table if not exists book (_id integer PRIMARY KEY autoincrement, title text, author text, contents text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        
    }
}
