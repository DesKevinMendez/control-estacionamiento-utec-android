package com.example.control_estacionamiento_utec_electiva_i.docentes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.control_estacionamiento_utec_electiva_i.R;

public class Estacionamiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estacionamiento);

        this.setTitle(R.string.estacionamiento);
    }
}
