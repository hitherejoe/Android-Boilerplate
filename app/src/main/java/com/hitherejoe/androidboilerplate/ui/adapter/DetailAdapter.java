package com.hitherejoe.androidboilerplate.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hitherejoe.androidboilerplate.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailHolder> {
    private List<String> mDetails;

    @Inject
    public DetailAdapter() {
        this.mDetails = new ArrayList<>();
    }

    @Override
    public DetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.item_detail, parent, false);
        return new DetailHolder(view);
    }

    @Override
    public void onBindViewHolder(final DetailHolder holder, final int position) {
        final Context context = holder.itemView.getContext();
        final String detail = mDetails.get(position);

        holder.detailText.setText(detail);
        holder.detailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(detail)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDetails.size();
    }

    public void setDetails(List<String> details) {
        mDetails = details;
        notifyDataSetChanged();
    }

    class DetailHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_detail)
        TextView detailText;

        public DetailHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}