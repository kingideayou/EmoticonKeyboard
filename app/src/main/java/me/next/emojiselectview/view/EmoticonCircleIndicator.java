package me.next.emojiselectview.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by NeXT on 17/11/21.
 */

public class EmoticonCircleIndicator extends CircleIndicator {
    public EmoticonCircleIndicator(Context context) {
        super(context);
    }

    public EmoticonCircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmoticonCircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EmoticonCircleIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setViewPager(ViewPager viewPager) {
        super.setViewPager(viewPager);
    }
}
