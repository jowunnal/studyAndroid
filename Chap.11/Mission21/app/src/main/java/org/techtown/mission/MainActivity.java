package org.techtown.mission;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
bookDatabaseHelper helper;
SQLiteDatabase db;
TextView tv;
EditText et1;
EditText et2;
EditText et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textView2);

        et1=findViewById(R.id.editTextTextPersonName);
        et2=findViewById(R.id.editTextTextPersonName2);
        et3=findViewById(R.id.editTextTextPersonName3);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeInDatabase();
            }
        });
    }

    public void storeInDatabase(){
        helper=new bookDatabaseHelper(this);
        db=helper.getWritableDatabase();

        try {
            String title = et1.getText().toString();
            String author = et2.getText().toString();
            String contents = et3.getText().toString();

            String sql = "insert into book(title, author, contents) values ('" + title + "', '" + author + "', '" + contents + "')";
            db.execSQL(sql);
        }catch (Exception e){
            println(e.getMessage());
        }

        inquiryDatabase();
    }

    public void inquiryDatabase(){
        try {
            String sql = "select _id,title,author,contents from book";
            Cursor cursor = db.rawQuery(sql, null);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String author = cursor.getString(2);
                String contents = cursor.getString(3);

                println("(" + id + ") 제목: " + title + "저자: " + author + "내용: " + contents);
            }

            cursor.close();
        }catch (Exception e){e.printStackTrace();}
    }

    public void println(String str){
        tv.append(str+"\n");
    }
}