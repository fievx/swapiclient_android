package com.swapiclient.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.swapiclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jeremy on 18/11/2016.
 */

public class LabelledDivider extends FrameLayout {

    @BindView(R.id.tv_label)
    TextView tvLabel;

    public LabelledDivider(Context context, AttributeSet attrs) {
        super(context, attrs);
        View v = LayoutInflater.from(context).inflate(R.layout.view_labelled_separator, this);
        ButterKnife.bind(this, v);

        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.LabelledDivider,
                    0, 0);

            tvLabel.setText(a.getText(R.styleable.LabelledDivider_label));

            a.recycle();
        }


    }
}
