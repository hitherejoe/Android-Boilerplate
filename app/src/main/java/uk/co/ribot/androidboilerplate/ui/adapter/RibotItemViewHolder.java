package uk.co.ribot.androidboilerplate.ui.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Ribot;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

@LayoutId(R.layout.item_ribot)
public class RibotItemViewHolder extends ItemViewHolder<Ribot> {

    @ViewId(R.id.view_hex_color)
    View mHexColorBox;

    @ViewId(R.id.text_name)
    TextView mName;

    @ViewId(R.id.text_role)
    TextView mRole;

    public RibotItemViewHolder(View view) {
        super(view);
    }

    @Override
    public void onSetValues(Ribot ribot, PositionInfo positionInfo) {
        mHexColorBox.setBackgroundColor(Color.parseColor(ribot.hexCode));
        mName.setText(ribot.info.firstName + " " + ribot.info.lastName);
        mRole.setText(ribot.info.role);
    }

}