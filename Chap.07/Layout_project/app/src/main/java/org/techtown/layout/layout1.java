package org.techtown.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class layout1 extends LinearLayout {
    TextView textView1;
    TextView textView2;
    ImageView imageView;
    public layout1(Context context) {
        super(context);
        init(context);
    }

    public layout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.layout1,this,true);

        textView1=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView3);
        imageView=findViewById(R.id.imageView);
    }

    public void setImage(int res){
        imageView.setImageResource(res);
    }
    public void setName(String name){
        textView1.setText(name);
    }
    public void setMobile(String mobile){
        textView2.setText(mobile);
    }
}

