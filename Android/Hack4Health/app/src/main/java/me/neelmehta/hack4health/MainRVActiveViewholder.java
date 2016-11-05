package me.neelmehta.hack4health;

import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by neel on 05/11/16 at 7:24 PM.
 */

public class MainRVActiveViewholder extends RecyclerView.ViewHolder {
    TextView title, time_remaining;
    CountDownTimer countDownTimer;

    public MainRVActiveViewholder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.item_title);
        time_remaining = (TextView) itemView.findViewById(R.id.time_remaining);
    }
}
