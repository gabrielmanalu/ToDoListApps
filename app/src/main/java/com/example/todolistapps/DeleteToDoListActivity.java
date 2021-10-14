package com.example.todolistapps;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.todolistapps.fragment.FinishedTask;
import com.example.todolistapps.model.DatabaseHandler;
import com.example.todolistapps.model.ToDoList;

import java.util.ArrayList;

public class DeleteToDoListActivity extends AppCompatActivity {

//    private DatabaseHandler mDatabaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_to_do_list_activity);
        FinishedTask mFinishedTask = FinishedTask.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.finished_task_container, mFinishedTask).commit();

//        mDatabaseHandler = new DatabaseHandler(DeleteToDoListActivity.this);
//        updateTheUserInterface();
    }

//    private void updateTheUserInterface() {
//        ArrayList<ToDoList> allTheToDoLists = mDatabaseHandler.returnAllToDoListObject();
//        RelativeLayout relativeLayout = new RelativeLayout(DeleteToDoListActivity.this);
//        ScrollView scrollView = new ScrollView(DeleteToDoListActivity.this);
//        RadioGroup radioGroup = new RadioGroup(DeleteToDoListActivity.this);
//
//        for(ToDoList toDoList : allTheToDoLists){
//            RadioButton radioButton = new RadioButton(DeleteToDoListActivity.this);
//            radioButton.setId(toDoList.getToDoListID());
//            radioButton.setText(toDoList.toString());
//            radioButton.setTextSize(20);
//            radioButton.setBackgroundColor(Color.RED);
//            radioGroup.addView(radioButton);
//        }
//
//        radioGroup.setOnCheckedChangeListener(DeleteToDoListActivity.this);
//        Button backButton = new Button(DeleteToDoListActivity.this);
//        backButton.setText("Back");
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        scrollView.addView(radioGroup);
//
//        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        buttonParams.setMargins(0, 0,0, 70);
//
//        relativeLayout.addView(backButton, buttonParams);
//
//        ViewGroup.LayoutParams scrollViewParams = new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//
//        relativeLayout.addView(scrollView, scrollViewParams);
//        setContentView(relativeLayout);
//
//    }

//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        mDatabaseHandler.deleteToDoListDatabaseByID(checkedId);
//        Toast.makeText(DeleteToDoListActivity.this, "TASK IS DONE", Toast.LENGTH_SHORT).show();
//        updateTheUserInterface();
//    }


}