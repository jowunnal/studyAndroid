package com.example.parcelable_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    public static final String KEY_SIMPLE_DATA="data";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button=findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent();
                intent.putExtra("name","mike");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        Intent intent=getIntent();
        processIntent(intent);
    }
    private void processIntent(Intent intent){
        if(intent !=null){
            Bundle bundle=intent.getExtras();
            SimpleData data=bundle.getParcelable(KEY_SIMPLE_DATA);
            if(intent !=null){
                textView.setText("전달받은데이터: "+data.number+"\nMessage: "+data.message);
            }
        }
    }
}
