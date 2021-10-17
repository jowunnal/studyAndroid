package org.techtown.mission11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
EditText editText;
Button button;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editTextTextPersonName);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        String tt=textView.getText().toString();
        Log.i("TAG",tt);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MyService.class);
                intent.putExtra("key",editText.getText().toString());
                startService(intent);
            }
        });

        Intent intent=getIntent();
        if(intent!=null){
            String txt=intent.getStringExtra("key");
            textView.setText(txt);
        }
    }
}