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

    private Context mContext;
    private List<T> mEmoticonList = new ArrayList<>();

    public EmoticonPagerAdapter(Context context, List<T> emoticonList) {
        this.mContext = context;
        this.mEmoticonList = emoticonList;
    }

    @Override
    public int getCount() {
        return mEmoticonList.size() / (EmoticonPageView.COLUMN_COUNT * EmoticonPageView.LINE_COUNT)
                + (mEmoticonList.size() % (EmoticonPageView.COLUMN_COUNT * EmoticonPageView.LINE_COUNT) > 0 ? 1 : 0);
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
        GridViewAdapter gridViewAdapter = new GridViewAdapter(mContext, R.layout.item_emoticon, getData(position));
        EmoticonPageView emoticonPageView = new EmoticonPageView(mContext);
        emoticonPageView.setNumColumns(6);
        emoticonPageView.getEmoticonsGridView().setAdapter(gridViewAdapter);
        emoticonPageView.getEmoticonsGridView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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

    private ArrayList<GridItem> getData(int position) {
        int pageEmoticonNum = COLUMN_COUNT * EmoticonPageView.LINE_COUNT;
        int pageNum = mEmoticonList.size() - pageEmoticonNum * (position + 1);
        int size = pageNum > 0 ? pageEmoticonNum : Math.abs(pageNum);

        ArrayList<GridItem> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new GridItem("" + i, "" + i));
        }

        return list;
    }

}
