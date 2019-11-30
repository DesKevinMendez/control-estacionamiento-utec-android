package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.control_estacionamiento_utec_electiva_i.R;

import java.util.ArrayList;

public class BuildingAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    ArrayList building;
    ArrayList TotalEstacionamiento;
    ArrayList EstacionamientoDisponible;

    public BuildingAdapter(Context contexto, ArrayList building,
                           ArrayList EstacionamientoDisponible ,
                           ArrayList TotalEstacionamiento) {
        this.contexto = contexto;
        this.building = building;
        this.TotalEstacionamiento = TotalEstacionamiento;
        this.EstacionamientoDisponible = EstacionamientoDisponible;

        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.admin_building_list, null);

        TextView tvBuilding = vista.findViewById(R.id.tvBuildingName);
        TextView InfoBuilding = vista.findViewById(R.id.tvInfoBuilding);

        tvBuilding.setText(building.get(i).toString());
        InfoBuilding.setText("Parqueos usado "
                + EstacionamientoDisponible.get(i).toString() +
                " de "+ TotalEstacionamiento.get(i).toString());

        return vista;
    }

    @Override
    public int getCount() {
        return building.size();
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
