package com.example.control_estacionamiento_utec_electiva_i.Vigilante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.ui.login.LoginActivity;

public class perfilVigilante extends AppCompatActivity {
    Button btnCancelar, btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_vigilante);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        setTitle("Perfil de usuario");

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio = new Intent(getApplicationContext(), inicioVigilante.class);
                startActivity(inicio);
                finish();
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio = new Intent(getApplicationContext(), inicioVigilante.class);
                startActivity(inicio);
                finish();
            }
        });


    }




    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_vigilante,menu);
        return true;


    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.idInicio){
            Intent inicio = new Intent(getApplicationContext(), inicioVigilante.class);
            startActivity(inicio);
        } else if(id == R.id.idPerfil){
            Intent perfil = new Intent(getApplicationContext(), perfilVigilante.class);
            startActivity(perfil);
        } else if(id == R.id.idMarcar){
            Intent marcar = new Intent(getApplicationContext(), marcarVigilante.class);
            startActivity(marcar);
        } else if(id == R.id.idComentarios){
            Intent comentarios = new Intent(getApplicationContext(), comentarioVigilante.class);
            startActivity(comentarios);
        } else if(id == R.id.idDisponibles){
            Intent disponibles = new Intent(getApplicationContext(), disponiblesVigilante.class);
            startActivity(disponibles);
        } else if(id == R.id.idReservados){
            Intent reservados = new Intent(getApplicationContext(), reservadosVigilante.class);
            startActivity(reservados);
        } else if(id == R.id.idCerrar){
            Intent cerrar = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(cerrar);
            finish();
        }  else if(id == R.id.idNotificaciones){
            Intent notificacion = new Intent(getApplicationContext(), notificacionesVigilante.class);
            startActivity(notificacion);
        }
        return super.onOptionsItemSelected(item);
    }



}
