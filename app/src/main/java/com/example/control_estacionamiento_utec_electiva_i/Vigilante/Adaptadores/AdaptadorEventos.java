package com.example.control_estacionamiento_utec_electiva_i.Vigilante.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.control_estacionamiento_utec_electiva_i.R;

import java.util.ArrayList;

public class AdaptadorEventos extends BaseAdapter {
    private static LayoutInflater inflater = null;

    Context context;
    ArrayList edificio;
    ArrayList cantidad;
    ArrayList entrada;
    ArrayList salida;
    ArrayList fecha;
    ArrayList comentario;

    public AdaptadorEventos(Context context, ArrayList edificio, ArrayList cantidad, ArrayList entrada, ArrayList salida, ArrayList fecha, ArrayList comentario) {
        this.context = context;
        this.edificio = edificio;
        this.cantidad = cantidad;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
        this.comentario = comentario;

        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.eventos, null);

        TextView tvEdificio = vista.findViewById(R.id.tvEdificio);
        TextView tvCantidad = vista.findViewById(R.id.tvCantidad);
        TextView tvEntrada = vista.findViewById(R.id.tvEntrada);
        TextView tvSalida = vista.findViewById(R.id.tvSalida);
        TextView tvFecha = vista.findViewById(R.id.tvFecha);
        TextView tvComentario = vista.findViewById(R.id.tvComentario);

        tvEdificio.setText("Edificio Asignado: "+edificio.get(i).toString());
        tvCantidad.setText("Estacionamientos asignados: "+cantidad.get(i).toString());
        tvEntrada.setText("Hora de entrada: "+entrada.get(i).toString());
        tvSalida.setText("hora de salida: "+salida.get(i).toString());
        tvFecha.setText("Fecha: "+fecha.get(i).toString());
        tvComentario.setText("Comentario: "+comentario.get(i).toString());

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
