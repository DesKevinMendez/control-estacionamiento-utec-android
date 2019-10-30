package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.control_estacionamiento_utec_electiva_i.R;

public class TeacherAdapter  extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    String[][] datos;

    public TeacherAdapter(Context contexto, String[][] datos) {
        this.contexto = contexto;
        this.datos = datos;

        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.admin_teacher_list, null);

        TextView tvTeacher = vista.findViewById(R.id.tvNameTeacher);
        TextView tvCarnetTeacher = vista.findViewById(R.id.tvCarnetTeacher);

        tvTeacher.setText(datos[i][0]);
        tvCarnetTeacher.setText(datos[i][1]);

        return vista;
    }
    @Override
    public int getCount() {
        return datos.length;
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
