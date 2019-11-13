package com.example.control_estacionamiento_utec_electiva_i.Vigilante;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.ui.login.LoginActivity;

import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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
        PerfilVigilante.OnFragmentInteractionListener
{


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigilante_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.inicioVigilante, R.id.perfilVigilante, R.id.comentariosVigilante,
                R.id.disponiblesVigilante, R.id.reservadosVigilante, R.id.notificacionesVigilante, R.id.cerrar)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }



    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Fragment frag = null;
        switch (id){
            case R.id.inicioVigilante:
                frag = new InicioVigilante();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, frag).commit();
                return true;

            case R.id.comentariosVigilante:
                frag = new ComentariosVigilante();

                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, frag).commit();
                return true;

            case R.id.notificacionesVigilante:
                frag = new NotificacionesVigilante();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, frag).commit();
                return true;

            case R.id.disponiblesVigilante:
                frag = new DisponiblesVigilante();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, frag).commit();
                return  true;

            case R.id.reservadosVigilante:
                frag = new ReservadosVigilante();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, frag).commit();
                return  true;

            case R.id.perfilVigilante:
                frag = new PerfilVigilante();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, frag).commit();
                return  true;

            case R.id.cerrar:
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
                finish();
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }

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
