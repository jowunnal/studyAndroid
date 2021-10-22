package org.techtown.threadanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ImageView iv;
ArrayList<Drawable> drawableList=new ArrayList<>();
Handler handler= new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res=getResources();
        drawableList.add(res.getDrawable(R.drawable.photo1));
        drawableList.add(res.getDrawable(R.drawable.photo2));
        drawableList.add(res.getDrawable(R.drawable.photo3));
        drawableList.add(res.getDrawable(R.drawable.photo4));
        drawableList.add(res.getDrawable(R.drawable.photo5));

        iv=findViewById(R.id.imageView);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread thread=new MyThread();
                thread.start();
            }
        });
    }

    private class MyThread extends Thread{
        @Override
        public void run() {
            int index=0;
            for(int i=0;i<100;i++){
                final Drawable drawable=drawableList.get(index);
                index+=1;
                if(index>4){
                    index=0;
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iv.setImageDrawable(drawable);
                    }
                });
                try{Thread.sleep(1000);}
                catch (Exception e){e.printStackTrace();}
            }
        }
    }
}