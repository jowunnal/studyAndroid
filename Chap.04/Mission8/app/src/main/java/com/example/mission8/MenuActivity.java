package com.example.mission8;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    static int REQUEST_CODE_Menu=102;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int num=data.getIntExtra("key",0);
        if(requestCode==REQUEST_CODE_Menu&&resultCode==RESULT_OK&&num==1){
            String name=data.getStringExtra("name");
            Toast.makeText(this,name,Toast.LENGTH_LONG).show();
            finish();
        }
        else if(requestCode==REQUEST_CODE_Menu&&resultCode==RESULT_OK&&num==0){
            String name=data.getStringExtra("name");
            Toast.makeText(this,name,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button1=findViewById(R.id.button);
        Button button2=findViewById(R.id.button2);
        Button button3=findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CustomerActivity.class);
                startActivityForResult(intent,REQUEST_CODE_Menu);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SalesActivity.class);
                startActivityForResult(intent,REQUEST_CODE_Menu);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ProductActivity.class);
                startActivityForResult(intent,REQUEST_CODE_Menu);
            }
        });
    }

}
