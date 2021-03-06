package com.example.todolistapps;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.todolistapps.model.DatabaseHandler;
import com.example.todolistapps.model.ToDoList;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyToDoListActivity extends AppCompatActivity {

    @BindView(R.id.editName)
    TextInputEditText editName;
    @BindView(R.id.editTaskButton)
    Button editTaskButton;
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
    String editedPriority;
    DatabaseHandler databaseHandler;
    int edit = 0;
    SingleDateAndTimePickerDialog.Builder singleBuilder;
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("EEE d MMM HH:mm", Locale.getDefault());


    @OnClick(R.id.backButton)
    void cancelTask() {
        finish();
    }

    @OnClick(R.id.editTaskButton)
    void editTask(){
        edit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_to_do_list);
        editDeadline = findViewById(R.id.editDeadline);


        Bundle extras = getIntent().getExtras();

        String stringID = extras.getString("TO_DO_LIST_ID");
        int id = Integer.parseInt(stringID);
        String name = getIntent().getStringExtra("TO_DO_LIST_NAME");
        String deadline = getIntent().getStringExtra("TO_DO_LIST_DEADLINE");
        String priority = getIntent().getStringExtra("TO_DO_LIST_PRIORITY");

        String priorityLevel[] = {"LOW", "NORMAL", "HIGH"};

        ButterKnife.bind(this);

        editName.setHint(name);
        editDeadline.setHint(deadline);


       if(priority.equals(priorityLevel[0])) {
            radio_low.setChecked(true);
       }
       else if(priority.equals(priorityLevel[1])) {
            radio_normal.setChecked(true);
       }else if (priority.equals(priorityLevel[2])) {
            radio_high.setChecked(true);
       }


        editDeadline.setShowSoftInputOnFocus(false);
        editDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deadlineBuilder();
            }
        });

        edit = id;


    }

    private void edit() {
        databaseHandler = new DatabaseHandler(ModifyToDoListActivity.this);
        ToDoList toDoList = new ToDoList();
        toDoList.setToDoListName(editName.getText().toString());
        toDoList.setToDoListDeadline(editDeadline.getText().toString());
        toDoList.setToDoListPriority(editedPriority);
        if(toDoList.getToDoListName() != null || toDoList.getToDoListDeadline() != null || toDoList.getToDoListPriority() != null) {
            databaseHandler.modifyToDoListDatabaseByID(edit, toDoList.getToDoListName(),
                    toDoList.getToDoListDeadline(),
                    toDoList.getToDoListPriority());
//                    Toast.makeText(ModifyToDoListActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            Toast.makeText(ModifyToDoListActivity.this, "TASK EDITED", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(ModifyToDoListActivity.this, "Please insert new data", Toast.LENGTH_SHORT).show();
            return;
        }
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
                    .mainColor(Color.rgb(101,73,156))
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
                        }
                    }).display();
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str="";
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_low:
                if(checked)
                    str = "Low Priority";
                editedPriority = "LOW";
                break;
            case R.id.radio_normal:
                if(checked)
                    str = "Normal Priority";
                editedPriority = "NORMAL";
                break;
            case R.id.radio_high:
                if(checked)
                    str = "High Priority";
                editedPriority = "HIGH";
                break;
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}