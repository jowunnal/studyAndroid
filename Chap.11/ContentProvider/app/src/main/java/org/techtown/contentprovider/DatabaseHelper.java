package org.techtown.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName="student.db";
    public static final int version=1;
    public static final String tableName="student";
    public static final String studentID="_id";
    public static final String studentName="name";
    public static final String studentAge="age";
    public static final String studentMobile="mobile";
    public static final String[] allColumns={studentID,studentName,studentAge,studentMobile};


    private static final String creat_Table= "create table "+tableName+" ("+studentID+" integer PRIMARY KEY autoincrement, "+studentName+" text, "+studentAge+" integer, "+studentMobile+" text)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(creat_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+tableName);
        onCreate(sqLiteDatabase);
    }
}
