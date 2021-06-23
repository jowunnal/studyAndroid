package com.example.mission7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button1=findViewById(R.id.button);
        Button button2=findViewById(R.id.button2);
        Button button3=findViewById(R.id.button3);

        Clicked(button1);
        Clicked(button2);
        Clicked(button3);

    }

    public void Clicked(final Button view){
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("name",view.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
