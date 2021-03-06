package me.next.emoticonkeyboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.next.emoticonkeyboard.interfaces.OnEmoticonClickListener;
import me.next.emoticonkeyboard.interfaces.OnEmoticonLongClickListener;
import me.next.emoticonkeyboard.model.EmoticonBean;
import me.next.emoticonkeyboard.view.EmoticonPageView;

/**
 * Created by NeXT on 17/11/7.
 */

public class GridViewAdapter extends ArrayAdapter<EmoticonBean> {

    protected final int mDefalutItemHeight;
    protected int mItemHeight;
    protected int mItemHeightMax;
    protected int mItemHeightMin;
    /*
     * 单个视图高度最高比例：mItemHeight * mItemHeightMaxRatio = mItemHeightMax
     */
    protected double mItemHeightMaxRatio = 2.22;
    private Context mContext;
    private int mLayoutResourceId;
    private int mDelResId = EmoticonPageView.DEL_BUTTON_NONE;
    private List<EmoticonBean> mGridData = new ArrayList<>();

    private OnEmoticonClickListener mOnEmoticonClickListener;
    private OnEmoticonLongClickListener mOnEmoticonLongClickListener;


    public GridViewAdapter(Context context, int layoutResId, ArrayList<EmoticonBean> objects) {
        super(context, layoutResId, objects);
        this.mContext = context;
        this.mGridData = objects;
        this.mLayoutResourceId = layoutResId;
        this.mDefalutItemHeight = this.mItemHeight = (int) context.getResources().getDimension(R.dimen.item_emoticon_size_default);
    }

    public GridViewAdapter(Context context, int layoutResId, int delResId, List<EmoticonBean> objects) {
        super(context, layoutResId, objects);
        this.mContext = context;
        this.mGridData = objects;
        this.mDelResId = delResId;
        this.mLayoutResourceId = layoutResId;
        this.mDefalutItemHeight = this.mItemHeight = (int) context.getResources().getDimension(R.dimen.item_emoticon_size_default);
    }

    public void setGridData(ArrayList<EmoticonBean> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    public List<EmoticonBean> getData() {
        return mGridData;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        final EmoticonBean item = mGridData.get(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mLayoutResourceId, null, false);

            holder = new ViewHolder();
            holder.llRoot = (LinearLayout) convertView.findViewById(R.id.ll_root);
            holder.mTextView = (TextView)convertView.findViewById(R.id.et_emoji);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_emoticon);

            convertView.setTag(holder);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnEmoticonClickListener != null) {
                        mOnEmoticonClickListener.onEmoticonClick(item, isDelButton(position));
                    }
                }
            });

            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnEmoticonLongClickListener != null && mOnEmoticonLongClickListener.onEmoticonLongClick(item, isDelButton(position));
                }
            });

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        updateUI(holder, parent);


        if (item == null && !isDelButton(position)) {
            return convertView;
        }
        if (isDelButton(position)) {
            holder.imageView.setImageResource(mDelResId);
            holder.imageView.setVisibility(View.VISIBLE);
            holder.mTextView.setVisibility(View.GONE);
        } else {

            if (item != null) {
                if (item.getIcon() == 0) {
                    showEmoji(position, holder);
                } else {
                    showEmoticon(holder, item);
                }
            } else {
                hideItem(holder);
            }
        }

        return convertView;
    }

    private void hideItem(ViewHolder holder) {
        holder.imageView.setVisibility(View.GONE);
        holder.mTextView.setVisibility(View.GONE);
    }

    private void showEmoji(int position, ViewHolder holder) {
        holder.imageView.setVisibility(View.GONE);
        holder.mTextView.setVisibility(View.VISIBLE);
        holder.mTextView.setText(mGridData.get(position).getEmoticon());
    }

    private void showEmoticon(ViewHolder holder, EmoticonBean item) {
        holder.imageView.setVisibility(View.VISIBLE);
        holder.mTextView.setVisibility(View.GONE);
        holder.imageView.setImageResource(item.getIcon());
    }

    private boolean isDelButton(int position) {
        return mDelResId != EmoticonPageView.DEL_BUTTON_NONE && position == getCount() - 1;
    }

    private void updateUI(ViewHolder viewHolder, ViewGroup parent) {
        if(mDefalutItemHeight != mItemHeight){
            viewHolder.imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mItemHeight));
        }
        mItemHeightMax = this.mItemHeightMax != 0 ? this.mItemHeightMax : (int) (mItemHeight * mItemHeightMaxRatio);
        mItemHeightMin = this.mItemHeightMin != 0 ? this.mItemHeightMin : mItemHeight;
        int realItemHeight = ((View) parent.getParent()).getMeasuredHeight() / EmoticonPageView.LINE_COUNT;
        realItemHeight = Math.min(realItemHeight, mItemHeightMax);
        realItemHeight = Math.max(realItemHeight, mItemHeightMin);
        viewHolder.llRoot.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, realItemHeight));
    }

    public void setOnEmoticonClickListener(OnEmoticonClickListener onEmoticonClickListener) {
        mOnEmoticonClickListener = onEmoticonClickListener;
    }

    public void setOnEmoticonLongClickListener(OnEmoticonLongClickListener onEmoticonLongClickListener) {
        mOnEmoticonLongClickListener = onEmoticonLongClickListener;
    }

    public void setItemHeightMaxRatio(double mItemHeightMaxRatio) {
        this.mItemHeightMaxRatio = mItemHeightMaxRatio;
    }

    private class ViewHolder {
        LinearLayout llRoot;
        TextView mTextView;
        ImageView imageView;
    }
}

