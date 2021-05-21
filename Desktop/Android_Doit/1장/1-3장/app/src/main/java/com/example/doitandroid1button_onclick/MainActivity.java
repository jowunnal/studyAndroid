package com.example.doitandroid1button_onclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick1button(View v){
        Toast.makeText(this,"확인버튼이 눌렷어요!",Toast.LENGTH_LONG).show();
    }

    public void onclicked2button(View v){
        Toast.makeText(this,"버튼이 눌러졌어염",Toast.LENGTH_LONG).show();
    }

    public void onClick3Button(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.naver.com"));
        startActivity(intent);
    }

    public void onClick4Button(View v){
        Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-0000-0000"));
        startActivity(intent);
    }
}
