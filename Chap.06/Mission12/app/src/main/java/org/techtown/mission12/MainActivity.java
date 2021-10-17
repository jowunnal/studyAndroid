package org.techtown.mission12;

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
TextView textView;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editTextTextPersonName);
        textView=findViewById(R.id.text3);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MyService.class);
                intent.putExtra("key",editText.getText().toString());
                startService(intent);
            }
        });
        Intent intent=getIntent();
        textView.setText(intent.getStringExtra("key"));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        textView.setText(intent.getStringExtra("key"));
        super.onNewIntent(intent);
    }

}