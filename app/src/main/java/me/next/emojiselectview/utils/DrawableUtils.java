package me.next.emojiselectview.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by NeXT on 17/11/13.
 */

public class DrawableUtils {

    public static Drawable getDrawableFromAssets(Context context, String imageFileName) {
        Drawable result = null;
        AssetManager assetManager = context.getAssets();
        InputStream is;
        try {
            is = assetManager.open(imageFileName);
            result = Drawable.createFromStream(is, null);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
