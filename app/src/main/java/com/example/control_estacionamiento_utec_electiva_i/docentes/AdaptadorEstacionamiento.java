package com.example.control_estacionamiento_utec_electiva_i.docentes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.control_estacionamiento_utec_electiva_i.R;

import java.util.ArrayList;
import java.util.List;

class AdaptadorEstacionamiento extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context context;
    ArrayList edificios;
    ArrayList disponibles;


    public AdaptadorEstacionamiento(Context context, ArrayList edificios,
                                    ArrayList disponibles) {
        this.context = context;
        this.edificios = edificios;
        this.disponibles = disponibles;

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.estacionamientos, null);

        TextView tvEdificio = vista.findViewById(R.id.textViewE);
        TextView tvEstado = vista.findViewById(R.id.textViewS);
        ImageView imgEdificio = vista.findViewById(R.id.imageViewEdi);

        tvEdificio.setText(edificios.get(i).toString());
        if (!disponibles.get(i).toString().equals("0"))
            tvEstado.setText("Disponibles: " + disponibles.get(i).toString());
        else
            tvEstado.setText("No Disponibles");
        String valor = Estacionamientos.getEdificioDisponible(i);
        Log.i("TOTAL", valor);
        if (!valor.equals("0")){

            imgEdificio.setImageResource(R.drawable.buildingg);

        } else {

            imgEdificio.setImageResource(R.drawable.buildingr);
        }

        return vista;
    }


    @Override
    public int getCount() {
        return edificios.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) { return 0; }


}