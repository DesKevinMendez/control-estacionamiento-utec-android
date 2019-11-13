package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.control_estacionamiento_utec_electiva_i.R;

import java.util.ArrayList;

public class TeacherAdapter  extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    ArrayList teacher;
    ArrayList carnets;

    public TeacherAdapter(Context contexto, ArrayList teacher, ArrayList carnets) {
        this.contexto = contexto;
        this.teacher = teacher;
        this.carnets = carnets;

        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.admin_teacher_list, null);

        TextView tvTeacher = vista.findViewById(R.id.tvBuildingName);
        TextView tvCarnetTeacher = vista.findViewById(R.id.tvInfoText);

        tvTeacher.setText(teacher.get(i).toString());
        tvCarnetTeacher.setText(carnets.get(i).toString());

        return vista;
    }
    @Override
    public int getCount() {
        return teacher.size();
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
