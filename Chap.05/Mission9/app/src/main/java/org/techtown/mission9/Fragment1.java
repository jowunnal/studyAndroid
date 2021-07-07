package org.techtown.mission9;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Fragment1 extends Fragment {
    Calendar cal=Calendar.getInstance();
    DatePickerDialog.OnDateSetListener listener;
    Button button;
    Button button2;
    EditText editText1;
    EditText editText2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.fragment1,container,false);

        button=rootView.findViewById(R.id.button1);
        button2=rootView.findViewById(R.id.button2);
        editText1=rootView.findViewById(R.id.editText);
        editText2=rootView.findViewById(R.id.editText2);

        SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/dd", Locale.getDefault());
        Date time= Calendar.getInstance().getTime();
        button.setText(sdf.format(time));

        listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                button.setText(year+"년"+(month+1)+"월"+dayOfMonth+"일");
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(),listener,cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"이름:"+editText1.getText()+" 나이:"+editText2.getText()+" 생년월일:"+button.getText(),Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }
}
