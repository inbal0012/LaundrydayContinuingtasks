package com.example.laundryday_continuingtasks;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ListView subTaskListView;
    ArrayList<SubTask> subTasks;

    EditText titleEt;
    EditText durationEt;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        titleEt = findViewById(R.id.add_title);
        durationEt = findViewById(R.id.add_duration);
        rg = findViewById(R.id.workWait_rg);

        //ListView & ArrayList Adapter Handler
        subTaskListView = findViewById(R.id.subTask_list);
        subTasks = new ArrayList<>();
        fillSubTasksList();
        SubTaskAdapter subTaskAdapter = new SubTaskAdapter(subTasks, this);
        subTaskListView.setAdapter(subTaskAdapter);

        final Button add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addSubTask()) {
                    resetAddLayout();
                    Log.d(TAG, "subTasks List: ");
                    for (int i = 0; i < subTasks.size(); i++) {
                        Log.d(TAG, "" + subTasks.get(i).toString());
                    }
                    closeKeyboard();
                }
            }
        });
    }

    private boolean addSubTask() {
        //title Handler
        String title = titleEt.getText().toString();

        //dumb user handler: empty title
        if (title.length() == 0) {
            String msg = getResources().getString(R.string.empty_title);
            Toast toast = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, -350);
            toast.show();
            Log.e(TAG, "addSubTask: dumb user handler: empty title");
            return false;
        }
        //END title Handler

        //duration Handler
        String durationStr = durationEt.getText().toString();
        //dumb user handler: empty duration
        if (durationStr.length() == 0) {
            String msg = getResources().getString(R.string.empty_duration);
            Toast toast = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, -350);
            toast.show();
            Log.e(TAG, "addSubTask: dumb user handler: empty duration");
            return false;
        }
        int duration = Integer.parseInt(durationEt.getText().toString());
        //END duration Handler

        //isWork Handler
        int radioButtonId = rg.getCheckedRadioButtonId();
        boolean isWork = false;
        if (radioButtonId == R.id.work_rb) {
            isWork = true;
        }
        //END isWork Handler

        subTasks.add(new SubTask(title, duration, isWork));
        subTaskListView.invalidate();

        //resetAddLayout();
        Toast.makeText(this, "subTask added", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "addSubTask: subTask added\n" + title + " for " + duration + "m, isWork? " + isWork);
        return true;
    }

    private void resetAddLayout() {
        titleEt.setText("");
        durationEt.setText("");
        RadioButton radioButton = findViewById(R.id.work_rb);
        radioButton.setChecked(true);
    }

    private void fillSubTasksList() {
        subTasks.add(new SubTask("Sort laundry",10, true));
        subTasks.add(new SubTask("Run a washing machine",5, true));
//        subTasks.add(new SubTask("Washing time", 105, false));
//        subTasks.add(new SubTask("Hang clothes",10, true));
//        subTasks.add(new SubTask("Dry clothes",60, false));
//        subTasks.add(new SubTask("Fold clothes",20, true));
//        subTasks.add(new SubTask("2Sort laundry",10, true));
//        subTasks.add(new SubTask("2Run a washing machine",5, true));
//        subTasks.add(new SubTask("2Washing time", 105, false));
//        subTasks.add(new SubTask("2Hang clothes",10, true));
//        subTasks.add(new SubTask("2Dry clothes",60, false));
//        subTasks.add(new SubTask("2Fold clothes",20, true));
//        subTasks.add(new SubTask("3Sort laundry",10, true));
//        subTasks.add(new SubTask("3Run a washing machine",5, true));
//        subTasks.add(new SubTask("3Washing time", 105, false));
//        subTasks.add(new SubTask("3Hang clothes",10, true));
//        subTasks.add(new SubTask("3Dry clothes",60, false));
//        subTasks.add(new SubTask("3Fold clothes",20, true));
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
