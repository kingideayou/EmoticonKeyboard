package me.next.emojiselectview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private EmoticonInputDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDetector = EmoticonInputDetector.with(this)
                .setEmotionView(findViewById(R.id.rl_emoticon))
                .bindToContent(findViewById(R.id.list))
                .bindToEditText((EditText) findViewById(R.id.edit_text))
                .bindToEmotionButton(findViewById(R.id.emotion_button))
                .build();

        initEmoticonView();

    }

    @Override
    public void onBackPressed() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
        }
    }


    private void initEmoticonView() {
        List<GridItem> gridItemList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            gridItemList.add(new GridItem());
        }

        ViewPager mViewPager = (ViewPager) findViewById(R.id.vp_emoticon);
        mViewPager.setAdapter(new EmoticonPagerAdapter<>(getApplicationContext(), gridItemList, R.mipmap.ic_launcher_round));

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);
    }


}
