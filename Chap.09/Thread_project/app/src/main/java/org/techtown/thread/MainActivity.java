package org.techtown.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
int value=0;
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.textView);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreadExample thread=new ThreadExample();
                thread.start();
            }
        });
    }

    private class ThreadExample extends Thread {
        @Override
        public void run() {
            for(int i=0;i<100;i++){
                try{
                    Thread.sleep(1000);
                }catch(Exception e){

                }
                value+=1;
                Log.d("Thread","value= "+value);
                tv.setText("스레드값: "+value);
            }
        }
    }
}