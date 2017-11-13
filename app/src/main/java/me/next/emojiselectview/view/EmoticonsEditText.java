package me.next.emojiselectview.view;

import android.content.Context;
import android.util.AttributeSet;

import me.next.emojiselectview.utils.EmotionSpannableStringUtils;

/**
 * Created by NeXT on 17/11/13.
 */
public class EmoticonsEditText extends android.support.v7.widget.AppCompatEditText {

    public EmoticonsEditText(Context context) {
        super(context);
    }

    public EmoticonsEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmoticonsEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        EmotionSpannableStringUtils.filter(this, text, start, lengthBefore, lengthAfter);
    }
}
