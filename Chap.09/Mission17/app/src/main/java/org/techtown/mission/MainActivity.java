package org.techtown.mission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView tv1;
Handler handler=new Handler();
Animation animationLeft;
Animation animationRight;
CardView container1;
CardView container2;
Boolean isOpenC1=true;
Boolean isOpenC2=false;
int count=1;
int repeatSwitch=1;
Boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=findViewById(R.id.textView2);
        container1=findViewById(R.id.container1);
        container2=findViewById(R.id.container2);
        TextView tv2=findViewById(R.id.textView3);
        TextView tv3=findViewById(R.id.textView4);
        TextView tv4=findViewById(R.id.textView5);
        TextView tv5=findViewById(R.id.textView6);
        TextView tv6=findViewById(R.id.textView7);
        TextView tv7=findViewById(R.id.textView8);

        tv2.setText("김민수");
        tv3.setText("010-1234-5678");
        tv4.setText("서울시");
        tv5.setText("홍길동");
        tv6.setText("010-8765-4321");
        tv7.setText("울산시");

        animationLeft= AnimationUtils.loadAnimation(this,R.anim.translate_left);
        animationRight=AnimationUtils.loadAnimation(this,R.anim.translate_right);

        AnimListener listener=new AnimListener();
        AnimListener2 listener2=new AnimListener2();
        animationLeft.setAnimationListener(listener);
        animationRight.setAnimationListener(listener2);
        Button button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimThread thread = new AnimThread();
                thread.start();

            }
        });

    }

    private class AnimListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
            if(isOpenC1==false){container1.setVisibility(View.VISIBLE);}

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isOpenC1==true) { container1.setVisibility(View.GONE); isOpenC1=false; }
            else{isOpenC1=true; }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
    private class AnimListener2 implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
            if(isOpenC2==false){container2.setVisibility(View.VISIBLE);};
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isOpenC2==false){isOpenC2=true;}
            else{container2.setVisibility(View.GONE);isOpenC2=false;}
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private class AnimThread extends Thread{
        @Override
        public void run() {
            flag=true;
            while(flag) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (repeatSwitch == 1) {
                            container1.startAnimation(animationLeft);
                            container2.startAnimation(animationRight);
                            repeatSwitch = 2;
                        } else if (repeatSwitch == 2) {
                            container1.startAnimation(animationRight);
                            container2.startAnimation(animationLeft);
                            repeatSwitch = 1;
                        }
                        count++;
                        tv1.setText(count + "/2");
                        if (count == 2) {
                            count = 0;
                        }
                        Log.d("TAG","실행함");
                    }
                });
                try{Thread.sleep(5000);}
                catch (Exception e){}
            }
        }
    }
}