package com.example.control_estacionamiento_utec_electiva_i.docentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.docentes.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Estacionamiento extends AppCompatActivity {

    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estacionamiento);

        setTitle("Estacionamientos");

        btnRegresar = findViewById(R.id.btnRegresar);

        ListView listView = findViewById(R.id.listviewEd);
        List<Estacionamientos> list = new LinkedList<>();
        list.add(new Estacionamientos("Benito Juarez","No disponible"));
        list.add(new Estacionamientos("Gabriela Mistral","Disponible"));
        list.add(new Estacionamientos("Giuseppe Garibaldi","No disponible"));
        list.add(new Estacionamientos("Garcia Lorca","Disponible"));
        list.add(new Estacionamientos("Villa Fermina","No disponible"));
        list.add(new Estacionamientos("Simon Bolivar","Disponible"));
        AdaptadorEstacionamiento adapter = new AdaptadorEstacionamiento(this, R.layout.estacionamientos, list);
        listView.setAdapter(adapter);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}


