package com.dzq.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dzq.formnormal.R;


/**
 * 表单
 * Created by ding on 2016/10/31.
 */
public class FormNormal2 extends LinearLayout {

    private TextView tvTitle;
    private TextView tvTitle2;
    private ImageView imvIndicator;
    private TextView tvValue;
    private ImageView imvLabel;

    public FormNormal2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context, attrs);
    }

    private void initUI(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_form_normal2, this, true);
        int left = getResources().getDimensionPixelSize(R.dimen.row_inner_left_padding);
        int top = left;
        int right = left;
        int bottom = left;

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle2 = (TextView) findViewById(R.id.tv_title2);
        imvIndicator = (ImageView) findViewById(R.id.imv_indicator);
        tvValue = (TextView) findViewById(R.id.tv_value);
        imvLabel = (ImageView) findViewById(R.id.imv_label);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FormNormal2);

        CharSequence hint = a.getText(R.styleable.FormNormal2_fn2Hint);
        CharSequence title = a.getText(R.styleable.FormNormal2_fn2Title);
        CharSequence title2 = a.getText(R.styleable.FormNormal2_fn2Title2);
        CharSequence text = a.getText(R.styleable.FormNormal2_fn2Text);
        boolean indicatorVisible = a.getBoolean(R.styleable.FormNormal2_fn2IndicatorVisible, true);
        int resId = a.getResourceId(R.styleable.FormNormal2_fn2ResId, -1);

        left = a.getDimensionPixelSize(R.styleable.FormNormal2_fn2LeftPadding, left);
        top = a.getDimensionPixelSize(R.styleable.FormNormal2_fn2TopPadding, top);
        right = a.getDimensionPixelSize(R.styleable.FormNormal2_fn2RightPadding, right);
        bottom = a.getDimensionPixelSize(R.styleable.FormNormal2_fn2BottomPadding, bottom);
        a.recycle();

        if (!TextUtils.isEmpty(hint)) {
            setHint(hint.toString());
        }

        if (!TextUtils.isEmpty(title)) {
            setTitle(title.toString());
        }

        if (!TextUtils.isEmpty(title2)) {
            setTitle2(title2.toString());
        }

        if (!TextUtils.isEmpty(text)) {
            setText(text.toString());
        }

        setImvLabelImageResource(resId);

        setImvIndicatorVisible(indicatorVisible);
        setPadding2(left, top, right, bottom);
    }

    public void setPadding2(int left, int top, int right, int bottom) {
        this.setPadding(left, top, right, bottom);
    }

    public void init(String title, String text) {
        setTitle(title);
        setHint(text);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTitle2(String title2) {
        tvTitle2.setText(title2);
    }

    public void setText(String text) {
        tvValue.setText(text);
    }

    public void setText(String text, int clrResId) {
        setText(text);
        tvValue.setTextColor(getResources().getColor(clrResId));
    }

    public void setTextDrawableRight(int drawableRightResId) {
        setHint("");
        Drawable drawable = null;
        try {
            drawable = getContext().getResources().getDrawable(drawableRightResId);
        } catch (Resources.NotFoundException e) {
            drawable = null;
        }
        tvValue.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }


    public void setText(String title, String text, int clrResId) {
        tvTitle.setText(title);
        setText(text);
        tvValue.setTextColor(getResources().getColor(clrResId));
    }

    public void setTextColor(int clrResId) {
        tvValue.setTextColor(getResources().getColor(clrResId));
    }

    // boolean isVisible  true 显示  false 隐藏
    public void setImvIndicatorVisible(boolean isVisible) {
        imvIndicator.setVisibility(isVisible ? VISIBLE : GONE);
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
        return tvValue;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setHint(String text) {
        tvValue.setHint(text);
    }

    public TextView getTvTitle2() {
        return tvTitle2;
    }
}