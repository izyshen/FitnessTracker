package com.ishen.fitnesstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

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

    public View getView(int position, View convert_view, ViewGroup parents) {
        convert_view = activity_inflater.inflate(view_resource_id, null);

        ExerciseProperties properties = exercises.get(position);

        if (properties != null) {
            EditText weightview = convert_view.findViewById(R.id.editWeight);
            EditText setview = convert_view.findViewById(R.id.editSet);
            EditText repview = convert_view.findViewById(R.id.editRep);
            EditText timeview = convert_view.findViewById(R.id.editTime);
            EditText speedview = convert_view.findViewById(R.id.editSpeed);
            Spinner weight_sp = convert_view.findViewById(R.id.weight_unit_sp);
            Spinner time_sp = convert_view.findViewById(R.id.time_unit_sp);
            Spinner speed_sp = convert_view.findViewById(R.id.speed_unit_sp);

            if (properties.getWeightbox() == 1) {
                weightview.setVisibility(View.VISIBLE);
                weight_sp.setVisibility(View.VISIBLE);
            } else {
                weightview.setVisibility(View.INVISIBLE);
                weight_sp.setVisibility(View.INVISIBLE);
            }
            if (properties.getSetbox() == 1) {
                setview.setVisibility(View.VISIBLE);
            } else {
                setview.setVisibility(View.INVISIBLE);
            }
            if (properties.getRepbox() == 1) {
                repview.setVisibility(View.VISIBLE);
            } else {
                repview.setVisibility(View.INVISIBLE);
            }
            if (properties.getTimebox() == 1) {
                timeview.setVisibility(View.VISIBLE);
                time_sp.setVisibility(View.VISIBLE);
            } else {
                timeview.setVisibility(View.INVISIBLE);
                time_sp.setVisibility(View.INVISIBLE);
            }
            if (properties.getSpeedbox() == 1) {
                speedview.setVisibility(View.VISIBLE);
                speed_sp.setVisibility(View.VISIBLE);
            } else {
                speedview.setVisibility(View.INVISIBLE);
                speed_sp.setVisibility(View.INVISIBLE);
            }
        }
        return convert_view;
    }

}