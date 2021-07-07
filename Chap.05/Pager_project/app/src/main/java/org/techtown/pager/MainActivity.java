package org.techtown.pager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ViewPager pager;
int num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num>2){num=0;}
                pager.setCurrentItem(num++);
            }
        });
        pager=findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        MyPagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager());

        fragment fragment1=new fragment();
        adapter.addItem(fragment1);
        fragment2 fragment2=new fragment2();
        adapter.addItem(fragment2);
        fragment3 fragment3=new fragment3();
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter{
        ArrayList<Fragment> items=new ArrayList<Fragment>();
        public MyPagerAdapter(FragmentManager fm){
            super(fm);
        }
        public void addItem(Fragment item){
            items.add(item);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        public CharSequence getPageTitle(int position){
            return "페이지"+position;
        }
    }
}
