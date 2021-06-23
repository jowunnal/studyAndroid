package com.example.mission8;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static int REQUEST_CODE=101;
    static int REQUEST_CODE_Menu=102;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(editText1.getText().length()==0||editText2.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"아이디와 비밀번호를 입력해주세요",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }
}
