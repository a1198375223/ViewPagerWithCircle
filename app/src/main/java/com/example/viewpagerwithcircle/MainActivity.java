package com.example.viewpagerwithcircle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.viewpagerwithcircle.view.ViewPagerWithCircle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPagerWithCircle viewPager = (ViewPagerWithCircle) findViewById(R.id.viewPager);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());
        viewPager.setCanRepeatScroll(adapter.getActuallyCount());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(100);
    }
}
