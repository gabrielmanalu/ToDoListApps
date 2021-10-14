package com.example.todolistapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.todolistapps.model.DatabaseHandler;
import com.example.todolistapps.model.ToDoList;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyToDoListActivity extends AppCompatActivity {

    @BindView(R.id.editName)
    TextInputEditText editName;
    @BindView(R.id.addTaskButton)
    Button addTaskButton;
    @BindView(R.id.backButton)
    Button backButton;
    @BindView(R.id.radio_group)
    RadioGroup priorityGroup;
    @BindView(R.id.radio_low)
    RadioButton radio_low;
    @BindView(R.id.radio_normal)
    RadioButton radio_normal;
    @BindView(R.id.radio_high)
    RadioButton radio_high;

    EditText editDeadline;
    String priority;
    DatabaseHandler databaseHandler;
    ToDoList editToDoList;
    SingleDateAndTimePickerDialog.Builder singleBuilder;
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("EEE d MMM HH:mm", Locale.getDefault());

    @OnClick(R.id.editTaskButton)
    void addTask() {
        setTask();
        Log.w("PRIORITY", priority);
    }



    @OnClick(R.id.backButton)
    void cancelTask() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_to_do_list);
        editDeadline = findViewById(R.id.editDeadline);
        String test = getIntent().getStringExtra("TO_DO_LIST_ID");
        Log.w("YOYO", test);
        ButterKnife.bind(this);
        init();
    }

    public void init(){
    }

    private void setTask() {
    }
}