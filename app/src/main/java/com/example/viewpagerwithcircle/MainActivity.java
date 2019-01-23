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
        viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
    }
}
