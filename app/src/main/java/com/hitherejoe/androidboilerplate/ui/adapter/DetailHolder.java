package com.hitherejoe.androidboilerplate.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.data.model.Item;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

@LayoutId(R.layout.item_detail)
public class DetailHolder extends ItemViewHolder<Item> {

    @ViewId(R.id.text_detail)
    TextView mDetailText;

    public DetailHolder(View view) {
        super(view);
    }

    @Override
    public void onSetValues(Item item, PositionInfo positionInfo) {
        mDetailText.setText(item.name);
    }

}