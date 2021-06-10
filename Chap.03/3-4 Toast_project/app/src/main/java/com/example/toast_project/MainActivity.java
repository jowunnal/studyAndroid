package com.example.toast_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
EditText editText1;
EditText editText2;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        button= findViewById(R.id.button);


    }

    public void onClickedButton1(View view){
        try{
            Toast toastView= Toast.makeText(this,"위치가 바뀐 토스트 메세지",Toast.LENGTH_LONG);
            int xOffset = Integer.parseInt(editText1.getText().toString());
            int yOffset = Integer.parseInt(editText2.getText().toString());

            toastView.setGravity(Gravity.TOP,xOffset,yOffset);
            toastView.show();
        } catch(NumberFormatException e){
            e.printStackTrace();
        }

    }

    public void onClickedButton2(View view){
        LayoutInflater inflater=getLayoutInflater();

        View layout =inflater.inflate(R.layout.toast_border,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text=layout.findViewById(R.id.textView);

        Toast toast=new Toast(this);
        text.setText("모양 바꾼 토스트");
        toast.setGravity(Gravity.CENTER,0,-100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void onClickedButton3(View v){
        Snackbar.make(v,"스낵바임니다",Snackbar.LENGTH_LONG).show();
    }
}
