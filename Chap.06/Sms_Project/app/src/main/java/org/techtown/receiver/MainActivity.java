package org.techtown.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1=findViewById(R.id.editTextTextPersonName);
        editText2=findViewById(R.id.editTextTextPersonName2);
        editText3=findViewById(R.id.editTextTextPersonName3);
        Button button= findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent=getIntent();
        ProcessIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        ProcessIntent(intent);
        super.onNewIntent(intent);
    }


    private void ProcessIntent(Intent intent){
        String number_SMS=intent.getStringExtra("sender");
        String content_SMS=intent.getStringExtra("contents");
        String date_SMS=intent.getStringExtra("receivedDate");

        editText1.setText(number_SMS);
        editText2.setText(content_SMS);
        editText3.setText(date_SMS);
    }
}