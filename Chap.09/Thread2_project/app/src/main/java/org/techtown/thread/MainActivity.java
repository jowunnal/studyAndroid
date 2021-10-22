package org.techtown.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
int value=0;
TextView tv;
MainHandler handler;
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
        handler=new MainHandler();
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


                Message message= handler.obtainMessage();
                Bundle bundle= new Bundle();
                bundle.putInt("value",value);
                message.setData(bundle);

                handler.sendMessage(message);
            }
        }
    }

    private class MainHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle=msg.getData();
            int value=bundle.getInt("value");
            tv.setText("스레드값: "+value);
        }
    }
}