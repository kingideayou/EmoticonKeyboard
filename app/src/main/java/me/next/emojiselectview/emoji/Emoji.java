package me.next.emojiselectview.emoji;

import me.next.emoticonkeyboard.model.EmoticonBean;

/**
 * Created by NeXT on 17/11/10.
 */

public class Emoji {

    public static EmoticonBean fromCodePoint(int codePoint) {
        EmoticonBean emoji = new EmoticonBean();
        emoji.setEmoticon(newString(codePoint));
        return emoji;
    }

    public static String newString(int codePoint) {
        if (Character.charCount(codePoint) == 1) {
            return String.valueOf(codePoint);
        } else {
            return new String(Character.toChars(codePoint));
        }
    }

    public static EmoticonBean fromChar(char ch) {
        EmoticonBean emoji = new EmoticonBean();
        emoji.setEmoticon(Character.toString(ch));
        return emoji;
    }

}
