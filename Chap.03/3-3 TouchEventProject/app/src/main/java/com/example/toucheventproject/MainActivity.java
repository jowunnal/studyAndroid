package com.example.toucheventproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {
    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = findViewById(R.id.textView);
        View view=findViewById(R.id.view);
        View view2=findViewById(R.id.view2);
        ScrollView sv=findViewById(R.id.scrollview);

        final GestureDetector detector;
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                float curX = event.getX();
                float curY = event.getY();

                if (action == event.ACTION_DOWN) {
                    println("손가락 눌림 : " + curX + ", " + curY);
                } else if (action == event.ACTION_MOVE) {
                    println("손가락 움직임 : " + curX + ", " + curY);
                } else if (action == event.ACTION_UP) {
                    println("손가락 뗌 " + curX + ", " + curY);
                }
                return true;
            }
        });

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDOwn() 호출");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("onshowPress() 호출");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onsingleTapUp() 호출");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("onscroll() 호출");
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress() 호출");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling() 호출");
                return false;
            }
        });

        view2.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view,MotionEvent motionEvent){
                detector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    public void println(String data){
        textview.append(data+"\n");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Toast.makeText(this,"Back버튼 눌림",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
