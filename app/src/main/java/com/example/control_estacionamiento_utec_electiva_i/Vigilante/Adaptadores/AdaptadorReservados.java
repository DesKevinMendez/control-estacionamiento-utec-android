package com.example.control_estacionamiento_utec_electiva_i.Vigilante.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.control_estacionamiento_utec_electiva_i.R;

import java.util.ArrayList;

public class AdaptadorReservados extends BaseAdapter {
    private static LayoutInflater inflater = null;

    Context context;
    ArrayList nombre;
    ArrayList apellido;
    ArrayList placa;
    ArrayList edificio;
    ArrayList horaEntrada;
    ArrayList horaSalida;
    // ArrayList img;

    public AdaptadorReservados(Context context, ArrayList nombre, ArrayList apellido, ArrayList placa, ArrayList edificio,
                               ArrayList horaEntrada, ArrayList horaSalida) {
        this.nombre = nombre;
        this.apellido=apellido;
        this.placa=placa;
        this.edificio=edificio;
        this.horaEntrada=horaEntrada;
        this.horaSalida=horaSalida;
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.edificios_reservas, null);

        TextView tvNombres = vista.findViewById(R.id.tvNombres);
        TextView tvPlaca = vista.findViewById(R.id.tvPlaca) ;
        TextView tvEdificio = vista.findViewById(R.id.tvEdificio);
        TextView tvHoraEntrada = vista.findViewById(R.id.tvHoraEntrada);
        TextView tvHoraSalida = vista.findViewById(R.id.tvHoraSalida);
        // ImageView imgEdificio = vista.findViewById(R.id.imgEdificio);

        tvNombres.setText("Nombre: "+nombre.get(i).toString()+" "+apellido.get(i).toString());
        tvPlaca.setText("Numero de placa: "+placa.get(i).toString());
        tvEdificio.setText("Parqueo Asignado: "+edificio.get(i).toString());
        tvHoraEntrada.setText("Hora de entrada: "+horaEntrada.get(i).toString());
        tvHoraSalida.setText("Hora de salida: "+horaSalida.get(i).toString());

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
