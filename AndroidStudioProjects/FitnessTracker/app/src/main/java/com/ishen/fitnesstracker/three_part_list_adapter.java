package com.ishen.fitnesstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by WingsOfRetribution on 2017-09-21.
 */

public class three_part_list_adapter extends ArrayAdapter<Exercise> {

    private LayoutInflater m_inflater;
    private ArrayList<Exercise> exercises;
    private int m_view_resource_id;

    public three_part_list_adapter(
            Context context,
            int textViewResourceId,
            ArrayList<Exercise> exercises) {
        super(context, textViewResourceId, exercises);
        this.exercises = exercises;
        m_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        m_view_resource_id = textViewResourceId;
    }

    public View getView(int position, View convert_view, ViewGroup parents) {
        convert_view = m_inflater.inflate(m_view_resource_id, null);

        Exercise exercise = exercises.get(position);

        if (exercise != null) {
            TextView disp_name = convert_view.findViewById(R.id.layout_exercise_name);
            TextView disp_box1 = convert_view.findViewById(R.id.layout_box1);
            TextView disp_box2 = convert_view.findViewById(R.id.layout_box2);

            if (disp_name != null) {
                disp_name.setText(exercise.getDisp_name());
            }
            if (disp_box1 != null) {
                disp_box1.setText(exercise.getDisp_box1());
            }
            if (disp_box2 != null) {
                disp_box2.setText(exercise.getDisp_box2());
            }
        }
        return convert_view;
    }
}