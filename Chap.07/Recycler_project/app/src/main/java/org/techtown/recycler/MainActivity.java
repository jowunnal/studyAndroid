package org.techtown.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        PersonAdapter adapter=new PersonAdapter();

        adapter.addItem(new Person("황진호","010-1234-5678"));
        adapter.addItem(new Person("홍길동","010-1111-2222"));
        adapter.addItem(new Person("김민수","010-3333-4444"));
        adapter.addItem(new Person("김민정","010-5555-6666"));
        adapter.addItem(new Person("김민희","010-7777-8888"));
        adapter.addItem(new Person("이지수","010-9999-0000"));
        adapter.addItem(new Person("박상문","010-0000-1234"));
        recyclerView.setAdapter(adapter);
    }
}