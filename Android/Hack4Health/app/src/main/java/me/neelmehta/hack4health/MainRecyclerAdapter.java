package me.neelmehta.hack4health;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/**
 * Created by rahuldominic on 05/11/16.
 */
class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private ArrayList<TaskModal> mDataset = new ArrayList<>();
    private ArrayList<CustomCountDownTimer> countdownTimers = new ArrayList<>();

    MainRecyclerAdapter(Context context, ArrayList<TaskModal> tasksDataSet) {
        mDataset = tasksDataSet;

        Collections.sort(mDataset, new Comparator<TaskModal>() {
            @Override
            public int compare(TaskModal o1, TaskModal o2) {
                return (int) -((o1.getTimeInMilliseconds() - (System.currentTimeMillis() - o1.getTimestamp())) -
                        (o2.getTimeInMilliseconds() - (System.currentTimeMillis() - o2.getTimestamp())));
            }
        });

        for (TaskModal task : mDataset) {
            if (task.getTimeInMilliseconds() - (System.currentTimeMillis() - task.getTimestamp()) < 0) {
                task.setTimeInMilliseconds(0);
            } else {
                task.setTimeInMilliseconds(task.getTimeInMilliseconds() - (System.currentTimeMillis() - task.getTimestamp()));
            }
        }

        for (TaskModal task : mDataset) {
            if (task.getTimeInMilliseconds() != 0) {
                ((MainActivity) context).createTimer(task);
            }
        }

        for (TaskModal task : mDataset) {
            countdownTimers.add(new CustomCountDownTimer(task.getTimeInMilliseconds(), 100) {
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

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private String millisToTime(long millisUntilFinished) {
        String formatted_time;
        formatted_time = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
        return formatted_time;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView title, time_remaining;

        ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.item_title);
            time_remaining = (TextView) v.findViewById(R.id.time_remaining);
        }
    }
}

