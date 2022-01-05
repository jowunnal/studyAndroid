package org.techtown.http;

import androidx.appcompat.app.AppCompatActivity;

import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
Handler handler=new Handler();
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.textView);
        EditText et=findViewById(R.id.editTextTextPersonName);
        Button button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strurl=et.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(strurl);
                    }
                }).start();
            }
        });
    }

    private void request(String strurl){
        StringBuilder output=new StringBuilder();
       try {
           URL url=new URL(strurl);
           HttpURLConnection http=(HttpURLConnection) url.openConnection();

           http.setConnectTimeout(10000);
           http.setRequestMethod("GET");
           http.setDoInput(true);

           int code=http.getResponseCode();
           BufferedReader reader=new BufferedReader(new InputStreamReader(http.getInputStream()));
           String line=null;
           while(true){
               line=reader.readLine();
               if(line==null){break;}
               output.append(line+"\n");
           }
           reader.close();
           http.disconnect();
       }
       catch (Exception e) {
          println("예외발생함"+e.toString());
       }

       println("응답=>"+output.toString());
    }

    private void println(String str){
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv.append(str.toString());
            }
        });
    }
}