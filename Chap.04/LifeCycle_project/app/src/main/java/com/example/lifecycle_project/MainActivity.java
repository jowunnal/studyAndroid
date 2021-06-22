package com.example.lifecycle_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static int REQUEST_CODE=101;
    EditText nameInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"onCreate()호출됨",Toast.LENGTH_LONG).show();

        nameInput=findViewById(R.id.nameInput);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        println("onstart()호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();
        println("onStop()호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        println("onDestory()호출됨");
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
        println("onPause()호출됨");
    }

    @Override
    protected void onResume(){
        super.onResume();
        restoreState();
        println("onResume()호출됨");
    }

    public void println(String data){
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
        Log.d("Main",data);
    }

    protected void saveState(){
        SharedPreferences pref=getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString("name",nameInput.getText().toString());
        editor.commit();
    }

    protected void restoreState(){
        SharedPreferences pref=getSharedPreferences("pref",Activity.MODE_PRIVATE);
        if((pref!=null)&&(pref.contains("name"))){
            String name=pref.getString("name","");
            nameInput.setText(name);
        }
    }

    protected void clearState(){
        SharedPreferences pref=getSharedPreferences("pref",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.clear();
        editor.commit();
    }
}
