package com.example.todolistapps.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapps.R;
import com.example.todolistapps.fragment.FinishedTask;
import com.example.todolistapps.model.ToDoList;
import com.example.todolistapps.view.FinishedTaskViewHolder;

import java.util.List;

public class FinishedTaskListAdapter extends RecyclerView.Adapter<FinishedTaskViewHolder> {

    private List<ToDoList> mToDoListList;
    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    public FinishedTaskListAdapter(List mToDoList, Context mContext){
        this.mContext = mContext;
        this.mToDoListList = mToDoList;
    }

    @NonNull
    @Override
    public FinishedTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list, parent, false);
       return new FinishedTaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinishedTaskViewHolder holder, int position) {
        String finishedTask = "( " + mToDoListList.get(position).getToDoListDeadline() + " )" + "  " + mToDoListList.get(position).getToDoListName();
        holder.getFinishedTask().setText(finishedTask);
    }

    @Override
    public int getItemCount() {
        return mToDoListList.size();
    }
}
