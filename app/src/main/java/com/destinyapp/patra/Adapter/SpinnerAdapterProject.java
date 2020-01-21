package com.destinyapp.patra.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.destinyapp.patra.Model.PatraProject;
import com.destinyapp.patra.R;

import java.util.List;

public class SpinnerAdapterProject extends ArrayAdapter<PatraProject> {

    public SpinnerAdapterProject(Context context, List<PatraProject> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_list, parent, false
            );
        }

        TextView textNama = convertView.findViewById(R.id.tvSpinner);
        TextView textId = convertView.findViewById(R.id.tvIdSpinner);
        PatraProject currentItem = getItem(position);

        if (currentItem != null) {
            textNama.setText(currentItem.nama_project);
            textId.setText(currentItem.id);
        }

        return convertView;
    }
}
