package com.example.control_estacionamiento_utec_electiva_i.docentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.ui.login.LoginActivity;

public class PantallaInicio extends AppCompatActivity {

    Button btnEstacionamiento;
    TextView tvState, tvCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);

        // Change the Title
        this.setTitle(R.string.pantalla_inicio);

        btnEstacionamiento = findViewById(R.id.btnStation);
        tvState = findViewById(R.id.tvState);
        tvCode = findViewById(R.id.tvCode);

        Bundle datos = getIntent().getExtras();

        String usuario = datos.getString("usuario");
        String estado = datos.getString("estado");
        String edificio = datos.getString("edificio");

        Toast.makeText(this, usuario, Toast.LENGTH_SHORT).show();

        if (estado == null && edificio == null){
            tvState.setTextColor(getColor(R.color.red));
        } else if (estado.equals("validado")){
            tvCode.setText(edificio + " (temporal)");
            tvState.setTextColor(getColor(R.color.green));
            tvState.setText("Disponible");
        }

        btnEstacionamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Estacionamiento.class);
                i.putExtra("estado", "no disponible");
                startActivity(i);

                finish();
            }
        });

    }



    // Method for show and hide the menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    // Method to assign the corresponding functions to the options of menu
    public  boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menuInicio){
            Intent i = new Intent(getApplicationContext(), PantallaInicio.class);
            startActivity(i);
            finish();
        } else if (id == R.id.menuPerfil){
            Intent i = new Intent(getApplicationContext(), PerfilUsuario.class);
            startActivity(i);
            finish();
        } else if (id == R.id.menuCerrar){
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
