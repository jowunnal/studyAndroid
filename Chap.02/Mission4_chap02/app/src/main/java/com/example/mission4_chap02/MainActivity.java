package com.example.mission4_chap02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText editText;
TextView textView;
Button button1;
Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edittext);
        textView= findViewById(R.id.textView2);
        button1= findViewById(R.id.button3);
        button2= findViewById(R.id.button4);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                textView.setText(text.length()+" /80 바이트");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onClickedButton1(View view){
        Toast.makeText(this,editText.getText(),Toast.LENGTH_SHORT).show();
    }

    public void onClickedButton2(View view){
        finish();
    }
}
