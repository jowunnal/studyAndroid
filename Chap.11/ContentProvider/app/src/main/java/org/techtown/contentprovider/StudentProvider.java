package org.techtown.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StudentProvider extends ContentProvider {
    private static final String authority="org.techtown.contentprovider";
    private static final String base_Path="student";
    private static final Uri content_Uri=Uri.parse("content://"+authority+"/"+base_Path);
    private static final int persons=1;
    private static final int person_ID=2;
    private static final UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
    static{
        uriMatcher.addURI(authority,base_Path,persons);
        uriMatcher.addURI(authority,base_Path+"/#",person_ID);
    }

    private SQLiteDatabase db;
    @Override
    public boolean onCreate() {
        DatabaseHelper helper=new DatabaseHelper(getContext());
        db=helper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor cursor;
        switch (uriMatcher.match(uri)){
            case persons:
                cursor=db.query(DatabaseHelper.tableName,DatabaseHelper.allColumns,s,null,null,null,DatabaseHelper.studentName+" ASC");
                break;
            default:
                throw new IllegalArgumentException("알수없는URI : "+uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case persons:
                return "vnd.android.cursor.dir/students";

            default:
                throw new IllegalArgumentException("알수없는URI : "+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long id=db.insert(DatabaseHelper.tableName,null,contentValues);
        if(id>0){
            Uri _uri= ContentUris.withAppendedId(content_Uri,id);
            getContext().getContentResolver().notifyChange(_uri,null);
            return _uri;
        }

        throw new SQLException("추가 실패-> URI= "+uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int count=0;
        switch (uriMatcher.match(uri)){
            case persons:
                count=db.delete(DatabaseHelper.tableName,s,strings);
                break;
            default:
                throw new IllegalArgumentException("알수없는URI : "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int count=0;
        switch (uriMatcher.match(uri)){
            case persons:
                count=db.update(DatabaseHelper.tableName,contentValues,s,strings);
                break;
            default:
                throw new IllegalArgumentException("알수없는URI : "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
}
