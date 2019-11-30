package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.control_estacionamiento_utec_electiva_i.R;

import java.util.ArrayList;

public class StudentsAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    ArrayList nombres;
    ArrayList placas;
    ArrayList carnets;
    ArrayList edificios;

    //String nombre, String placa, String carnet, String edificio
    public StudentsAdapter(Context contexto, ArrayList nombre, ArrayList placa,
                           ArrayList carnet, ArrayList edificio) {
        this.contexto = contexto;
        this.nombres = nombre;
        this.placas = placa;
        this.carnets = carnet;
        this.edificios = edificio;

        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.admin_students_list, null);

        TextView tvNameStudents = vista.findViewById(R.id.tvNameStudents);
        TextView tvBuildingStudents = vista.findViewById(R.id.tvBuildingStudents);
        TextView tvPlacaStudents = vista.findViewById(R.id.tvPlacaStudents);

        tvNameStudents.setText(nombres.get(i).toString());
        tvBuildingStudents.setText(edificios.get(i).toString());
        tvPlacaStudents.setText(placas.get(i).toString());

        return vista;
    }
    @Override
    public int getCount() {
        return nombres.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


}
