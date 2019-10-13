package com.example.control_estacionamiento_utec_electiva_i.Estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.ui.login.LoginActivity;

public class perfilEstudiante extends AppCompatActivity {
    Button btnCancelar, btnConfirmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_estudiante);
        setTitle("Perfil de usuario");

        btnCancelar = findViewById(R.id.btnCancelar);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio = new Intent(getApplicationContext(), inicioEstudiante.class);
                startActivity(inicio);
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cerrar = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(cerrar);
            }
        });
    }




    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.idInicio){
            Intent inicio = new Intent(getApplicationContext(), inicioEstudiante.class);
            startActivity(inicio);

        } else if(id == R.id.idPerfil){
            Intent perfil = new Intent(getApplicationContext(), perfilEstudiante.class);
            startActivity(perfil);

        } else if(id == R.id.idFin){
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        }
        return super.onOptionsItemSelected(item);
    }

}
