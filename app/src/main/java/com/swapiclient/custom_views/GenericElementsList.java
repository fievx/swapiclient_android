package com.swapiclient.custom_views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swapiclient.R;
import com.swapiclient.model.SwElement;
import com.swapiclient.model.SwGenericElement;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jeremy on 24/11/2016.
 */

public class GenericElementsList extends FrameLayout {
    List<SwGenericElement> elementList;
    @BindView(R.id.tv_label)
    TextView tvLabel;
    @BindView(R.id.ll_elements)
    LinearLayout llElements;

    public GenericElementsList(Context context, List<SwGenericElement> list) {
        super(context);
        this.elementList = list;

        View view = LayoutInflater.from(context).inflate(R.layout.view_generic_elements_list, this);
        ButterKnife.bind(view, this);
        if(elementList!=null && !elementList.isEmpty())
            tvLabel.setText(elementList.get(0).getType());

        mapList(llElements, elementList);

    }

    private void mapList(LinearLayout layout, List<SwGenericElement> elementList) {
        for (SwElement swElement : elementList) {
            final TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_basic_text, null);
            textView.setText(swElement.getDisplayableName());
            layout.addView(textView);
        }
    }
}
