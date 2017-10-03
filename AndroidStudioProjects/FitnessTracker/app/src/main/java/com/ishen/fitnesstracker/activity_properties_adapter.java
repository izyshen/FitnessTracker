package com.ishen.fitnesstracker;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by WingsOfRetribution on 2017-10-02.
 */

public class activity_properties_adapter extends ArrayAdapter<ExerciseProperties> {

    private LayoutInflater inflater;
    private ArrayList<ExerciseProperties> properties;
    private int view_resource_id;

    public activity_properties_adapter(
            Context context,
            int textViewResourceId,
            ArrayList<ExerciseProperties> property_list) {
        super(context, textViewResourceId, property_list);
        this.properties = property_list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view_resource_id = textViewResourceId;
    }

    public View get_view (int position, View convert_view, ViewGroup parents) {
        convert_view = inflater.inflate(view_resource_id, null);

        ExerciseProperties property = properties.get(position);

        if (property != null) {
            EditText property_name = convert_view.findViewById(R.id.layout_general_edit_text);
            if (property.getWeightbox() == 1 || property.getSpeedbox() == 1 || property.getTimebox()==1) {
                Spinner unit_sp = convert_view.findViewById(R.id.layout_general_spinner);
            }


        }
        return convert_view;
    }
}
