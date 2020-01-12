package com.destinyapp.patra.Activity.ui.tarif;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.destinyapp.patra.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TarifFragment extends Fragment {

    public TarifFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarif, container, false);
    }

}
