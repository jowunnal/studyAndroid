package com.example.project_chap02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
ImageView ig1;
ImageView ig2;
int click_index=1;
BitmapDrawable bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScrollView scrollView1 = findViewById(R.id.scrollView1);
        ScrollView scrollView2 = findViewById(R.id.scrollView2);
        ig1=findViewById(R.id.imageView7);
        ig2=findViewById(R.id.imageView8);
        Button button = findViewById(R.id.button6);

        Resources res= getResources();
        bitmap= (BitmapDrawable) res.getDrawable(R.drawable.cat);
        ig1.setImageDrawable(bitmap);
    }

    public void onClickedButton1(View view){
        if(click_index==1){
            ig2.setImageDrawable(bitmap);
            ig1.setImageResource(0);
            click_index=2;
        }
        else if(click_index==2){
            ig1.setImageDrawable(bitmap);
            ig2.setImageResource(0);
            click_index=1;
        }
    }
}
