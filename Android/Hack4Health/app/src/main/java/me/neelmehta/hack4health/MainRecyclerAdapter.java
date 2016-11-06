package me.neelmehta.hack4health;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/**
 * Created by rahuldominic on 05/11/16.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<TaskModal> mDataset = new ArrayList<>();
    private ArrayList<CustomCountDownTimer> countdownTimers = new ArrayList<>();
    private Context context;
    private final int ACTIVE = 0, INACTIVE = 1;

    MainRecyclerAdapter(Context context, ArrayList<TaskModal> tasksDataSet) {
        this.context = context;

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
            }
        }

        ArrayList<TaskModal> earlyTasks = new ArrayList<>();
        for (int i = 0; i < mDataset.size(); i++) {
            if (mDataset.get(i).getTimeInMilliseconds() - (System.currentTimeMillis() - mDataset.get(i).getTimestamp()) > 0) {
                earlyTasks.add(mDataset.get(i));
            } else {
                Collections.sort(earlyTasks, new Comparator<TaskModal>() {
                    @Override
                    public int compare(TaskModal o1, TaskModal o2) {
                        return (int) ((o1.getTimeInMilliseconds() - (System.currentTimeMillis() - o1.getTimestamp())) -
                                (o2.getTimeInMilliseconds() - (System.currentTimeMillis() - o2.getTimestamp())));
                    }
                });

                for (TaskModal earlyTask : earlyTasks) {
                    earlyTask.setTimeInMilliseconds(earlyTask.getTimeInMilliseconds() - (System.currentTimeMillis() - earlyTask.getTimestamp()));
                }

                for (int j = i; j < mDataset.size(); j++) {
                    earlyTasks.add(mDataset.get(j));
                }

                break;
            }
        }

        mDataset = earlyTasks;

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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        if (viewType == ACTIVE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.main_rv_active, parent, false);
            return new MainRVActiveViewholder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.main_rv_inactive, parent, false);
            return new MainRVInactiveViewholder(view);
        }
    }

    private void update() {
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
            }
        }

        ArrayList<TaskModal> earlyTasks = new ArrayList<>();
        for (int i = 0; i < mDataset.size(); i++) {
            if (mDataset.get(i).getTimeInMilliseconds() - (System.currentTimeMillis() - mDataset.get(i).getTimestamp()) > 0) {
                earlyTasks.add(mDataset.get(i));
            } else {
                Collections.sort(earlyTasks, new Comparator<TaskModal>() {
                    @Override
                    public int compare(TaskModal o1, TaskModal o2) {
                        return (int) ((o1.getTimeInMilliseconds() - (System.currentTimeMillis() - o1.getTimestamp())) -
                                (o2.getTimeInMilliseconds() - (System.currentTimeMillis() - o2.getTimestamp())));
                    }
                });

                for (TaskModal earlyTask : earlyTasks) {
                    earlyTask.setTimeInMilliseconds(earlyTask.getTimeInMilliseconds() - (System.currentTimeMillis() - earlyTask.getTimestamp()));
                }

                for (int j = i; j < mDataset.size(); j++) {
                    earlyTasks.add(mDataset.get(j));
                }

                break;
            }
        }

        mDataset = earlyTasks;

        notifyDataSetChanged();
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        if (getItemViewType(position) == ACTIVE) {
            final MainRVActiveViewholder holder = (MainRVActiveViewholder) viewHolder;
            holder.title.setText(mDataset.get(position).getName());

            holder.finishTaskButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.countDownTimer != null)
                        holder.countDownTimer.cancel();
                    mDataset.get(position).setTimeInMilliseconds(0);
                    ((MainActivity) context).removeTimer(mDataset.get(position));
                    update();
                }
            });

            if (holder.countDownTimer != null)
                holder.countDownTimer.cancel();

            // Set CountDownTimer for holder
            if (countdownTimers.get(position).mMillisInFuture != 0) {
                holder.countDownTimer = new CountDownTimer(mDataset.get(position).getTimeInMilliseconds()
                        - (System.currentTimeMillis() - mDataset.get(position).getTimestamp()), 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        holder.time_remaining.setText(String.valueOf
                                (millisToTime(millisUntilFinished)));
                    }

                    @Override
                    public void onFinish() {
                        mDataset.get(position).setTimeInMilliseconds(0);
                        update();
                    }
                }.start();
            }
        } else {
            final MainRVInactiveViewholder holder = (MainRVInactiveViewholder) viewHolder;
            holder.title.setText(mDataset.get(position).getName());
            holder.time_remaining.setText("Done");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position).getTimeInMilliseconds() == 0 ? INACTIVE : ACTIVE;
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
}

