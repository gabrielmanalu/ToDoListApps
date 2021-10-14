package com.example.todolistapps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolistapps.model.DatabaseHandler;
import com.example.todolistapps.model.ToDoList;

public class AddToDoListActivity extends AppCompatActivity {

    EditText edtName, edtDate, edtPriority;
    Button addButton, backButton;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        edtName = findViewById(R.id.editName);
        edtDate = findViewById(R.id.editDeadline);
        edtPriority = findViewById(R.id.editPriority);

        addButton = findViewById(R.id.addTaskButton);
        backButton = findViewById(R.id.backButton);
        databaseHandler = new DatabaseHandler(AddToDoListActivity.this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTaskObjectToDatabase();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void addTaskObjectToDatabase(){
//        String priceValueString = edtPrice.getText().toString();
//        if(TextUtils.isEmpty(edtPrice.getText().toString())){
//            priceValueString = "0.0";
//        }
        String nameValue = edtName.getText().toString();
        String dateValue = edtDate.getText().toString();
        String priorityValue = edtPriority.getText().toString();

//        if(TextUtils.isEmpty(edtName.getText().toString())){
//            nameValue = "No Name";
//        }
//        if(TextUtils.isEmpty(edtColor.getText().toString())){
//            colorValue = "No Color";
//        }
////
        try {

            double priceDoubleValue = Double.parseDouble(dateValue);
            ToDoList toDoList = new ToDoList(0, nameValue, priceDoubleValue, priorityValue);
            databaseHandler.addToDoList(toDoList);
            Toast.makeText(getApplicationContext(), toDoList.getToDoListName() + " is added to Database", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

}
