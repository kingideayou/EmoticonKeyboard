package me.next.emojiselectview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;

import me.next.emojiselectview.emoji.EmojiPeople;
import me.next.emoticonkeyboard.EmoticonInputDetector;
import me.next.emoticonkeyboard.EmoticonPagerAdapter;
import me.next.emoticonkeyboard.interfaces.OnEmoticonClickListener;
import me.next.emoticonkeyboard.model.EmoticonBean;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private EmoticonInputDetector mDetector;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit_text);

        mDetector = EmoticonInputDetector.with(this)
                .setEmotionView(findViewById(R.id.rl_emoticon))//表情选择视图
                .bindToContent(findViewById(R.id.list))
                .bindToEditText(editText)
                .bindToEmotionButton(findViewById(R.id.emotion_button))//控制表情显示按钮
                .build();

        initEmoticonView();
    }

    private void initEmoticonView() {
        List<EmoticonBean> gridItemList = Arrays.asList(EmojiPeople.DATA);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.vp_emoticon);
        EmoticonPagerAdapter emoticonPagerAdapter = new EmoticonPagerAdapter(
                getApplicationContext(),
                gridItemList,
                R.mipmap.ic_launcher_round);
        emoticonPagerAdapter.setOnEmoticonClickListener(new OnEmoticonClickListener() {
            @Override
            public void onEmoticonClick(EmoticonBean emoticonBean, boolean isDel) {
                if (isDel) {
                    clickDelButton();
                    return;
                }
                inputEmoticon(emoticonBean);
            }
        });
        mViewPager.setAdapter(emoticonPagerAdapter);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
        }
    }

    /**
     * 插入表情
     * @param emoticonBean
     */
    private void inputEmoticon(EmoticonBean emoticonBean) {
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

    /**
     * 响应删除事件
     */
    private void clickDelButton() {
        int length = editText.getText().length();
        if (length <= 0) {
            return;
        }
        editText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
    }

}
