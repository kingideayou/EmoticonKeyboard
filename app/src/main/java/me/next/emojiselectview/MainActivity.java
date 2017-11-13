package me.next.emojiselectview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import java.util.List;

import me.next.emojiselectview.view.EmoticonsEditText;
import me.next.emoticonkeyboard.interfaces.OnEmoticonClickListener;
import me.next.emoticonkeyboard.model.EmoticonBean;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private EmoticonInputDetector mDetector;
    EmoticonsEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EmoticonsEditText) findViewById(R.id.edit_text);

        mDetector = EmoticonInputDetector.with(this)
                .setEmotionView(findViewById(R.id.rl_emoticon))
                .bindToContent(findViewById(R.id.list))
                .bindToEditText(editText)
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
//        List<EmoticonBean> gridItemList = Arrays.asList(EmojiPeople.DATA);
        List<EmoticonBean> gridItemList = EmoticonSet.getTiebaEmoticon(getApplicationContext());

        ViewPager mViewPager = (ViewPager) findViewById(R.id.vp_emoticon);
        EmoticonPagerAdapter emoticonPagerAdapter = new EmoticonPagerAdapter(getApplicationContext(), gridItemList, R.mipmap.ic_launcher_round);
        emoticonPagerAdapter.setOnEmoticonClickListener(new OnEmoticonClickListener() {
            @Override
            public void onEmoticonClick(EmoticonBean emoticonBean, boolean isDel) {
                if (isDel) {
                    int length = editText.getText().length();
                    if (length <= 0) {
                        return;
                    }
                    editText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                    return;
                }

                if (emoticonBean == null) {
                    return;
                }

                int start = editText.getSelectionStart();
                int end = editText.getSelectionEnd();

                if (start < 0) {
                    editText.append(emoticonBean.getEmoticon());
                } else {
                    editText.getText().replace(
                            Math.min(start, end),
                            Math.max(start, end),
                            emoticonBean.getEmoticon(), 0, emoticonBean.getEmoticon().length());
                }
            }
        });
        mViewPager.setAdapter(emoticonPagerAdapter);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);
    }

}
