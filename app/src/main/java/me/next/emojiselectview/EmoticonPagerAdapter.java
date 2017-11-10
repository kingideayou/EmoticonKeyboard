package me.next.emojiselectview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.next.emojiselectview.view.EmoticonPageView;

import static me.next.emojiselectview.view.EmoticonPageView.COLUMN_COUNT;

/**
 * Created by NeXT on 17/11/8.
 */
public class EmoticonPagerAdapter<T> extends PagerAdapter {

    private int mDelResId = EmoticonPageView.DEL_BUTTON_NONE;
    private Context mContext;
    private List<T> mEmoticonList = new ArrayList<>();

    public EmoticonPagerAdapter(Context context, List<T> emoticonList) {
        this.mContext = context;
        this.mEmoticonList = emoticonList;
    }

    public EmoticonPagerAdapter(Context context, List<T> emoticonList, int delResId) {
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
        final List<GridItem> gridItemList = getData(position);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(mContext, R.layout.item_emoticon, mDelResId, gridItemList);
        EmoticonPageView emoticonPageView = new EmoticonPageView(mContext);
        emoticonPageView.setNumColumns(EmoticonPageView.COLUMN_COUNT);
        emoticonPageView.getEmoticonsGridView().setAdapter(gridViewAdapter);
        emoticonPageView.getEmoticonsGridView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isDeleteButton(position)) {
                    Toast.makeText(mContext, "删除", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (gridItemList.get(position) == null) {
                    return;
                }
                Toast.makeText(mContext, "点击第" + position + "个", Toast.LENGTH_SHORT).show();
            }
        });
        emoticonPageView.getEmoticonsGridView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "长按第" + position + "个", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        container.addView(emoticonPageView);
        return emoticonPageView;
    }

    private boolean isDeleteButton(int position) {
        return useDelButton() && position == getEmoticonPageSize() - 1;
    }

    private ArrayList<GridItem> getData(int position) {

        int pageTotalSize = getEmoticonPageSize();//每页表情数
        int leftEmoticonSize = mEmoticonList.size() - getEmoticonWithoutDelButtonSize() * position;//当前剩余的表情数
        int size = leftEmoticonSize > pageTotalSize ? getEmoticonWithoutDelButtonSize() : leftEmoticonSize % pageTotalSize;

        ArrayList<GridItem> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new GridItem("" + i, "" + i));
        }

        if (!useDelButton()) {
            return list;
        }

        for (int i = 0; i < pageTotalSize - size; i++) {
            list.add(null);
        }

        return list;
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
