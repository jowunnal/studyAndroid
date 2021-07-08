package org.techtown.Mission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Toolbar toolbar;
NavigationView nav;
DrawerLayout drawer;
BottomNavigationView bottomNav;
Fragment1 fragment1;
Fragment2 fragment2;
Fragment3 fragment3;
ViewPager pager;
LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav=findViewById(R.id.nav);
        drawer=findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,
                drawer,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        linearLayout=findViewById(R.id.container);

        pager=findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        MyAdapter myAdapter=new MyAdapter(getSupportFragmentManager());
        fragment1=new Fragment1();
        myAdapter.addFragment(fragment1);
        fragment2=new Fragment2();
        myAdapter.addFragment(fragment2);
        fragment3=new Fragment3();
        myAdapter.addFragment(fragment3);
        pager.setAdapter(myAdapter);

        NavigationView nav=findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu1:
                        Toast.makeText(getApplicationContext(),"메뉴1이 선택되었습니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu2:
                        Toast.makeText(getApplicationContext(),"메뉴2가 선택되었습니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu3:
                        Toast.makeText(getApplicationContext(),"메뉴3이 선택되었습니다.",Toast.LENGTH_SHORT).show();
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        bottomNav=findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu1:
                        Toast.makeText(getApplicationContext(),"메뉴1이 선택되었습니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu2:
                        Toast.makeText(getApplicationContext(),"메뉴2가 선택되었습니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu3:
                        Toast.makeText(getApplicationContext(),"메뉴3이 선택되었습니다.",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });


    }
    class MyAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items=new ArrayList<Fragment>();

        public MyAdapter(FragmentManager fm){
            super(fm);
        }
        public void addFragment(Fragment fragment){
            items.add(fragment);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @NonNull
        @Override
        public Fragment getItem(int pos) {
            return items.get(pos);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}