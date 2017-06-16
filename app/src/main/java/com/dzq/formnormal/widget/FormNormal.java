package com.dzq.formnormal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dzq.formnormal.R;


/**
 * 表单
 * Created by ding on 2016/10/31.
 */
public class FormNormal extends LinearLayout {

    private TextView tvTitle;
    private TextView etValue;
    private ImageView imvIndicator;
    private TextView tvValue;
    private ImageView imvLabel;
    private boolean editable;
    //    0 任意字符串(默认) 、1 手机号 、2 密码
    private FormNormalTypeEnum mTypeEnum = FormNormalTypeEnum.TYPE_NORMAL;

    public FormNormal(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context, attrs);
    }

    private void initUI(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_form_normal, this, true);
        int left = getResources().getDimensionPixelSize(R.dimen.row_inner_left_padding);
        int top = getResources().getDimensionPixelSize(R.dimen.row_inner_vertical_padding);
        int right = getResources().getDimensionPixelSize(R.dimen.row_inner_right_padding);
        int bottom = getResources().getDimensionPixelSize(R.dimen.row_inner_vertical_padding);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        etValue = (TextView) findViewById(R.id.et_value);
        imvIndicator = (ImageView) findViewById(R.id.imv_indicator);
        tvValue = (TextView) findViewById(R.id.tv_value);
        imvLabel = (ImageView) findViewById(R.id.imv_label);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FormNormal);

        if (a != null) {
            CharSequence hint = a.getText(R.styleable.FormNormal_fnHint);
            CharSequence title = a.getText(R.styleable.FormNormal_fnTitle);
            CharSequence text = a.getText(R.styleable.FormNormal_fnText);
            boolean indicatorVisible = a.getBoolean(R.styleable.FormNormal_fnIndicatorVisible, true);
            editable = a.getBoolean(R.styleable.FormNormal_fnEditable, false);
            boolean fnGravityLeft = a.getBoolean(R.styleable.FormNormal_fnGravityLeft, false);// 内容靠左对其
            int resid = a.getResourceId(R.styleable.FormNormal_fnResId, -1);

            int titleTextSize = a.getInteger(R.styleable.FormNormal_fnTitleTextSize, 17);
            int textSize = a.getInteger(R.styleable.FormNormal_fnTextSize, -1);

            int titleTextColor = a.getResourceId(R.styleable.FormNormal_fnTitleTextColor, R.color.color_333333);
            int textColor = a.getResourceId(R.styleable.FormNormal_fnTextColor, -1);

            left = a.getDimensionPixelSize(R.styleable.FormNormal_fnLeftPadding, left);
            top = a.getDimensionPixelSize(R.styleable.FormNormal_fnTopPadding, top);
            right = a.getDimensionPixelSize(R.styleable.FormNormal_fnRightPadding, right);
            bottom = a.getDimensionPixelSize(R.styleable.FormNormal_fnBottomPadding, bottom);

            a.recycle();

            setTitleTextSize(titleTextSize);
            setTitleTextColor(titleTextColor);

            setTextSize(textSize);
            setTextColor(textColor);

            if (!TextUtils.isEmpty(hint)) {
                setHint(hint.toString());
            }

            if (!TextUtils.isEmpty(title)) {
                setTitle(title.toString());
            }

            if (!TextUtils.isEmpty(text)) {
                setText(text.toString());
            }

            setImvIndicatorVisible(indicatorVisible);
            setEtValueEditable(editable);
            setGravity(fnGravityLeft);

            setImvLabelImageResource(resid);
        }
        setPadding2(left, top, right, bottom);

    }

    public void init(String title, String text) {
        setTitle(title);
        setHint(text);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    // 内容是否左对齐，   false 右对齐
    public void setGravity(boolean isLeft) {
        etValue.setGravity(isLeft ? Gravity.LEFT : Gravity.RIGHT);
        tvValue.setGravity(isLeft ? Gravity.LEFT : Gravity.RIGHT);
    }

    public void setText(String text) {
        etValue.setText(text);
        tvValue.setText(text);
    }

    public void setText(String text, int clrResId) {
        setText(text);
        etValue.setTextColor(getResources().getColor(clrResId));
        tvValue.setTextColor(getResources().getColor(clrResId));
    }

    public void setText(String title, String text, int clrResId) {
        tvTitle.setText(title);
        setText(text);
        etValue.setTextColor(getResources().getColor(clrResId));
        tvValue.setTextColor(getResources().getColor(clrResId));
    }

    public void setTextDrawableRight(int drawableRightResId) {
        setHint("");
        Drawable drawable = getContext().getResources().getDrawable(drawableRightResId);
        tvValue.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }

    public void setTitleTextColor(int clrResId) {
        tvTitle.setTextColor(getResources().getColor(clrResId));
    }

    public void setTitleTextSize(float size) {
        tvTitle.setTextSize(size);
    }

    public void setTextColor(int clrResId) {
        if (clrResId != -1) {
            etValue.setTextColor(getResources().getColor(clrResId));
            tvValue.setTextColor(getResources().getColor(clrResId));
        }
    }

    public void setTextSize(float size) {
        if (size != -1) {
            tvValue.setTextSize(size);
            etValue.setTextSize(size);
        }

    }

    // boolean isVisible  true 显示  false 隐藏
    public void setImvIndicatorVisible(boolean isVisible) {
        imvIndicator.setVisibility(isVisible ? VISIBLE : GONE);
    }

    public void setEtValueEditable(boolean editable) {
        etValue.setEnabled(editable);
        etValue.setVisibility(editable ? VISIBLE : GONE);
        tvValue.setVisibility(editable ? GONE : VISIBLE);
    }

    public void setImvLabelImageResource(int resId) {
        if (resId != -1) {
            imvLabel.setImageResource(resId);
            imvLabel.setVisibility(VISIBLE);
        } else {
            imvLabel.setVisibility(GONE);
        }
    }


    public String getText() {
        return getTextView().getText().toString();
    }

    public TextView getTextView() {
        return editable ? etValue : tvValue;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setHint(String text) {
        etValue.setHint(text);
        tvValue.setHint(text);
    }

    public void setPadding2(int left, int top, int right, int bottom) {
        this.setPadding(left, top, right, bottom);
    }

    public void setMaxLength(int maxLength) {
        etValue.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }

    public void addTextChangedListener(TextWatcher watcher) {
        etValue.addTextChangedListener(watcher);
    }

    /**
     * 该方法在组件可输入的情况下才会用到！
     * 选择输入框输入类型
     */
    public void chooseInputType(FormNormalTypeEnum typeEnum) {
        this.mTypeEnum = typeEnum;
        switch (mTypeEnum) {
            case TYPE_NORMAL: //
                etValue.setInputType(InputType.TYPE_CLASS_TEXT);

                break;
            case TYPE_PHONE_NUMBER: //
                etValue.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case TYPE_PASSWORD: //
                etValue.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                        .TYPE_TEXT_VARIATION_PASSWORD);

                break;
            case TYPE_NUMBER_OR_LETTERS: //
                etValue.addTextChangedListener(new CustomTextWatcher(2));
                break;

            case TYPE_NUMBER_OR_DECIMAL: //
                etValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            default:
                break;
        }

    }

    public enum FormNormalTypeEnum {
        //        0 任意字符串(默认) 、1 手机号 、2 密码 3 数字或者字母  4 只能输入数字和小数点
        TYPE_NORMAL(0),
        TYPE_PHONE_NUMBER(1),
        TYPE_PASSWORD(2),
        TYPE_NUMBER_OR_LETTERS(3),
        TYPE_NUMBER_OR_DECIMAL(4);

        private int mCode;

        public int getCode() {
            return mCode;
        }

        FormNormalTypeEnum(int code) {
            this.mCode = code;
        }


    }

}