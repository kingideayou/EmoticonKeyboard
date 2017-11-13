package me.next.emojiselectview.utils;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * Created by NeXT on 17/11/13.
 */

public class FontUtils {

    public static int getFontHeight(TextView textView) {
        Paint paint = new Paint();
        paint.setTextSize(textView.getTextSize());
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.ascent);
    }

}
