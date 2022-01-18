package org.techtown.mission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

Fragment1 fragment1;
Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout=findViewById(R.id.container);

        fragment1=new Fragment1();
        fragment2=new Fragment2();

        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container,fragment1).commit();

        TabLayout tab=findViewById(R.id.tablayout);
        tab.addTab(tab.newTab().setText("입력"));
        tab.addTab(tab.newTab().setText("조회"));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                Fragment selected=null;
                switch(position){
                    case 0:
                        selected=fragment1;
                        break;
                    case 1:
                        selected=fragment2;
                        break;
                }
                manager.beginTransaction().replace(R.id.container,selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}