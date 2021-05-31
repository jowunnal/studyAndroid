package com.example.scrollview_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
ScrollView scrollView;
ImageView imageView;
ImageView imageView2;
BitmapDrawable bitmap;
int imageIndex=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = findViewById(R.id.scrollview);
        imageView = findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);

        Resources res=getResources();
        bitmap= (BitmapDrawable) res.getDrawable(R.drawable.imo);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight= bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width=bitmapWidth;
        imageView.getLayoutParams().height=bitmapHeight;
    }

    public void onClickedButton1(View view){
        Resources res=getResources();
        bitmap=(BitmapDrawable) res.getDrawable(R.drawable.jibril_ballon);
        int bitmapWidth=bitmap.getIntrinsicWidth();
        int bitmapHeight=bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width=bitmapWidth;
        imageView.getLayoutParams().height=bitmapHeight;
    }

    public void onCLickedButton2(View view){
        if(imageIndex==1){
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageIndex=2;
        }
        else if(imageIndex==2){
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageIndex=1;
        }
    }

}
