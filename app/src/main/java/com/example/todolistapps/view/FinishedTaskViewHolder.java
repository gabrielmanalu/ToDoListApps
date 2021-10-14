package com.example.todolistapps.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapps.R;

public class FinishedTaskViewHolder extends RecyclerView.ViewHolder {

    private TextView finishedTask;

    public FinishedTaskViewHolder(@NonNull View itemView) {
        super(itemView);
        finishedTask =itemView.findViewById(R.id.finishedTask);

    }

    public TextView getFinishedTask() {
        return finishedTask;
    }

    public void setFinishedTask(TextView finishedTask) {
        this.finishedTask = finishedTask;
    }
}
