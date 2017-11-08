package me.next.emojiselectview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<GridItem> gridItemList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            gridItemList.add(new GridItem());
        }

        mViewPager = (ViewPager) findViewById(R.id.vp_emoticon);
        mViewPager.setAdapter(new EmoticonPagerAdapter<>(getApplicationContext(), gridItemList, R.mipmap.ic_launcher_round));

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);

    }



}
