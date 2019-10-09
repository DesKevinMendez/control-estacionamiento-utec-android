package com.example.control_estacionamiento_utec_electiva_i.docentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.R;

public class PantallaInicio extends AppCompatActivity {

    Button btnEstacionamiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);

        // Change the Title
        this.setTitle(R.string.pantalla_inicio);

        btnEstacionamiento = findViewById(R.id.btnStation);

        btnEstacionamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Estacionamiento.class);
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
            Toast.makeText(this, "opcion 1", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuPerfil){
            Toast.makeText(this, "opcion 2", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuCerrar){
            Toast.makeText(this, "opcion 3", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
