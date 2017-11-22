package me.next.emojiselectview;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import me.next.emoticonkeyboard.model.EmoticonBean;

/**
 * Created by NeXT on 17/11/10.
 */

public class EmoticonSet {

    public static List<EmoticonBean> getTiebaEmoticon(Context context) {

        List<EmoticonBean> tiebaEmoticonList = new ArrayList<>();

        Integer[] numArr = new Integer[]{
                Integer.valueOf(R.drawable.tieba_emotion_01),
                Integer.valueOf(R.drawable.tieba_emotion_02),
                Integer.valueOf(R.drawable.tieba_emotion_03),
                Integer.valueOf(R.drawable.tieba_emotion_04),
                Integer.valueOf(R.drawable.tieba_emotion_05),
                Integer.valueOf(R.drawable.tieba_emotion_06),
                Integer.valueOf(R.drawable.tieba_emotion_07),
                Integer.valueOf(R.drawable.tieba_emotion_08),
                Integer.valueOf(R.drawable.tieba_emotion_09),
                Integer.valueOf(R.drawable.tieba_emotion_10),
                Integer.valueOf(R.drawable.tieba_emotion_11),
                Integer.valueOf(R.drawable.tieba_emotion_12),
                Integer.valueOf(R.drawable.tieba_emotion_13),
                Integer.valueOf(R.drawable.tieba_emotion_14),
                Integer.valueOf(R.drawable.tieba_emotion_15),
                Integer.valueOf(R.drawable.tieba_emotion_16),
                Integer.valueOf(R.drawable.tieba_emotion_17),
                Integer.valueOf(R.drawable.tieba_emotion_18),
                Integer.valueOf(R.drawable.tieba_emotion_19),
                Integer.valueOf(R.drawable.tieba_emotion_20),
                Integer.valueOf(R.drawable.tieba_emotion_21),
                Integer.valueOf(R.drawable.tieba_emotion_22),
                Integer.valueOf(R.drawable.tieba_emotion_23),
                Integer.valueOf(R.drawable.tieba_emotion_24),
                Integer.valueOf(R.drawable.tieba_emotion_25),
                Integer.valueOf(R.drawable.tieba_emotion_26),
                Integer.valueOf(R.drawable.tieba_emotion_27),
                Integer.valueOf(R.drawable.tieba_emotion_28),
                Integer.valueOf(R.drawable.tieba_emotion_29),
                Integer.valueOf(R.drawable.tieba_emotion_30),
                Integer.valueOf(R.drawable.tieba_emotion_31),
                Integer.valueOf(R.drawable.tieba_emotion_32),
                Integer.valueOf(R.drawable.tieba_emotion_33),
                Integer.valueOf(R.drawable.tieba_emotion_34),
                Integer.valueOf(R.drawable.tieba_emotion_35),
                Integer.valueOf(R.drawable.tieba_emotion_36),
                Integer.valueOf(R.drawable.tieba_emotion_37),
                Integer.valueOf(R.drawable.tieba_emotion_38),
                Integer.valueOf(R.drawable.tieba_emotion_39),
                Integer.valueOf(R.drawable.tieba_emotion_40),
                Integer.valueOf(R.drawable.tieba_emotion_41),
                Integer.valueOf(R.drawable.tieba_emotion_42),
                Integer.valueOf(R.drawable.tieba_emotion_43),
                Integer.valueOf(R.drawable.tieba_emotion_44),
                Integer.valueOf(R.drawable.tieba_emotion_45),
                Integer.valueOf(R.drawable.tieba_emotion_46),
                Integer.valueOf(R.drawable.tieba_emotion_47),
                Integer.valueOf(R.drawable.tieba_emotion_48),
                Integer.valueOf(R.drawable.tieba_emotion_49),
                Integer.valueOf(R.drawable.tieba_emotion_50),
                Integer.valueOf(R.drawable.tieba_emotion_52),
                Integer.valueOf(R.drawable.tieba_emotion_53),
                Integer.valueOf(R.drawable.tieba_emotion_54),
                Integer.valueOf(R.drawable.tieba_emotion_55),
                Integer.valueOf(R.drawable.tieba_emotion_56),
                Integer.valueOf(R.drawable.tieba_emotion_57),
                Integer.valueOf(R.drawable.tieba_emotion_58),
                Integer.valueOf(R.drawable.tieba_emotion_59),
                Integer.valueOf(R.drawable.tieba_emotion_60),
                Integer.valueOf(R.drawable.tieba_emotion_61),
                Integer.valueOf(R.drawable.tieba_emotion_62),
                Integer.valueOf(R.drawable.tieba_emotion_63),
                Integer.valueOf(R.drawable.tieba_emotion_64),
                Integer.valueOf(R.drawable.tieba_emotion_65),
                Integer.valueOf(R.drawable.tieba_emotion_66),
                Integer.valueOf(R.drawable.tieba_emotion_67),
                Integer.valueOf(R.drawable.tieba_emotion_68),
                Integer.valueOf(R.drawable.tieba_emotion_69),
                Integer.valueOf(R.drawable.tieba_emotion_70)};

        String[] stringArray = context.getResources().getStringArray(R.array.tieba_emotion_array_1);

        if (numArr.length == stringArray.length) {
            for (int i = 0; i < numArr.length; i++) {
                tiebaEmoticonList.add(new EmoticonBean(numArr[i], ' ', stringArray[i]));
            }
        }

        return tiebaEmoticonList;

//        Map<String, Integer> tiebaEmoticonMap = new HashMap<>();
//        stringBuilder.append(((a) arrayList.get(arrayList.size() - 1)).c());
//        stringBuilder.append(")\\)");
//        Pattern.compile(stringBuilder.toString()), "<img src=\"$0\"/>", arrayList);
    }
}

