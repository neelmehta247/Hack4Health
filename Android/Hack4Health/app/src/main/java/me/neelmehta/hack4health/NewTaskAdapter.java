package me.neelmehta.hack4health;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by neel on 05/11/16 at 1:22 PM.
 */

public class NewTaskAdapter extends RecyclerView.Adapter<NewTaskViewholder> {
    private LayoutInflater inflater;
    private ArrayList<String> tasks;
    private ArrayList<Long> times;

    public NewTaskAdapter(Context context, ArrayList<String> tasks, ArrayList<Long> times) {
        inflater = LayoutInflater.from(context);
        this.tasks = tasks;
        this.times = times;
    }

    @Override
    public NewTaskViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.newtask_viewholder, parent, false);

        return new NewTaskViewholder(view);
    }

    @Override
    public void onBindViewHolder(NewTaskViewholder holder, int position) {
        if (position == tasks.size()) {
            holder.textView.setText("Add custom task");
        } else {
            holder.textView.setText(tasks.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size() + 1;
    }
}
