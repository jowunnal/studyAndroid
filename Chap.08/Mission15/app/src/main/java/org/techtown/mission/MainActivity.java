package org.techtown.mission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText ed1;
EditText ed2;
EditText ed3;
Animation transLeftAnim;
Animation transRightAnim;
Boolean isPageOpen=false;
ConstraintLayout constraintLayout;
Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout=findViewById(R.id.inputLayout);

        button1=findViewById(R.id.button1);
        Button button2=findViewById(R.id.button2);

        ed1=findViewById(R.id.editTextTextPersonName);
        ed2=findViewById(R.id.editTextTextPersonName2);
        ed3=findViewById(R.id.editTextTextPersonName3);

        transLeftAnim= AnimationUtils.loadAnimation(this,R.anim.translate);
        transRightAnim=AnimationUtils.loadAnimation(this,R.anim.translate_right);
        SlidingPageListener listener=new SlidingPageListener();
        transLeftAnim.setAnimationListener(listener);
        transRightAnim.setAnimationListener(listener);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.startAnimation(transLeftAnim);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.startAnimation(transRightAnim);
                if(getCurrentFocus()!=null){
                    InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                }

                Toast.makeText(getApplicationContext(),"이름:"+ed1.getText().toString()+" 나이:"+ed2.getText().toString()+" 생년월일:"+ed3.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
    private class SlidingPageListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
           if(!isPageOpen){
               constraintLayout.setVisibility(View.VISIBLE);
               button1.setVisibility(View.GONE);
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen){
                constraintLayout.setVisibility(View.GONE);
                isPageOpen=false;
                button1.setVisibility(View.VISIBLE);
            }
            else{
                isPageOpen=true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}