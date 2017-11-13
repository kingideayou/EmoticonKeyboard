package me.next.emojiselectview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.next.emojiselectview.view.EmoticonPageView;
import me.next.emoticonkeyboard.interfaces.OnEmoticonClickListener;
import me.next.emoticonkeyboard.interfaces.OnEmoticonLongClickListener;
import me.next.emoticonkeyboard.model.EmoticonBean;

import static me.next.emojiselectview.view.EmoticonPageView.COLUMN_COUNT;

/**
 * Created by NeXT on 17/11/8.
 */
public class EmoticonPagerAdapter extends PagerAdapter {

    private int mDelResId = EmoticonPageView.DEL_BUTTON_NONE;

    private Context mContext;
    private List<EmoticonBean> mEmoticonList = new ArrayList<>();
    private OnEmoticonClickListener mOnEmoticonClickListener;
    private OnEmoticonLongClickListener mOnEmoticonLongClickListener;

    public EmoticonPagerAdapter(Context context, List<EmoticonBean> emoticonList) {
        this.mContext = context;
        this.mEmoticonList = emoticonList;
    }

    public EmoticonPagerAdapter(Context context, List<EmoticonBean> emoticonList, int delResId) {
        this.mContext = context;
        this.mDelResId = delResId;
        this.mEmoticonList = emoticonList;
    }

    @Override
    public int getCount() {
        return mEmoticonList.size() / (getEmoticonWithoutDelButtonSize())
                + (mEmoticonList.size() % (getEmoticonWithoutDelButtonSize()) > 0 ? 1 : 0);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final List<EmoticonBean> gridItemList = getData(mEmoticonList, position);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(mContext, R.layout.item_emoticon, mDelResId, gridItemList);
        gridViewAdapter.setOnEmoticonClickListener(mOnEmoticonClickListener);
        EmoticonPageView emoticonPageView = new EmoticonPageView(mContext);
        emoticonPageView.setNumColumns(EmoticonPageView.COLUMN_COUNT);
        emoticonPageView.getEmoticonsGridView().setAdapter(gridViewAdapter);
        container.addView(emoticonPageView);
        return emoticonPageView;
    }

    private List<EmoticonBean> getData(List<EmoticonBean> emoticonBeanList, int position) {

        int pageTotalSize = getEmoticonPageSize();//每页表情数
        int leftEmoticonSize = emoticonBeanList.size() - getEmoticonWithoutDelButtonSize() * position;//当前剩余的表情数
        int pageSize = leftEmoticonSize >= pageTotalSize
                ? getEmoticonWithoutDelButtonSize() : leftEmoticonSize % pageTotalSize;
        List<EmoticonBean> list = new ArrayList<>(
                emoticonBeanList.subList(
                        getEmoticonWithoutDelButtonSize() * position,
                        getEmoticonWithoutDelButtonSize() * position + pageSize));

        if (!useDelButton()) {
            return list;
        }

        for (int i = 0; i < pageTotalSize - pageSize; i++) {
            list.add(null);
        }

        return list;
    }

    public void setOnEmoticonClickListener(OnEmoticonClickListener onEmoticonClickListener) {
        mOnEmoticonClickListener = onEmoticonClickListener;
    }

    public void setOnEmoticonLongClickListener(OnEmoticonLongClickListener onEmoticonLongClickListener) {
        mOnEmoticonLongClickListener = onEmoticonLongClickListener;
    }

    private int getEmoticonWithoutDelButtonSize() {
        return getEmoticonPageSize()  - (useDelButton() ? 1 : 0);
    }

    private int getEmoticonPageSize() {
        return COLUMN_COUNT * EmoticonPageView.LINE_COUNT;
    }

    private boolean useDelButton() {
        return mDelResId != EmoticonPageView.DEL_BUTTON_NONE;
    }

}
