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

public class KeyValueView extends FrameLayout {
    @BindView(R.id.tv_value)
    TextView tvValue;
    @BindView(R.id.tv_key)
    TextView tvKey;

    public KeyValueView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View v = LayoutInflater.from(context).inflate(R.layout.view_key_value, this);
        ButterKnife.bind(this, v);

        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.KeyValueView,
                    0, 0);

            tvKey.setText(a.getText(R.styleable.KeyValueView_key));
            tvValue.setText(a.getText(R.styleable.KeyValueView_value));

            a.recycle();
        }

    }

    public void setValue (String value){
        if (tvValue!=null) {
            tvValue.setText(value);
        }
    }
}
