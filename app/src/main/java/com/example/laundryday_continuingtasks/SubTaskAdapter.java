package com.example.laundryday_continuingtasks;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SubTaskAdapter extends BaseAdapter {
    private List<SubTask> subTasks;
    private Context context;

    public SubTaskAdapter(List<SubTask> subTasks, Context context) {
        this.subTasks = subTasks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return subTasks.size();
    }

    @Override
    public Object getItem(int position) {
        return subTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.sub_task_layout, parent, false);
        }

        SubTask subTask = subTasks.get(position);

        ImageView waitWork_iv = convertView.findViewById(R.id.wait_OR_work_iv);
        TextView title_tv = convertView.findViewById(R.id.subTaskTitle);
        TextView duration_tv = convertView.findViewById(R.id.subTaskDuration);
        ImageView reorder = convertView.findViewById(R.id.reorder_iv);

        //Work OR Wait img set
        if(subTask.getWork()) {
            waitWork_iv.setImageResource(R.drawable.ic_work_black_24dp);
        }
        else
            waitWork_iv.setImageResource(R.drawable.ic_wait_black_24dp);

        title_tv.setText(subTask.getTitle());
        int duration = subTask.getDuration();
        duration_tv.setText(subTask.durationToString());

        return convertView;
    }
}
