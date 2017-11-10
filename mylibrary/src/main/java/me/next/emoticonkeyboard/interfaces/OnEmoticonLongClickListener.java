package me.next.emoticonkeyboard.interfaces;

import me.next.emoticonkeyboard.model.EmoticonBean;

/**
 * Created by NeXT on 17/11/10.
 */

public interface OnEmoticonLongClickListener {
    boolean onEmoticonLongClick(EmoticonBean emoticonBean, boolean isDel);
}
