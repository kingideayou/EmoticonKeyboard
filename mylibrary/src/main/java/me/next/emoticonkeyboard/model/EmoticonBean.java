package me.next.emoticonkeyboard.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NeXT on 17/11/10.
 */

public class EmoticonBean implements Parcelable {

    private int icon;
    private char value;
    private String emoticon;

    public EmoticonBean() {
    }

    public EmoticonBean(int icon, char value, String emoticon) {
        this.icon = icon;
        this.value = value;
        this.emoticon = emoticon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public String getEmoticon() {
        return emoticon;
    }

    public void setEmoticon(String emoticon) {
        this.emoticon = emoticon;
    }

    @Override
    public String toString() {
        return "EmoticonBean{" +
                "icon=" + icon +
                ", value=" + value +
                ", emoticon='" + emoticon + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.icon);
        dest.writeInt(this.value);
        dest.writeString(this.emoticon);
    }

    protected EmoticonBean(Parcel in) {
        this.icon = in.readInt();
        this.value = (char) in.readInt();
        this.emoticon = in.readString();
    }

    public static final Parcelable.Creator<EmoticonBean> CREATOR = new Parcelable.Creator<EmoticonBean>() {
        @Override
        public EmoticonBean createFromParcel(Parcel source) {
            return new EmoticonBean(source);
        }

        @Override
        public EmoticonBean[] newArray(int size) {
            return new EmoticonBean[size];
        }
    };
}
