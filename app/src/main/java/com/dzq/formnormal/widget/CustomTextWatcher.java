/*
 * @Author: Mars Tsang
 * @Mail: zmars@me.com
 */

/*
 * @Author: Mars Tsang
 * @Mail: zmars@me.com
 */

/*
 * @Author: Mars Tsang
 * @Mail: zmars@me.com
 */

/*
 * @Author: Mars Tsang
 * @Mail: zmars@me.com
 */

package com.dzq.formnormal.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import com.dzq.formnormal.R;
import com.dzq.formnormal.utils.ToastTools;


/**
 * 限制输入  数字 、大小写字母、汉字（不包括，。？！）
 */
public class CustomTextWatcher implements TextWatcher {
    private int mMaxLength = 0;
    private Context mContext = null;
    private int mType = 0; //  0,数字 、大小写字母、汉字;1,大小写字母、汉字; 2 数字 、大小写字母
    private String mToastStr = null;

    public CustomTextWatcher() {

    }

    public CustomTextWatcher(int type) {
        this.mType = type;
    }

    public CustomTextWatcher(Context context, int maxLength) {
        this.mMaxLength = maxLength;
        this.mContext = context;
        mToastStr = String.format(context.getString(R.string.max_input_prompt), "" + maxLength);
    }

    public CustomTextWatcher(Context context, int maxLength, int type) {
        this.mMaxLength = maxLength;
        this.mContext = context;
        this.mType = type;
        mToastStr = String.format(context.getResources().getString(R.string.max_input_prompt), ""
                + maxLength);
    }

    public CustomTextWatcher(Context context, int maxLength, int type, String toastStr) {
        this.mMaxLength = maxLength;
        this.mContext = context;
        this.mType = type;
        this.mToastStr = toastStr;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i2, int i3) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        try {
            if (mMaxLength != 0 && editable.toString().length() > mMaxLength) {
                editable.delete(mMaxLength, editable.toString().length());
                ToastTools.showToast(mContext, mToastStr);

                return;
            }
            for (int i = 0; i < editable.toString().length(); i++) {
                String tem = editable.toString().substring(i, i + 1);
                char[] temC = tem.toCharArray();
                int mid = temC[0];
                switch (mType) {
                    case 0:
                        if (mid >= 48 && mid <= 57) {//数字
                        } else if (mid >= 65 && mid <= 90) {//大写字母
                        } else if (isChinese(tem.charAt(0))) {//中文
                        } else if (mid >= 97 && mid <= 122) {//小写字母
                        } else {
                            editable.delete(i, i + 1);
                            i--;
                        }
                        break;
                    case 1:
                        if (mid >= 65 && mid <= 90) {//大写字母
                        } else if (isChinese(tem.charAt(0))) {//中文
                        } else if (mid >= 97 && mid <= 122) {//小写字母
                        } else {
                            editable.delete(i, i + 1);
                            i--;
                        }
                        break;
                    case 2:
                        if (mid >= 48 && mid <= 57) {//数字
                        } else if (mid >= 65 && mid <= 90) {//大写字母
                        } else if (mid >= 97 && mid <= 122) {//小写字母
                        } else {
                            editable.delete(i, i + 1);
                            i--;
                        }
                        break;
                    case 3:
                        if (mid >= 48 && mid <= 57) {//数字
                        } else if (mid >= 65 && mid <= 90) {//大写字母
                        } else if (isChinese(tem.charAt(0))) {//中文
                        } else if (mid >= 97 && mid <= 122) {//小写字母
                        } else if (specialSign(tem.charAt(0))) {//特殊符号
                        } else {
                            editable.delete(i, i + 1);
                            i--;
                        }
                        break;
                    default:
                        break;
                }


            }

        } catch (Exception ignored) {
        }
    }

    /**
     * 判定输入  允许的特殊符号
     *
     * @param c
     * @return
     */
    public boolean specialSign(char c) {
        String str = c + "";
        if (str.equals(" ") || str.equals("&")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */
    public boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            String str = c + "";
            if (str.equals("，") || str.equals("。") || str.equals("？") || str.equals("！")) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

}
