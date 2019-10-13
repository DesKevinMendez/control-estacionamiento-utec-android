package com.example.control_estacionamiento_utec_electiva_i.Estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.ui.login.LoginActivity;

public class inicioEstudiante extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_estudiante);
        setTitle("Pantalla de inicio");
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
            finish();
        } else if(id == R.id.idPerfil){
            Intent perfil = new Intent(getApplicationContext(), perfilEstudiante.class);
            startActivity(perfil);
            finish();
        } else if(id == R.id.idFin){
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
