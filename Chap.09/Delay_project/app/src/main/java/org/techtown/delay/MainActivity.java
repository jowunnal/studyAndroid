package org.techtown.delay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Handler handler=new Handler();
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
                request();
            }
        });
    }
    private void request(){
        String title="원격요청";
        String message="원격요청을 하시겟습니까?";
        String titleButtonYes="예";
        String titleButtonNo="아니오";
        AlertDialog dialog=makeRequestDialog(title,message,titleButtonYes,titleButtonNo);
        dialog.show();

        tv.setText("대화상자 표시중...");
    }
    private AlertDialog makeRequestDialog(String title,String message,String titleButtonYes,String titleButtonNo){
        AlertDialog.Builder requestDialog=new AlertDialog.Builder(this);
        requestDialog.setTitle(title);
        requestDialog.setMessage(message);
        requestDialog.setPositiveButton(titleButtonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv.setText("5초후에 표시됨");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("요청완료");
                    }
                },5000);
            }
        });

        requestDialog.setNegativeButton(titleButtonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return requestDialog.create();
    }
}