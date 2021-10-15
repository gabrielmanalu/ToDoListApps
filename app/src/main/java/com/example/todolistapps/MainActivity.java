package com.example.todolistapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolistapps.model.DatabaseHandler;
import com.example.todolistapps.model.ToDoList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler mDatabaseHandler;
    private int taskButtonWidth;
    private ScrollView scrollView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseHandler = new DatabaseHandler(MainActivity.this);
        Point screenSize = new Point();
        scrollView = findViewById(R.id.itemScrollView);

        getWindowManager().getDefaultDisplay().getSize(screenSize);
        taskButtonWidth = screenSize.x / 2;
        modifyUserInterface();


    }

    private void modifyUserInterface() {

        ArrayList<ToDoList> allToDoLists = mDatabaseHandler.returnAllToDoListObject();
        scrollView.removeAllViewsInLayout();

        if(allToDoLists.size() > 0){


            GridLayout gridLayout = new GridLayout(MainActivity.this);
            gridLayout.setRowCount((allToDoLists.size() + 1) / 2);
            gridLayout.setColumnCount(2);
            gridLayout.setElevation(20);
            gridLayout.setAlignmentMode(GridLayout.ALIGN_MARGINS);
            gridLayout.setUseDefaultMargins(true);



            ToDoListButton[] toDoListButtons = new ToDoListButton[allToDoLists.size()];


            int index = 0;

            for (ToDoList toDoList : allToDoLists){
                toDoListButtons[index] = new ToDoListButton(MainActivity.this, toDoList);
                toDoListButtons[index].setText(toDoList.getToDoListName() + "\n" +
                        toDoList.getToDoListDeadline());


                switch (toDoList.getToDoListPriority()){
                    case "HIGH":
                        toDoListButtons[index].setBackgroundColor(Color.rgb(255,255,0));
                        break;
                    case "NORMAL":
                        toDoListButtons[index].setBackgroundColor(Color.rgb(174,234,00));
                        break;
                    case "LOW":
                        toDoListButtons[index].setBackgroundColor(Color.rgb(0,176,255));
                        break;
                }

                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.setMargins(20,20,20,20);
                gridLayout.addView(toDoListButtons[index], taskButtonWidth,
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                gridLayout.setLayoutParams(layoutParams);
                setSingleEvent(gridLayout);


            }
            scrollView.addView(gridLayout);


        }

    }

    private void setSingleEvent(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ToDoListButton toDoListButton = (ToDoListButton) gridLayout.getChildAt(i);
            final int finalI = i;
            int finalI1 = i;
            toDoListButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editTask(toDoListButton.getToDoListID(),
                            toDoListButton.getToDoListName(),
                            toDoListButton.getToDoListDeadline(),
                            toDoListButton.getToDoListPriority());

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_task:
                addTask();
                return true;
            case R.id.delete_task:
                deleteTask();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void addTask() {
        Intent intent = new Intent(MainActivity.this, AddToDoListActivity.class);
        startActivity(intent);
    }

    private void deleteTask() {
        Intent intent = new Intent(MainActivity.this, DeleteToDoListActivity.class);
        startActivity(intent);
    }

    private void editTask(int id, String name, String deadline, String priority) {
        Intent intent = new Intent(MainActivity.this, ModifyToDoListActivity.class);
        Bundle extras = new Bundle();
        extras.putString("TO_DO_LIST_ID", id +"");
        intent.putExtras(extras);
        intent.putExtra("TO_DO_LIST_NAME", name);
        intent.putExtra("TO_DO_LIST_DEADLINE", deadline);
        intent.putExtra("TO_DO_LIST_PRIORITY", priority);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        modifyUserInterface();
    }
}