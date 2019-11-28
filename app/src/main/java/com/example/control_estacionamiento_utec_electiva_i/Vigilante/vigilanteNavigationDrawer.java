package com.example.control_estacionamiento_utec_electiva_i.Vigilante;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.control_estacionamiento_utec_electiva_i.Login.Login;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;


import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos.PeticionesVigilante;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;


public class vigilanteNavigationDrawer extends AppCompatActivity implements
        InicioVigilante.OnFragmentInteractionListener,
        ComentariosVigilante.OnFragmentInteractionListener,
        DetallecVigilante.OnFragmentInteractionListener,
        NotificacionesVigilante.OnFragmentInteractionListener,
        DisponiblesVigilante.OnFragmentInteractionListener,
        ReservadosVigilante.OnFragmentInteractionListener,
        PerfilVigilante.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener
{

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    NavigationView navigationView;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigilante_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Inicio");

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    final PeticionesVigilante peticion = new PeticionesVigilante();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
// Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Fragment frag = null;
        switch (id){
            case R.id.inicioVigilante:
                frag = new InicioVigilante();
                getSupportActionBar().setTitle("Inicio");
                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, frag).commit();
                break;

            case R.id.comentariosVigilante:
                frag = new ComentariosVigilante();
                getSupportActionBar().setTitle("Comentarios");
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, frag).commit();
                break;


            case R.id.notificacionesVigilante:
                Log.i("TEST", "Notificaciones");
                getSupportActionBar().setTitle("Notificaciones");
                PeticionesVigilante peticion3 = new PeticionesVigilante();
                peticion3.ObtenerHistorial(vigilanteNavigationDrawer.this);
                break;

            case R.id.disponiblesVigilante:
                Log.i("TEST", "Estacionamientos disponibles");
                getSupportActionBar().setTitle("Estacionamiento Disponibles");
                //PeticionesVigilante peticion = new PeticionesVigilante();
                peticion.ObtenerEdificios(vigilanteNavigationDrawer.this);

                break;

            case R.id.reservadosVigilante:
                Log.i("TEST", "Estacionamientos reservados");
                getSupportActionBar().setTitle("Estacionamiento Reservados");
                PeticionesVigilante peticion2 = new PeticionesVigilante();
                peticion2.ObtenerReservados(vigilanteNavigationDrawer.this);
                break;

            case R.id.perfilVigilante:
                frag = new PerfilVigilante();
                getSupportActionBar().setTitle("Perfil");
                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, frag).commit();
                break;

            case R.id.cerrar:
                // Establece la sesion de usuario con falso, y limpia la data del usuario
                user.setLoggedUser(false);
                user.setDataUser(0, null, null, null, null,
                        null, 0, 0, null);

                Intent login = new Intent(getApplicationContext(), Login.class);
                startActivity(login);
                finish();
                break;

            default:
                throw new IllegalArgumentException("menu option not implemented!!");
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;


    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        drawer.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vigilante_navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void onFragmentInteraction(Uri uri) {

    }
}
