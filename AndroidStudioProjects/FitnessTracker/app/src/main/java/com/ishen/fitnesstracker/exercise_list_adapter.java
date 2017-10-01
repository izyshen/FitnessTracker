package com.ishen.fitnesstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by WingsOfRetribution on 2017-10-01.
 */

public class exercise_list_adapter extends ArrayAdapter<ExerciseProperties> {
    private LayoutInflater activity_inflater;
    private ArrayList<ExerciseProperties> exercises;
    private int view_resource_id;

    public exercise_list_adapter (
            Context context, int textViewResourceId, ArrayList<ExerciseProperties> exercises) {
        super(context, textViewResourceId, exercises);
        this.exercises = exercises;
        activity_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view_resource_id = textViewResourceId;
    }
    /*
    public View getView(int position, View convert_view, ViewGroup parents) {
        convert_view = activity_inflater.inflate(view_resource_id, null);

        ExerciseProperties properties = exercises.get(position);

        if (properties != null) {

        }
    }
    */
}


/*

    public View getView(int position, View convert_view, ViewGroup parents) {
        convert_view = m_inflater.inflate(m_view_resource_id, null);

        Exercise exercise = exercises.get(position);

        if (exercise != null) {
            TextView disp_name = (TextView) convert_view.findViewById(R.id.layout_exercise_name);
            TextView disp_box1 = (TextView) convert_view.findViewById(R.id.layout_box1);
            TextView disp_box2 = (TextView) convert_view.findViewById(R.id.layout_box2);

            if (disp_name != null) {
                disp_name.setText((exercise.getDisp_name()));
            }
            if (disp_box1 != null) {
                disp_box1.setText((exercise.getDisp_box1()));
            }
            if (disp_box2 != null) {
                disp_box2.setText((exercise.getDisp_box2()));
            }
        }
        return convert_view;
    }
}

*/