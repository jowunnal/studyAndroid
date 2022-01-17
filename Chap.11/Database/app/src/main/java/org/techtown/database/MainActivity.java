package org.techtown.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
EditText et1;
EditText et2;
TextView tv;
SQLiteDatabase db;
String tableName;
SQLiteOpenHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.editTextTextPersonName);
        et2=findViewById(R.id.editTextTextPersonName2);
        tv=findViewById(R.id.textView);

        Button button1=findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dbName=et1.getText().toString();
                creatDb(dbName);
            }
        });

        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableName=et2.getText().toString();
                creatTable(tableName);
            }
        });

        Button button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRecord();
            }
        });

        Button button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeQuery();
            }
        });
    }

    public void creatDb(String name){
        println("DB 생성 메소드 호출");
        db=openOrCreateDatabase(name,MODE_PRIVATE,null);
        println("DB생성완료");
    }

    public void creatTable(String name){
        println("DB Table생성 메소드 호출");
        try {
            if (db == null) {
                println( "DB를 먼저 생성하세요");
                return;
            }
            String sql = "create table if not exists " + name + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";
            db.execSQL(sql);
        }
        catch (Exception e){println(e.getMessage());}
    }

    public void insertRecord(){
        if(db==null){
            println("DB를 먼저 생성하세요");
            return;
        }
        if(tableName==null){
           println("table을 먼저 생성하세요");
            return;
        }
        try {
            String sql = "insert into " + tableName + "(name, age, mobile) values ( 'John', 20, '010-1234-5678')";
            db.execSQL(sql);
        }catch (Exception e){println(e.getMessage());}
        println("Record 생성완료");
    }

    public void println(String str){
        tv.append(str+"\n");
    }

    public void executeQuery(){
        println("executeQuery 실행");
        if(db==null){
            println("DB를 먼저 생성하세요");
            return;
        }
        tableName=et2.getText().toString();
        try {
            String sql = "select _id, name, age, mobile from " + tableName;
            Cursor cursor = db.rawQuery(sql, null);
            int recordCount = cursor.getCount();
            println("레코드 개수: " + recordCount);

            for (int i = 0; i < recordCount; i++) {
                cursor.moveToNext();
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String mobile = cursor.getString(3);

                println("레코드 :" + i + "번 : " + id + " , " + name + " , " + age + " , " + mobile);
            }
            cursor.close();
        }catch (Exception e){println(e.getMessage());}
    }

}