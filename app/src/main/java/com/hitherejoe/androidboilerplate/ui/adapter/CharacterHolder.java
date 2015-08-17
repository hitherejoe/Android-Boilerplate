package com.hitherejoe.androidboilerplate.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.data.model.Character;

import timber.log.Timber;
import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

@LayoutId(R.layout.item_character)
public class CharacterHolder extends ItemViewHolder<Character> {

    @ViewId(R.id.text_name)
    TextView mNameText;

    @ViewId(R.id.text_description)
    TextView mDescriptionText;

    @ViewId(R.id.image_character)
    ImageView mCharacterImage;

    public CharacterHolder(View view) {
        super(view);
    }

    @Override
    public void onSetValues(Character character, PositionInfo positionInfo) {
        mNameText.setText(character.name);
        //mDescriptionText.setText(character.description);
        Timber.e(character.thumbnail.getImageUrl());
        Glide.with(getContext()).load(character.thumbnail.getImageUrl()).into(mCharacterImage);
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