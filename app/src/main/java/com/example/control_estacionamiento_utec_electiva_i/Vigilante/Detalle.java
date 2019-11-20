package com.example.control_estacionamiento_utec_electiva_i.Vigilante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.control_estacionamiento_utec_electiva_i.R;

import com.google.android.material.navigation.NavigationView;

public class Detalle extends AppCompatActivity implements
        DetallecVigilante.OnFragmentInteractionListener,
        ComentariosVigilante.OnFragmentInteractionListener
{

    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detallec_vigilante);
        setTitle("Ampliar Comentario");

        Button btnDenegar2 = findViewById(R.id.btnDenegar2);
        Button btnAceptar2 = findViewById(R.id.btnAceptar2);
        final EditText edtComentario = findViewById(R.id.edtComentario);

        btnDenegar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inicio = new Intent(getApplicationContext(), vigilanteNavigationDrawer.class);
                startActivity(inicio);
            }
        });

        btnAceptar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comentario = edtComentario.getText().toString().trim();
                if (comentario.equals("")){
                    edtComentario.setError("Se debe agregar un comentario");
                } else {
                    Intent inicio = new Intent(getApplicationContext(), vigilanteNavigationDrawer.class);
                    startActivity(inicio);
                }

            }
        });
    }

    //Muestra el menu de los tres puntos
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_vigilante_navigation_drawer_drawer, menu);
        return true;
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void onFragmentInteraction(Uri uri) {

    }
}
