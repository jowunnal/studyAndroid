package org.techtown.databasehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
TextView tv;
EditText et1;
EditText et2;
String name;
SQLiteDatabase db;
DatabaseHelper helper;
String tableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.textView);
        et1=findViewById(R.id.editTextTextPersonName);
        et2=findViewById(R.id.editTextTextPersonName2);


        Button button1=findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=et1.getText().toString();
                createDB(name);
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

    private void createDB(String name){
        try{
        helper=new DatabaseHelper(this,name);
        db=helper.getWritableDatabase();
        println("DB생성 완료"+name);}
        catch (Exception e){
            println(e.getMessage());
        }
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

    public void executeQuery(){
        println("executeQuery 실행");
        if(db==null){
            println("DB를 먼저 생성하세요");
            return;
        }

        try {
            String sql = "select _id, name, age, mobile from " + tableName;
            Cursor cursor = db.rawQuery(sql, null);
            int recordCount = cursor.getCount();
            println("레코드 개수: " + recordCount);
            int index=0;
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String mobile = cursor.getString(3);

                println("레코드 :" + index + "번 : " + id + " , " + name + " , " + age + " , " + mobile);
                index++;
            }
            cursor.close();
        }catch (Exception e){println(e.getMessage());}
    }


    public void println(String str){
        tv.append(str +"\n");
    }
}