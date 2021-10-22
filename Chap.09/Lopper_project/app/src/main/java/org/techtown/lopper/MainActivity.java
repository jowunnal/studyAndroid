package org.techtown.lopper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;
    Handler handler=new Handler();
    ProcessThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=findViewById(R.id.editTextTextPersonName);
        tv=findViewById(R.id.textView);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input=et.getText().toString();
                Message message=Message.obtain();
                message.obj=input;

                thread.processHandler.sendMessage(message);
            }
        });
        thread=new ProcessThread();
    }

    private class ProcessThread extends Thread{
        ProcessHandler processHandler=new ProcessHandler();
        private class ProcessHandler extends Handler{
            @Override
            public void handleMessage(@NonNull Message msg) {
                final String output=msg.obj+" from thread";
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(output);
                    }
                });
            }
        }
        @Override
        public void run() {
            Looper.prepare();
            Looper.loop();
        }

    }
}