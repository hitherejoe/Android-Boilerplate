package com.hitherejoe.androidboilerplate.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.data.model.Boilerplate;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

@LayoutId(R.layout.item_boilerplate)
public class BoilerplateHolder extends ItemViewHolder<Boilerplate> {

    @ViewId(R.id.text_hello_world)
    TextView mBoilerPlateText;

    public BoilerplateHolder(View view) {
        super(view);
    }

    @Override
    public void onSetValues(Boilerplate boilerplate, PositionInfo positionInfo) {
        mBoilerPlateText.setText(boilerplate.androidBoilerplate);
    }

    @Override
    public void onSetListeners() {
        mBoilerPlateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}