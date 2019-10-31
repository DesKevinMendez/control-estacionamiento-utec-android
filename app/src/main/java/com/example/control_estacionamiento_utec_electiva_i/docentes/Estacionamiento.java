package com.example.control_estacionamiento_utec_electiva_i.docentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.ui.login.LoginActivity;

public class Estacionamiento extends AppCompatActivity {

    ImageButton btnSB, btnTF, btnJL, btnGM;
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estacionamiento);

        btnSB = findViewById(R.id.imgSB);
        btnTF = findViewById(R.id.imgTF);
        btnJL = findViewById(R.id.imgJL);
        btnGM = findViewById(R.id.imgGM);

        regresar = findViewById(R.id.btnRegresar);

        this.setTitle(R.string.estacionamiento);

        btnSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Estacionamiento.this, "Disponible", Toast.LENGTH_SHORT).show();
            }
        });

        btnTF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Estacionamiento.this, "Disponible", Toast.LENGTH_SHORT).show();
            }
        });

        btnJL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Estacionamiento.this, "Disponible", Toast.LENGTH_SHORT).show();
            }
        });

        btnGM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Estacionamiento.this, "Disponible", Toast.LENGTH_SHORT).show();
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
