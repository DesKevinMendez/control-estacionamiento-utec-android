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

public class comentarioVigilante extends AppCompatActivity {
    Button btnRegresar, btnDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario_vigilante);
        setTitle("Comentarios");

        btnDetalle = findViewById(R.id.btnDetalle);
        btnRegresar = findViewById(R.id.btnRegresar);


        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detalle = new Intent(getApplicationContext(), detallecVigilante.class);
                startActivity(detalle);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio = new Intent(getApplicationContext(), inicioVigilante.class);
                startActivity(inicio);
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
