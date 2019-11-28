package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.control_estacionamiento_utec_electiva_i.R;

import java.util.ArrayList;

public class VigilanteAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    ArrayList vigilante;
    ArrayList edificioAsignado;
    ArrayList AliasEdificioAsignado;

    public VigilanteAdapter(Context contexto, ArrayList vigilante, ArrayList edificioAsignado, ArrayList AliasEdificioAsignado) {
        this.contexto = contexto;
        this.vigilante = vigilante;
        this.edificioAsignado = edificioAsignado;
        this.AliasEdificioAsignado = AliasEdificioAsignado;

        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.admin_watchman_list, null);

        TextView tvNameWatchman = vista.findViewById(R.id.tvWatchManName);
        TextView tvBuildingAssign = vista.findViewById(R.id.tvBuildingAssign);
        TextView tvAliasBuildingAssing = vista.findViewById(R.id.tvAliasBuildingAssing);

        tvNameWatchman.setText(vigilante.get(i).toString());
        tvBuildingAssign.setText(edificioAsignado.get(i).toString());
        tvAliasBuildingAssing.setText(AliasEdificioAsignado.get(i).toString());

        return vista;
    }
    @Override
    public int getCount() {
        return vigilante.size();
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
