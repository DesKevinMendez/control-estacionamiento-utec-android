package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.control_estacionamiento_utec_electiva_i.R;

public class BuildingAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    String[][] building;

    public BuildingAdapter(Context contexto, String[][] building) {
        this.contexto = contexto;
        this.building = building;

        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.admin_building_list, null);

        TextView tvBuilding = vista.findViewById(R.id.tvBuildingName);
        TextView InfoBuilding = vista.findViewById(R.id.tvInfoBuilding);

        tvBuilding.setText(building[i][0]);
        InfoBuilding.setText("Parqueos usado " + building[i][1] + " de "+ building[i][2]);

        return vista;
    }

    @Override
    public int getCount() {
        return building.length;
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
