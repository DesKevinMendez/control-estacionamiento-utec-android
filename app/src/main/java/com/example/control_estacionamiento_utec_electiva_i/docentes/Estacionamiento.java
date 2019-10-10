package com.example.control_estacionamiento_utec_electiva_i.docentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.ui.login.LoginActivity;

public class Estacionamiento extends AppCompatActivity {

    ImageButton btnSB, btnTF, btnJL, btnGM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estacionamiento);

        btnSB = findViewById(R.id.imgSB);
        btnTF = findViewById(R.id.imgTF);
        btnJL = findViewById(R.id.imgJL);
        btnGM = findViewById(R.id.imgGM);

        this.setTitle(R.string.estacionamiento);

        btnSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PopActivity.class);
                startActivity(i);
            }
        });

        btnTF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PopActivity.class);
                startActivity(i);
            }
        });

        btnJL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PopActivity.class);
                startActivity(i);
            }
        });

        btnGM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PopActivity.class);
                startActivity(i);
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
