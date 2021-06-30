package org.techtown.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements ListFragment.ImageSelectionCallback  {
ListFragment listFragment;
viewerFragment viewerFragment;
int[] images={R.drawable.pic1,R.drawable.pic2,R.drawable.pic3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager=getSupportFragmentManager();
        listFragment=(ListFragment) manager.findFragmentById(R.id.listfragment);
        viewerFragment=(viewerFragment) manager.findFragmentById(R.id.viewerfragment);
    }

    public void onImageSelected(int position){
        viewerFragment.setImage(images[position]);
    }
}
