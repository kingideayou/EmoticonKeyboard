package me.next.emojiselectview.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.next.emojiselectview.EmoticonSet;
import me.next.emoticonkeyboard.CenterImageSpan;

/**
 * Created by NeXT on 17/11/13.
 */

public class EmotionSpannableStringUtils {

    public static final int WRAP_DRAWABLE = -1;
    private static int emoticonSize = -1;
    public static final Pattern XHS_RANGE = Pattern.compile("\\[[a-zA-Z0-9\\u4e00-\\u9fa5]+\\]");

    public static Matcher getMatcher(CharSequence matchStr) {
        return XHS_RANGE.matcher(matchStr);
    }

    public static void filter(EditText editText, CharSequence text, int start, int lengthBefore, int lengthAfter) {
        emoticonSize = emoticonSize == -1 ? FontUtils.getFontHeight(editText) : emoticonSize;
        clearSpan(editText.getText(), start, text.toString().length());
        Matcher m = getMatcher(text.toString().substring(start, text.toString().length()));
        while (m.find()) {
            String key = m.group();
            int icon = EmoticonSet.getEmotionMap(editText.getContext()).get(key);
            if (icon > 0) {
                displayEmoticon(editText.getContext(), editText.getText(), icon, emoticonSize, start + m.start(), start + m.end());
            }
        }
    }

    public static Spannable spannableFilter(Context context, Spannable spannable, CharSequence text, int fontSize) {
        Matcher m = getMatcher(text);
        while (m.find()) {
            String key = m.group();
            int icon = EmoticonSet.getEmotionMap(context).get(key);
            if (icon > 0) {
                displayEmoticon(context, spannable, icon, fontSize, m.start(), m.end());
            }
        }
        return spannable;
    }

    private static void clearSpan(Spannable spannable, int start, int end) {
        if (start == end) {
            return;
        }
        CenterImageSpan[] oldSpans = spannable.getSpans(start, end, CenterImageSpan.class);
        for (CenterImageSpan oldSpan : oldSpans) {
            spannable.removeSpan(oldSpan);
        }
    }

    public static void displayEmoticon(Context context, Spannable spannable, int drawableId, int fontSize, int start, int end) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (drawable != null) {
            int itemHeight;
            int itemWidth;
            if (fontSize == WRAP_DRAWABLE) {
                itemHeight = drawable.getIntrinsicHeight();
                itemWidth = drawable.getIntrinsicWidth();
            } else {
                itemHeight = fontSize;
                itemWidth = fontSize;
            }
            drawable.setBounds(0, 0, itemHeight, itemWidth);
            CenterImageSpan imageSpan = new CenterImageSpan(drawable);
            spannable.setSpan(imageSpan, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }
    }

    public static void displayEmoticon(Context context, Spannable spannable, String emoticonName, int fontSize, int start, int end) {
        Drawable drawable = DrawableUtils.getDrawableFromAssets(context, emoticonName);
        if (drawable != null) {
            int itemHeight;
            int itemWidth;
            if (fontSize == WRAP_DRAWABLE) {
                itemHeight = drawable.getIntrinsicHeight();
                itemWidth = drawable.getIntrinsicWidth();
            } else {
                itemHeight = fontSize;
                itemWidth = fontSize;
            }
            drawable.setBounds(0, 0, itemHeight, itemWidth);
            CenterImageSpan imageSpan = new CenterImageSpan(drawable);
            spannable.setSpan(imageSpan, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }
    }

}
