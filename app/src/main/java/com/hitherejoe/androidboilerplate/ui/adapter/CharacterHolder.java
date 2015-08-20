package com.hitherejoe.androidboilerplate.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.ui.activity.CharacterActivity;
import com.hitherejoe.androidboilerplate.ui.activity.DetailActivity;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

@LayoutId(R.layout.item_character)
public class CharacterHolder extends ItemViewHolder<Character> {

    @ViewId(R.id.container_character)
    View mCharacterContainer;

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
        int filmCount = character.films.size();
        String description = getContext().getString(R.string.text_films_description, filmCount);
        mDescriptionText.setText(filmCount == 0 ? getContext().getString(R.string.text_no_description) : description);
        Glide.with(getContext())
                .load(getImageUrl(character.name))
                .into(mCharacterImage);
    }

    @Override
    public void onSetListeners() {
        mViewText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(CharacterActivity.getStartIntent(getContext(), getItem()));
            }
        });

        mCharacterContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(CharacterActivity.getStartIntent(getContext(), getItem()));
            }
        });

        mTabText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(DetailActivity.getStartIntent(getContext(), getItem()));
            }
        });
    }

    private String getImageUrl(String name) {
        // Ugly, but the API doesn't provide images - so this is just for example image loading
        switch (name.toLowerCase()) {
            case "luke skywalker":
                return "http://img3.wikia.nocookie.net/__cb20091030151422/starwars/images/d/d9/Luke-rotjpromo.jpg";
            case "c-3po":
                return "http://img2.wikia.nocookie.net/__cb20131005124036/starwars/images/thumb/5/51/C-3PO_EP3.png/400px-C-3PO_EP3.png";
            case "r2-d2":
                return "http://img1.wikia.nocookie.net/__cb20090524204255/starwars/images/thumb/1/1a/R2d2.jpg/400px-R2d2.jpg";
            case "darth vader":
                return "http://img2.wikia.nocookie.net/__cb20130621175844/starwars/images/thumb/6/6f/Anakin_Skywalker_RotS.png/400px-Anakin_Skywalker_RotS.png";
            default:
                return "http://img2.wikia.nocookie.net/__cb20130221005853/starwars/images/thumb/9/9d/DSI_hdapproach.png/400px-DSI_hdapproach.png";
        }
    }
}