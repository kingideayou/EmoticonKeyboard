package me.next.emojiselectview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.next.emojiselectview.emoji.EmojiPeople;
import me.next.emojiselectview.view.EmoticonsEditText;
import me.next.emoticonkeyboard.EmoticonInputDetector;
import me.next.emoticonkeyboard.EmoticonPagerAdapter;
import me.next.emoticonkeyboard.interfaces.OnEmoticonClickListener;
import me.next.emoticonkeyboard.model.EmoticonBean;
import me.next.emoticonkeyboard.view.pagerslidingtabstrip.PagerSlidingTabStrip;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private EmoticonInputDetector mDetector;
    EmoticonsEditText editText;

    List<List<EmoticonBean>> mEmotionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<EmoticonBean> gridItemList1 = Arrays.asList(EmojiPeople.DATA);
        List<EmoticonBean> gridItemList2 = EmoticonSet.getTiebaEmoticon(getApplicationContext());
        List<EmoticonBean> gridItemList3 = EmoticonSet.getWeiboEmoticon(getApplicationContext());

        mEmotionList.add(gridItemList1);
        mEmotionList.add(gridItemList2);
        mEmotionList.add(gridItemList3);

        editText = (EmoticonsEditText) findViewById(R.id.edit_text);

        boolean showTabLayout = mEmotionList != null && mEmotionList.size() > 0;

        mDetector = EmoticonInputDetector.with(this)
                .setEmotionView(findViewById(R.id.rl_emoticon))//表情选择视图
                .bindToContent(findViewById(R.id.list))
                .bindToEditText(editText)
                .bindToTableLayout(findViewById(R.id.tabs), showTabLayout)
                .bindToEmotionButton(findViewById(R.id.emotion_button))//控制表情显示按钮
                .build();

        initEmoticonView(mEmotionList);
    }

    private void initEmoticonView(final List<List<EmoticonBean>> emoticonBeanList) {
        final ViewPager mViewPager = (ViewPager) findViewById(R.id.vp_emoticon);
        final EmoticonPagerAdapter emoticonPagerAdapter = new EmoticonPagerAdapter(
                getApplicationContext(),
                R.mipmap.ic_launcher_round,
                emoticonBeanList, new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher});

        emoticonPagerAdapter.setOnEmoticonClickListener(new OnEmoticonClickListener() {
            @Override
            public void onEmoticonClick(EmoticonBean emoticonBean, boolean isDel) {
                if (isDel) {
                    clickDelButton();
                    return;
                }
                if (emoticonBean == null) {
                    return;
                }
                inputEmoticon(emoticonBean);
            }
        });
        mViewPager.setAdapter(emoticonPagerAdapter);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);

        PagerSlidingTabStrip pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pagerSlidingTabStrip.setViewPager(mViewPager);
        pagerSlidingTabStrip.setOnTabClickListener(new PagerSlidingTabStrip.OnTabClickListener() {
            @Override
            public void onTabClick(int position) {
                mViewPager.setCurrentItem(emoticonPagerAdapter.getEmoticonGroupList().get(position).getPageStart());
            }
        });
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
