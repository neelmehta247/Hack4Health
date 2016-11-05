package me.neelmehta.hack4health;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rahuldominic on 05/11/16.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private TaskModal[] mDataset;


    public MainRecyclerAdapter(TaskModal[] tasksDataSet) {
        mDataset = tasksDataSet;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(mDataset[position].getName());
        holder.time_remaining.setText(String.valueOf(mDataset[position].getTimeInMilliseconds()));

    }

    @Override
    public int getItemCount() {
        return mDataset.length;
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

