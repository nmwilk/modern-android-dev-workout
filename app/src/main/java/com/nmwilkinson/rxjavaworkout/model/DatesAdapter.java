package com.nmwilkinson.rxjavaworkout.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nmwilkinson.rxjavaworkout.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by neil on 24/03/2016.
 */
public class DatesAdapter extends RecyclerView.Adapter {
    private final List<String> dates = new ArrayList<>();

    private final View.OnClickListener rowClickListener;

    public DatesAdapter(@NonNull final View.OnClickListener rowClickListener) {
        this.rowClickListener = rowClickListener;
    }

    public void addDate(final String date) {
        if (!this.dates.contains(date)) {
            this.dates.add(date);
        }
    }

    public void sort() {
        Collections.sort(this.dates, (lhs, rhs) -> rhs.compareTo(lhs));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int type) {
        // create a new view
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_view, parent, false);
        v.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        v.setOnClickListener(rowClickListener);
        return new ViewHolder((FrameLayout) v);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int index) {
        ((ViewHolder) viewHolder).textView.setText(dates.get(index));
        viewHolder.itemView.setId(index);
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public String getDate(final int index) {
        if (dates.size() > index) {
            return dates.get(index);
        }

        return "";
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        ViewHolder(final FrameLayout container) {
            super(container);
            textView = (TextView) container.findViewById(R.id.date);
        }
    }

}
