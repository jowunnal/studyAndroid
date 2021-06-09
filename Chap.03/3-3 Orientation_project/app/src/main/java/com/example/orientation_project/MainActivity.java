package com.example.orientation_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
String name;
EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToast("onCreat 호출");
        editText=findViewById(R.id.editText);

        Button button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                name=editText.getText().toString();
                showToast("입력된 변수 : "+name);
            }
        });
        if(savedInstanceState != null){
            name=savedInstanceState.getString("name");
            showToast("이전 데이터 : "+name);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        showToast("onStart 호출");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToast("onStop 호출");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("ondestroy 호출");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name",name);
    }

    public void showToast(String data){
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }
}
