package me.next.emojiselectview.view;

/**
 * Created by NeXT on 17/11/7.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;

import me.next.emojiselectview.R;

public class EmoticonPageView extends RelativeLayout {

    public static final int LINE_COUNT = 3;
    public static final int COLUMN_COUNT = 6;
    public static final int DEL_BUTTON_NONE = -1;

    private GridView mGvEmotion;

    public GridView getEmoticonsGridView() {
        return mGvEmotion;
    }

    public EmoticonPageView(Context context) {
        this(context, null);
    }

    public EmoticonPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_emoticonpage, this);
        mGvEmotion = view.findViewById(R.id.gv_emotion);
        mGvEmotion.setMotionEventSplittingEnabled(false);
        mGvEmotion.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        mGvEmotion.setCacheColorHint(0);
        mGvEmotion.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mGvEmotion.setVerticalScrollBarEnabled(false);
    }

    public void setNumColumns(int row) {
        mGvEmotion.setNumColumns(row);
    }
}

