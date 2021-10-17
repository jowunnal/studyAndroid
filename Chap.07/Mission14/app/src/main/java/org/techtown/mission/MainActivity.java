package org.techtown.mission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv=findViewById(R.id.textView);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        ProductAdapter adapter= new ProductAdapter();
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


        adapter.addItem(new Product("남성구두","10만원","핫한 남성 구두 아이템",R.drawable.shoes));
        adapter.addItem(new Product("네셔널지오그래픽 가방","30만원","인기 상품 할인중",R.drawable.bag));
        adapter.addItem(new Product("롱코트","15만원","올겨울 최고의 가성비 롱코트",R.drawable.coat));
        adapter.addItem(new Product("무지티셔츠","1만원","심플하게 입을수잇는 베이직 무지반팔티셔츠",R.drawable.shirt));
        adapter.addItem(new Product("코카콜라","1000원","탄산음료 국내 업계1위 할인상품",R.drawable.coke));
        adapter.addItem(new Product("크라운산도","2500원","인기많은 과자",R.drawable.cookie));
        adapter.setOnProductClickListener(new ProduckOnClickListener() {
            @Override
            public void onItemClicked(ProductAdapter.ViewHolder holder, View v, int position) {
                Product item= adapter.getItem(position);
                Toast.makeText(getApplicationContext(),item.getName()+" 의 항목이 선택되었습니다.",Toast.LENGTH_LONG).show();
            }
        });


    }
}