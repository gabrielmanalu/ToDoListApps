package com.example.todolistapps.fragment;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todolistapps.DeleteToDoListActivity;
import com.example.todolistapps.R;
import com.example.todolistapps.controller.FinishedTaskListAdapter;
import com.example.todolistapps.model.DatabaseHandler;
import com.example.todolistapps.model.ToDoList;

import java.util.List;
import java.util.zip.Inflater;

public class FinishedTask extends Fragment {

    RecyclerView mRecyclerView;
    FinishedTaskListAdapter mAdapter;
    private List<ToDoList> mToDoListLists;
    private ToDoList deletedList;
    private DatabaseHandler mDatabaseHandler;

    public FinishedTask() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FinishedTask newInstance() {
        FinishedTask task = new FinishedTask();
        return task;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mDatabaseHandler = new DatabaseHandler(context);
        mToDoListLists = mDatabaseHandler.returnAllToDoListObject();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finished_task, container, false);
        if (view != null) {
            mRecyclerView = view.findViewById(R.id.recycler_view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mAdapter = new FinishedTaskListAdapter(mToDoListLists, getContext());
            mRecyclerView.setAdapter(mAdapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mSimpleCallback);
            itemTouchHelper.attachToRecyclerView(mRecyclerView);

        }
        return view;
    }

    ItemTouchHelper.SimpleCallback mSimpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            switch(direction) {
                case ItemTouchHelper.RIGHT:
                case ItemTouchHelper.LEFT:

                    deletedList = mToDoListLists.get(position);
                    mDatabaseHandler.deleteToDoListDatabaseByID(deletedList.getToDoListID());
                    mToDoListLists.remove(deletedList);
                    mAdapter.notifyItemRemoved(position);
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "TASK IS DONE", Toast.LENGTH_SHORT).show();
            break;
            }
        }
    };
}