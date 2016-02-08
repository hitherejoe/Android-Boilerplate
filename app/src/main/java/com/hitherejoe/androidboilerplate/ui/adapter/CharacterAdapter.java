package com.hitherejoe.androidboilerplate.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.ui.activity.CharacterActivity;
import com.hitherejoe.androidboilerplate.ui.activity.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterHolder> {
    private List<Character> mCharacters;

    @Inject
    public CharacterAdapter() {
        this.mCharacters = new ArrayList<>();
    }

    @Override
    public CharacterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_character, parent, false);
        return new CharacterHolder(view);
    }

    @Override
    public void onBindViewHolder(final CharacterHolder holder, final int position) {
        final Context context = holder.itemView.getContext();

        final Character character = mCharacters.get(position);
        holder.nameText.setText(character.name);
        int filmCount = character.films.size();
        String description = context.getString(R.string.text_films_description, filmCount);
        holder.descriptionText.setText(filmCount == 0
                ? context.getString(R.string.text_no_description) : description);
        Glide.with(context)
                .load(getImageUrl(character.name))
                .into(holder.characterImage);


        holder.viewText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(CharacterActivity.getStartIntent(context, character));
            }
        });

        holder.characterContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(CharacterActivity.getStartIntent(context, character));
            }
        });

        holder.tabText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(DetailActivity.getStartIntent(context, character));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    public void setCharacters(List<Character> characters) {
        mCharacters = characters;
        notifyDataSetChanged();
    }

    private String getImageUrl(String name) {
        // Ugly, but the API doesn't provide images - so this is just for example image loading
        switch (name.toLowerCase()) {
            case "luke skywalker":
                return "http://img3.wikia.nocookie.net/__cb20091030151422/starwars/" +
                        "images/d/d9/Luke-rotjpromo.jpg";
            case "c-3po":
                return "http://img2.wikia.nocookie.net/__cb20131005124036/starwars/" +
                        "images/thumb/5/51/C-3PO_EP3.png/400px-C-3PO_EP3.png";
            case "r2-d2":
                return "http://img1.wikia.nocookie.net/__cb20090524204255/starwars/" +
                        "images/thumb/1/1a/R2d2.jpg/400px-R2d2.jpg";
            case "darth vader":
                return "http://img2.wikia.nocookie.net/__cb20130621175844/starwars/" +
                        "images/thumb/6/6f/Anakin_Skywalker_RotS.png/" +
                        "400px-Anakin_Skywalker_RotS.png";
            default:
                return "http://img2.wikia.nocookie.net/__cb20130221005853/starwars/" +
                        "images/thumb/9/9d/DSI_hdapproach.png/400px-DSI_hdapproach.png";
        }
    }

    class CharacterHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.container_character)
        View characterContainer;

        @Bind(R.id.text_name)
        TextView nameText;

        @Bind(R.id.text_description)
        TextView descriptionText;

        @Bind(R.id.image_character)
        ImageView characterImage;

        @Bind(R.id.text_view)
        TextView viewText;

        @Bind(R.id.text_tab)
        TextView tabText;

        public CharacterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}