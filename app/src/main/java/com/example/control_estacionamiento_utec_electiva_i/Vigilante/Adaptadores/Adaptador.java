package com.example.control_estacionamiento_utec_electiva_i.Vigilante.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos.DatosVigilante;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private static LayoutInflater inflater = null;

    Context context;
    ArrayList edificio;
    ArrayList estado;
   // ArrayList img;

    public Adaptador(Context context, ArrayList edificio, ArrayList estado) {
        this.edificio = edificio;
        this.estado = estado;
        //this.img = img;
        this.context = context;

        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.edificios_vigilante, null);

        TextView tvEdificio = vista.findViewById(R.id.tvEdificio);
        TextView tvEstado = vista.findViewById(R.id.tvEstado) ;
        ImageView imgEdificio = vista.findViewById(R.id.imgEdificio);

        tvEdificio.setText(edificio.get(i).toString());
        tvEstado.setText(estado.get(i).toString()+ "  estacionamientos disponibles");

        if (!DatosVigilante.getEdificioDisponible(i).equals("0")){
            imgEdificio.setImageResource(R.drawable.buildingg);
        }else {
            imgEdificio.setImageResource(R.drawable.buildingr);
        }



        return vista;
    }


    @Override
    public int getCount() {
        return edificio.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) { return 0; }


}
