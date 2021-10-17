package org.techtown.recycler2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.recyclerView);

        GridLayoutManager GridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(GridLayoutManager);
        PersonAdapter adapter=new PersonAdapter();

        adapter.addItem(new Person("황진호","010-1234-5678"));
        adapter.addItem(new Person("홍길동","010-1111-2222"));
        adapter.addItem(new Person("김민수","010-3333-4444"));
        adapter.addItem(new Person("김민정","010-5555-6666"));
        adapter.addItem(new Person("김민희","010-7777-8888"));
        adapter.addItem(new Person("이지수","010-9999-0000"));
        adapter.addItem(new Person("박상문","010-0000-1234"));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickedListener(new OnPersonItemClickedListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                Person item= adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"선택된 아이템: "+item.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }
}