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
            gridLayout.setUseDefaultMargins(true);
            gridLayout.setPadding(5, 5, 5, 5);



            ToDoListButton[] toDoListButtons = new ToDoListButton[allToDoLists.size()];


            int index = 0;

            for (ToDoList toDoList : allToDoLists){
                toDoListButtons[index] = new ToDoListButton(MainActivity.this, toDoList);
                toDoListButtons[index].setText(toDoList.getToDoListName() + "\n" +
                        toDoList.getToDoListDeadline());


                switch (toDoList.getToDoListPriority()){
                    case "Red":
                        toDoListButtons[index].setBackgroundColor(Color.RED);
                        break;
                    case "Blue":
                        toDoListButtons[index].setBackgroundColor(Color.BLUE);
                        break;
                    case "Green":
                        toDoListButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    case "Black":
                        toDoListButtons[index].setBackgroundColor(Color.BLACK);
                        break;
                    case "Yellow":
                        toDoListButtons[index].setBackgroundColor(Color.YELLOW);
                        break;
                    case "Purple":
                        toDoListButtons[index].setBackgroundColor(Color.CYAN);
                        break;
                    case "White":
                        toDoListButtons[index].setBackgroundColor(Color.WHITE);
                    default:
                        toDoListButtons[index].setBackgroundColor(Color.GRAY);
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
                    Toast.makeText(MainActivity.this, toDoListButton.getToDoListID() + "", Toast.LENGTH_SHORT).show();

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
            case R.id.update_task:
                updateTask();
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

    private void updateTask() {
        Intent intent = new Intent(MainActivity.this, UpdateToDoListActivity.class);
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