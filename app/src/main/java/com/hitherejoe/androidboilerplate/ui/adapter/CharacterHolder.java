package com.hitherejoe.androidboilerplate.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.ui.activity.CharacterActivity;
import com.hitherejoe.androidboilerplate.ui.activity.DetailActivity;

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

    @ViewId(R.id.text_view)
    TextView mViewText;

    @ViewId(R.id.text_tab)
    TextView mTabText;

    public CharacterHolder(View view) {
        super(view);
    }

    @Override
    public void onSetValues(Character character, PositionInfo positionInfo) {
        mNameText.setText(character.name);
        String description = character.description.trim();
        mDescriptionText.setText(description.isEmpty() ? getContext().getString(R.string.text_no_description) : description);
        Timber.e(character.thumbnail.getImageUrl());
        Glide.with(getContext()).load(character.thumbnail.getImageUrl()).into(mCharacterImage);
    }

    @Override
    public void onSetListeners() {
        mViewText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(DetailActivity.getStartIntent(getContext(), getItem()));
            }
        });

        mTabText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(CharacterActivity.getStartIntent(getContext(), getItem()));
            }
        });
    }
}