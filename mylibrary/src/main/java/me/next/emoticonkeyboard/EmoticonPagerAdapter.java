package me.next.emoticonkeyboard;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.next.emoticonkeyboard.interfaces.OnEmoticonClickListener;
import me.next.emoticonkeyboard.interfaces.OnEmoticonLongClickListener;
import me.next.emoticonkeyboard.model.EmoticonBean;
import me.next.emoticonkeyboard.model.EmoticonListBean;
import me.next.emoticonkeyboard.view.EmoticonPageView;

import static me.next.emoticonkeyboard.view.EmoticonPageView.COLUMN_COUNT;

/**
 * Created by NeXT on 17/11/8.
 */
public class EmoticonPagerAdapter extends PagerAdapter {

    private int mDelResId = EmoticonPageView.DEL_BUTTON_NONE;

    private Context mContext;
    private List<EmoticonBean> mEmoticonList = new ArrayList<>();
    private List<EmoticonListBean> mEmoticonGroupList = new ArrayList<>();
    private OnEmoticonClickListener mOnEmoticonClickListener;
    private OnEmoticonLongClickListener mOnEmoticonLongClickListener;

    public EmoticonPagerAdapter(Context context, List<EmoticonBean> emoticonList) {
        this.mContext = context;
        this.mEmoticonList = emoticonList;
        this.mEmoticonGroupList.add(new EmoticonListBean(emoticonList));
    }

    public EmoticonPagerAdapter(Context context, List<EmoticonBean> emoticonList, int delResId) {
        this.mContext = context;
        this.mDelResId = delResId;
        this.mEmoticonList = emoticonList;
        this.mEmoticonGroupList.add(new EmoticonListBean(emoticonList));
    }

    public EmoticonPagerAdapter(Context context, int delResId, List<List<EmoticonBean>> emoticonList) {
        this.mContext = context;
        this.mDelResId = delResId;
        for (List<EmoticonBean> emoticonBeen : emoticonList) {
            this.mEmoticonList.addAll(emoticonBeen);
            this.mEmoticonGroupList.add(new EmoticonListBean(emoticonBeen));
        }
    }

    @Override
    public int getCount() {
        int count = 0;
        for (EmoticonListBean emoticonListBean : mEmoticonGroupList) {
            emoticonListBean.setPageStart(count);
            count += emoticonListBean.getEmoticonBeanList().size() / (getEmoticonWithoutDelButtonSize())
                    + (emoticonListBean.getEmoticonBeanList().size() % (getEmoticonWithoutDelButtonSize()) > 0 ? 1 : 0);
            emoticonListBean.setPageEnd(count);
        }
        return count;
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
        final List<EmoticonBean> gridItemList = getData(mEmoticonGroupList, position);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(mContext, R.layout.item_emoticon, mDelResId, gridItemList);
        gridViewAdapter.setOnEmoticonClickListener(mOnEmoticonClickListener);
        EmoticonPageView emoticonPageView = new EmoticonPageView(mContext);
        emoticonPageView.setNumColumns(EmoticonPageView.COLUMN_COUNT);
        emoticonPageView.getEmoticonsGridView().setAdapter(gridViewAdapter);
        container.addView(emoticonPageView);
        return emoticonPageView;
    }

    private List<EmoticonBean> getData(List<EmoticonListBean> emoticonListBeanList, int position) {

        List<EmoticonBean> emoticonBeanList = new ArrayList<>();
        EmoticonListBean currentEmotionListBean = null;
        for (EmoticonListBean emoticonListBean : emoticonListBeanList) {
            if (emoticonListBean.getPageEnd() > position) {
                currentEmotionListBean = emoticonListBean;
                break;
            }
        }

        if (currentEmotionListBean == null) {
            return emoticonBeanList;
        }
        emoticonBeanList = currentEmotionListBean.getEmoticonBeanList();

        position = position - currentEmotionListBean.getPageStart();

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

    public EmoticonListBean getEmoticonListBean(int position) {
        EmoticonListBean currentEmotionListBean = null;
        for (EmoticonListBean emoticonListBean : mEmoticonGroupList) {
            if (emoticonListBean.getPageEnd() > position) {
                currentEmotionListBean = emoticonListBean;
                break;
            }
        }
        return currentEmotionListBean;
    }

    public void setOnEmoticonClickListener(OnEmoticonClickListener onEmoticonClickListener) {
        mOnEmoticonClickListener = onEmoticonClickListener;
    }

    public void setOnEmoticonLongClickListener(OnEmoticonLongClickListener onEmoticonLongClickListener) {
        mOnEmoticonLongClickListener = onEmoticonLongClickListener;
    }

    public List<EmoticonListBean> getEmoticonGroupList() {
        return mEmoticonGroupList;
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
