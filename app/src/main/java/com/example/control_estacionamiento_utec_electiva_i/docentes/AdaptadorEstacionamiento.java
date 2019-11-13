package com.example.control_estacionamiento_utec_electiva_i.docentes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.control_estacionamiento_utec_electiva_i.R;

import java.util.ArrayList;
import java.util.List;

class AdaptadorEstacionamiento extends ArrayAdapter<Estacionamientos> {

    public AdaptadorEstacionamiento(Context context, int textViewResourceId,
                         List<Estacionamientos> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewOptimize(position, convertView, parent);
    }

    public View getViewOptimize(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.estacionamientos, null);
            viewHolder = new ViewHolder();
            viewHolder.image = convertView.findViewById(R.id.imageViewEdi);
            viewHolder.name = convertView.findViewById(R.id.textViewE);
            viewHolder.estado = convertView.findViewById(R.id.textViewS);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Estacionamientos estacionamientos = getItem(position);
        viewHolder.name.setText(estacionamientos.getNombreEdificio());
        viewHolder.estado.setText(estacionamientos.getEstadoEdificio());
        if (estacionamientos.getEstadoEdificio().equals("Disponible"))
            viewHolder.image.setImageResource(R.drawable.buildingg);
        else
            viewHolder.image.setImageResource(R.drawable.buildingr);
        return convertView;
    }

    private class ViewHolder {
        public TextView name;
        public TextView estado;
        public ImageView image;
    }
}
