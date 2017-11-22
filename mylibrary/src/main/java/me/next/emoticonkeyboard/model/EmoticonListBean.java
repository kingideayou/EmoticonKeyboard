package me.next.emoticonkeyboard.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NeXT on 17/11/21.
 */

public class EmoticonListBean {

    int pageStart = -1;
    int pageEnd = -1;
    List<EmoticonBean> mEmoticonBeanList = new ArrayList<>();

    public EmoticonListBean(int pageStart, List<EmoticonBean> emoticonBeanList) {
        this.pageStart = pageStart;
        mEmoticonBeanList = emoticonBeanList;
    }

    public EmoticonListBean(List<EmoticonBean> emoticonBeanList) {
        mEmoticonBeanList = emoticonBeanList;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public List<EmoticonBean> getEmoticonBeanList() {
        return mEmoticonBeanList;
    }

    public void setEmoticonBeanList(List<EmoticonBean> emoticonBeanList) {
        mEmoticonBeanList = emoticonBeanList;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
}
