package org.techtown.contacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.textView);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseContacts();
            }
        });
        AutoPermissions.Companion.loadAllPermissions(this,101);
    }

    public void chooseContacts(){
        Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent,101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101){
            if(resultCode==RESULT_OK){
                Uri contactsUri=data.getData();
                String id=contactsUri.getLastPathSegment();
                getContacts(id);
            }
        }
    }


    @SuppressLint("Range")
    public void getContacts(String id){
        Cursor cursor=null;
        String name="";

        try{
            cursor=getContentResolver().query(ContactsContract.Data.CONTENT_URI,null,ContactsContract.Data.CONTACT_ID+"=?",new String[]{id},null);
            if(cursor.moveToFirst()){
                name=cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                println("Name: "+name);

                String columns[]=cursor.getColumnNames();
                for(String column:columns){
                    int index=cursor.getColumnIndex(column);
                    String columnOUtput=("#"+index+" : ["+column+" ] "+cursor.getString(index));
                    println(columnOUtput);
                }
                cursor.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void println(String str){
        tv.append(str+"\n");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);

    }

    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(this,"권한 거절 : "+strings.length,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this,"권한 승인 : "+strings.length,Toast.LENGTH_SHORT).show();
    }
}