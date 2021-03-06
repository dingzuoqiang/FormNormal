package com.dzq.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
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
    private EditText etValue;
    private ImageView imvIndicator;
    private TextView tvValue;
    private ImageView imvLabel;
    private FrameLayout layContent;
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
        etValue = (EditText) findViewById(R.id.et_value);
        imvIndicator = (ImageView) findViewById(R.id.imv_indicator);
        tvValue = (TextView) findViewById(R.id.tv_value);
        imvLabel = (ImageView) findViewById(R.id.imv_label);
        layContent = (FrameLayout) findViewById(R.id.lay_content);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FormNormal);

        if (a != null) {
            CharSequence hint = a.getText(R.styleable.FormNormal_fnHint);
            CharSequence title = a.getText(R.styleable.FormNormal_fnTitle);
            CharSequence text = a.getText(R.styleable.FormNormal_fnText);
            boolean indicatorVisible = a.getBoolean(R.styleable.FormNormal_fnIndicatorVisible, true);
            editable = a.getBoolean(R.styleable.FormNormal_fnEditable, false);
            boolean fnGravityLeft = a.getBoolean(R.styleable.FormNormal_fnGravityLeft, false);// 内容靠左对其
            int resid = a.getResourceId(R.styleable.FormNormal_fnResId, -1);

            boolean fnSetClearable = a.getBoolean(R.styleable.FormNormal_fnSetClearable, false);//

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

            if (fnSetClearable) {
                setClearable();
            }
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

    // 设置 title minwidth
    public void setTitleMinWidth(int minWidth) {
        tvTitle.setMinWidth(minWidth);
        tvTitle.setMinimumWidth(minWidth);//必须同时设置这个
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

    public void setImvIndicatorImageResource(int resId) {
        if (resId != -1) {
            this.imvIndicator.setImageResource(resId);
        }
    }

    public String getText() {
        return getTextView().getText().toString();
    }

    public TextView getTextView() {
        return editable ? etValue : tvValue;
    }

    public EditText getEtValue() {
        return etValue;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public ImageView getImvIndicator() {
        return imvIndicator;
    }

    public FrameLayout getLayContent() {
        return layContent;
    }

    public ImageView getImvLabel() {
        return imvLabel;
    }

    public void setHint(String text) {
        etValue.setHint(text);
        tvValue.setHint(text);
    }

    public void setPadding2(int left, int top, int right, int bottom) {
        this.setPadding(left, top, right, bottom);
    }

    public void setClearable() {
        imvIndicator.setVisibility(INVISIBLE);
        setImvIndicatorImageResource(R.drawable.icon_clear);
        getTextView().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                imvIndicator.setVisibility(s.length() > 0 ? VISIBLE : INVISIBLE);
            }
        });
        imvIndicator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getTextView().setText("");
            }
        });
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
            case TYPE_CLASS_NUMBER: //
                etValue.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case TYPE_CLASS_PHONE: //
                etValue.setInputType(InputType.TYPE_CLASS_PHONE);
                break;
            case TYPE_PASSWORD: //
                etValue.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                        .TYPE_TEXT_VARIATION_PASSWORD);
                break;
            case TYPE_VISIBLE_PASSWORD: //
                etValue.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                        .TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;

            case TYPE_NUMBER_OR_DECIMAL: //
                etValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            case TYPE_TEXT_VARIATION_EMAIL_ADDRESS: //
                etValue.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            default:
                break;
        }

    }

    public enum FormNormalTypeEnum {
        //        0 任意字符串(默认) 、1 手机号 、2 密码   4 只能输入数字和小数点 5 可见密码 6 数字
        TYPE_NORMAL(0),
        TYPE_CLASS_PHONE(1),
        TYPE_PASSWORD(2),
        TYPE_NUMBER_OR_DECIMAL(4),
        TYPE_VISIBLE_PASSWORD(5),
        TYPE_CLASS_NUMBER(6),
        TYPE_TEXT_VARIATION_EMAIL_ADDRESS(7);

        private int mCode;

        public int getCode() {
            return mCode;
        }

        FormNormalTypeEnum(int code) {
            this.mCode = code;
        }


    }

}