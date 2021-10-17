package org.techtown.mission13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView tv;
EditText ed1;
EditText ed2;
EditText ed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.textView7);
        ed1=findViewById(R.id.editTextTextPersonName1);
        ed2=findViewById(R.id.editTextTextPersonName2);
        ed3=findViewById(R.id.editTextTextPersonName3);
        Button button=findViewById(R.id.button);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        final PersonAdapter adapter=new PersonAdapter();
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(new Person(ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString()));
                adapter.notifyDataSetChanged();
                if(adapter.getItemCount()!=0){
                    tv.setText(adapter.getItemCount()+"명");
                }
                else
                    tv.setText("0명");
            }
        });
    }
}