package org.techtown.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
EditText et;
TextView tv1;
TextView tv2;
Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=findViewById(R.id.editTextTextPersonName);
        tv1=findViewById(R.id.textView);
        tv2=findViewById(R.id.textView2);
        Button button1=findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String data=et.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                    }
                }).start();

            }
        });

        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });

    }

    public void printClientLog(final String data){
        Log.d("MainActivity",data);
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv1.append(data+"\n");
            }
        });
    }

    public void printServerLog(final String data){
        Log.d("MainActivity",data);
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv2.append(data+"\n");
            }
        });
    }

    public void send(String data){
        try {
            int portNumber = 5001;
            Socket socket = new Socket("localHost", portNumber);
            printClientLog("소켓연결함");
            ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(data);
            outputStream.flush();
            printClientLog("데이터 전달함");

            ObjectInputStream inputStream=new ObjectInputStream(socket.getInputStream());
            printClientLog("서버로부터 데이터받음: "+inputStream.readObject());
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startServer(){
        try {
            int portNumber = 5001;
            ServerSocket server=new ServerSocket(portNumber);
            printServerLog("서버시작함: "+portNumber);

            while(true){
                Socket socket=server.accept();
                InetAddress clientHost=socket.getLocalAddress();
                int clientPort=socket.getPort();
                printServerLog("클라이언트 연결됨: "+clientHost+" : "+clientPort);

                ObjectInputStream inputStream=new ObjectInputStream(socket.getInputStream());
                Object obj=inputStream.readObject();
                printServerLog("데이터 전달받음"+obj);

                ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(obj+" from server");
                outputStream.flush();
                printServerLog("데이터 전달함");
                socket.close();
            }
        }
        catch (Exception e){ e.printStackTrace();}
    }
}