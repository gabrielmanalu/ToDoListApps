package com.example.todolistapps;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolistapps.model.DatabaseHandler;
import com.example.todolistapps.model.ToDoList;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddToDoListActivity extends AppCompatActivity {

    @BindView(R.id.editName)
    TextInputEditText editName;
    @BindView(R.id.editPriority)
    TextInputEditText editPriority;
    //    @BindView(R.id.editDeadline)
//    TextInputEditText editDeadline;
    @BindView(R.id.addTaskButton)
    Button addTaskButton;
    @BindView(R.id.backButton)
    Button backButton;

    EditText editDeadline;
    DatabaseHandler databaseHandler;
    boolean isSelectDeadline;
    SingleDateAndTimePickerDialog.Builder singleBuilder;
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("EEE d MMM HH:mm", Locale.getDefault());

    @OnClick(R.id.addTaskButton)
    void addTask() {
        setTask();
    }

    @OnClick(R.id.backButton)
    void cancelTask() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        editDeadline = findViewById(R.id.editDeadline);
        ButterKnife.bind(this);
        init();

    }


    private void setTask() {

        if (!isSelectDeadline) {
            Toast.makeText(this, "Please Enter the Deadline time", Toast.LENGTH_SHORT).show();
            return;
        }
        ToDoList toDoList = new ToDoList();
        toDoList.setToDoListID(0);
        toDoList.setToDoListName(editName.getText().toString());
        toDoList.setToDoListDeadline(editDeadline.getText().toString());
        toDoList.setToDoListPriority(editPriority.getText().toString());
        databaseHandler.addToDoList(toDoList);
        Toast.makeText(this, toDoList.getToDoListName() + " is added!", Toast.LENGTH_SHORT).show();


    }

    private void init() {
        databaseHandler = new DatabaseHandler(AddToDoListActivity.this);
        editDeadline.setShowSoftInputOnFocus(false);
        editDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deadlineBuilder();
            }
        });
    }

    public void deadlineBuilder() {
        {

            final Date now = new Date();
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            final Calendar nowCalender = Calendar.getInstance();
            nowCalender.setTime(now);
            nowCalender.set(Calendar.HOUR_OF_DAY, hour);
            nowCalender.set(Calendar.MINUTE, minute);

            final Date defaultDate = nowCalender.getTime();

            new SingleDateAndTimePickerDialog.Builder(this)
                    .setTimeZone(TimeZone.getDefault())
                    .curved()
                    .mustBeOnFuture()
                    .defaultDate(defaultDate)
                    .displayListener(new SingleDateAndTimePickerDialog.DisplayListener() {
                        @Override
                        public void onDisplayed(SingleDateAndTimePicker picker) {
                        }
                    })
                    .title("Deadline")
                    .listener(new SingleDateAndTimePickerDialog.Listener() {
                        @Override
                        public void onDateSelected(Date date) {
                            editDeadline.setText(mSimpleDateFormat.format(date));
                            isSelectDeadline = true;
                        }
                    }).display();
        }
    }
}