package com.hitherejoe.androidboilerplate.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.data.model.Character;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

@LayoutId(R.layout.item_character)
public class CharacterHolder extends ItemViewHolder<Character> {

    @ViewId(R.id.text_name)
    TextView mNameText;

    public CharacterHolder(View view) {
        super(view);
    }

    @Override
    public void onSetValues(Character character, PositionInfo positionInfo) {
        mNameText.setText(character.name);
    }

    @Override
    public void onSetListeners() {
        mNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}