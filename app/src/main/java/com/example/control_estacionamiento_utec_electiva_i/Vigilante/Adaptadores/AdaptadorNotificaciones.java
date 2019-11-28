package com.example.control_estacionamiento_utec_electiva_i.Vigilante.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos.DatosVigilante;

import java.util.ArrayList;

public class AdaptadorNotificaciones extends BaseAdapter {
    private static LayoutInflater inflater = null;
    ArrayList nombre;
    ArrayList apellido;
    ArrayList placa;
    ArrayList comentario;

    public AdaptadorNotificaciones(Context context, ArrayList nombre, ArrayList apellido, ArrayList placa, ArrayList comentario){
        this.nombre=nombre;
        this.apellido=apellido;
        this.placa=placa;
        this.comentario=comentario;

        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.notificaciones, null);

        TextView tvNombres = vista.findViewById(R.id.tvNombres);
        TextView tvNumPlaca = vista.findViewById(R.id.tvNumPlaca) ;
        TextView tvComentario = vista.findViewById(R.id.tvComentario);


        tvNombres.setText(nombre.get(i).toString()+ " "+apellido.get(i).toString());
        tvNumPlaca.setText(placa.get(i).toString());
        tvComentario.setText(comentario.get(i).toString());


        return vista;
    }


    @Override
    public int getCount() {
        return placa.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) { return 0; }


}
