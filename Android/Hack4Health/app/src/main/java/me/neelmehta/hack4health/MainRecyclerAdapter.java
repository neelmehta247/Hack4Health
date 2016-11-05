package me.neelmehta.hack4health;

import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by rahuldominic on 05/11/16.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private ArrayList<TaskModal> mDataset = new ArrayList<>();
    private ArrayList<CustomCountDownTimer> countdownTimers = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView title, time_remaining;

        ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.item_title);
            time_remaining = (TextView) v.findViewById(R.id.time_remaining);
        }
    }

    public MainRecyclerAdapter(ArrayList<TaskModal> tasksDataSet) {
        mDataset = tasksDataSet;
        for (int i = 0; i < mDataset.size(); i++) {
            countdownTimers.add(new CustomCountDownTimer(mDataset.get(i).getTimeInMilliseconds(), 100) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                }
            }.start());
        }
    }

    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title.setText(mDataset.get(position).getName());

        // Set CountDownTimer for holder
        new CountDownTimer(countdownTimers.get(position).mMillisInFuture, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                holder.time_remaining.setText(String.valueOf
                        (millisToTime(millisUntilFinished)));
            }

            @Override
            public void onFinish() {
                holder.time_remaining.setText("0");
            }
        }.start();
    }

    private String millisToTime(long millisUntilFinished) {
        String formatted_time = "";
        long millis = 3600000;
        formatted_time = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return formatted_time;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

