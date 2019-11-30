package com.example.control_estacionamiento_utec_electiva_i.Admin;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.control_estacionamiento_utec_electiva_i.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentsActiveAndNotActive_admin extends Fragment {


    public StudentsActiveAndNotActive_admin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_students_active_and_not_active_admin, container, false);

        return view;
    }

}
