package com.example.todolistapps;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolistapps.model.DatabaseHandler;
import com.example.todolistapps.model.ToDoList;

import java.util.ArrayList;

public class UpdateToDoListActivity extends AppCompatActivity {

    private DatabaseHandler mDatabaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_to_do_list);

        mDatabaseHandler = new DatabaseHandler(UpdateToDoListActivity.this);
        modifyUserInterface();

    }

    private void modifyUserInterface() {

        ArrayList<ToDoList> toDoLists = mDatabaseHandler.returnAllToDoListObject();

        if(toDoLists.size() > 0){

            ScrollView scrollView = new ScrollView(UpdateToDoListActivity.this);
            GridLayout gridLayout = new GridLayout(UpdateToDoListActivity.this);
            gridLayout.setRowCount(toDoLists.size());
            gridLayout.setColumnCount(5);

            TextView[] idTextView = new TextView[toDoLists.size()];
            EditText[][] editNamePriceColor = new EditText[toDoLists.size()][3];
            Button[] modifyButton = new Button[toDoLists.size()];

            Point screenSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);
            int screenWidth = screenSize.x;
            int index = 0;

            for(ToDoList toDoList : toDoLists){

                idTextView[index] = new TextView(UpdateToDoListActivity.this);
                idTextView[index].setGravity(Gravity.CENTER);
                idTextView[index].setText(toDoList.getToDoListID() + "");

                editNamePriceColor[index][0] = new EditText(UpdateToDoListActivity.this);
                editNamePriceColor[index][1] = new EditText(UpdateToDoListActivity.this);
                editNamePriceColor[index][2] = new EditText(UpdateToDoListActivity.this);

                editNamePriceColor[index][0].setText(toDoList.getToDoListName());
                editNamePriceColor[index][1].setText(toDoList.getToDoListDeadline() + "");
                editNamePriceColor[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                editNamePriceColor[index][2].setText(toDoList.getToDoListPriority());

                editNamePriceColor[index][0].setId(toDoList.getToDoListID() + 10);
                editNamePriceColor[index][1].setId(toDoList.getToDoListID() + 20);
                editNamePriceColor[index][2].setId(toDoList.getToDoListID() + 30);

                modifyButton[index] = new Button(UpdateToDoListActivity.this);
                modifyButton[index].setText("Modify");
                modifyButton[index].setId(toDoList.getToDoListID());
                modifyButton[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText editName = findViewById(toDoList.getToDoListID() + 10);
                        EditText editPrice = findViewById(toDoList.getToDoListID() + 20);
                        EditText editColor = findViewById(toDoList.getToDoListID() + 30);

                        String editedName = editName.getText().toString();
                        String editedPrice = editPrice.getText().toString();
                        String editedColor = editColor.getText().toString();

                        try {
                            Double editedPriceDouble = Double.parseDouble(editedPrice);
                            mDatabaseHandler.modifyToDoListDatabaseByID(toDoList.getToDoListID(), editedName, editedPriceDouble , editedColor);
                            Toast.makeText(UpdateToDoListActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        }catch (NumberFormatException e ){
                            e.printStackTrace();
                        }

                    }
                });

                gridLayout.addView(idTextView[index], (int)(screenWidth / 12.5), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(editNamePriceColor[index][0], (int) (screenWidth*0.30), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(editNamePriceColor[index][1], (int) (screenWidth*0.17), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(editNamePriceColor[index][2], (int) (screenWidth*0.15), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(modifyButton[index], (int)(screenWidth*0.30), ViewGroup.LayoutParams.WRAP_CONTENT );

                index++;
            }
            scrollView.addView(gridLayout);
            setContentView(scrollView);
        }

    }
}