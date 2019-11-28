package com.example.control_estacionamiento_utec_electiva_i.docentes.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.control_estacionamiento_utec_electiva_i.HTTP.HttpRequestDocente;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;

public class HomeFragment extends Fragment {

    User user;

    Button btnEstacionamiento;

    TextView tvName,tvCarne,tvCode,tvHorario,tvEstado;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btnEstacionamiento = root.findViewById(R.id.btnStation);
        tvName = root.findViewById(R.id.tvNameDocente);
        tvCarne = root.findViewById(R.id.tvCarnetDocente);
        tvCode = root.findViewById(R.id.tvCode);
        tvHorario = root.findViewById(R.id.tvSchedule);
        tvEstado = root.findViewById(R.id.tvState);

        tvName.setText(user.getNombres() + " " + user.getApellidos());
        tvCarne.setText(user.getEmail());
        tvCode.setText(user.getAlias_edificio_parqueo_asignado());


        btnEstacionamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpRequestDocente httpRequestDocente = new HttpRequestDocente();
                httpRequestDocente.HTTPrequestBuilding(getContext());

            }
        });

        return root;

    }



}