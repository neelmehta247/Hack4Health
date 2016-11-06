package me.neelmehta.hack4health;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by neel on 05/11/16 at 7:29 PM.
 */

public class MainRVInactiveViewholder extends RecyclerView.ViewHolder {
    TextView title, time_remaining;

    public MainRVInactiveViewholder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.item_title);
        time_remaining = (TextView) itemView.findViewById(R.id.time_remaining);
    }
}
