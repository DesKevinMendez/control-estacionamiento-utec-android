package com.example.control_estacionamiento_utec_electiva_i.Admin;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedTeacher;
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


import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivityAdmin extends AppCompatActivity implements
        InicioAdmin.OnFragmentInteractionListener,
        ProfileUser.OnFragmentInteractionListener,
        ReserveEvents.OnFragmentInteractionListener,
        SelectedBuilding.OnFragmentInteractionListener,
        SelectedSchedule.OnFragmentInteractionListener,
        SelectedTeacher.OnFragmentInteractionListener,
        AssignWatchman.OnFragmentInteractionListener,
        RecerveParking.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener{


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegation_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Inicio");

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home_admin:

                changeFragments(new InicioAdmin(), 0);

                break;
            case R.id.nav_profile_admin:

                changeFragments(new ProfileUser(), 1);

                break;

            case R.id.nav_assign_parking:

                changeFragments(new AssignParking(), 2);

                break;
            case R.id.nav_reserve_events:

                changeFragments(new ReserveEvents(), 3);

                break;
            case R.id.nav_reserve_parking:

                changeFragments(new RecerveParking(), 4);

                break;
            case R.id.nav_assing_watchman:

                changeFragments(new AssignWatchman(), 5);

                break;

            default:
                throw new IllegalArgumentException("menu option not implemented!!");
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.home:

                changeFragments(new InicioAdmin(), 0);

                return true;
            case R.id.seeProfile:

                changeFragments(new ProfileUser(), 1);

                return true;

            case R.id.assignParking:

                changeFragments(new AssignParking(), 2);

                return true;

            case R.id.reserveParking:

                changeFragments(new RecerveParking(), 3);

                return true;

            case R.id.assingWatchMan:

                changeFragments(new AssignWatchman(), 4);

                return  true;

            case R.id.reserveEvents:

                changeFragments(new ReserveEvents(), 5);

                return  true;

            case R.id.logout:
                // Establece la sesion de usuario con falso, y limpia la data del usuario
                user.setLoggedUser(false);
                
                user.setDataUser(0, null, null, null, null,
                        null, 0, 0, null);
                        
                Intent login = new Intent(getApplicationContext(), Login.class);
                startActivity(login);
                finish();
                
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }

    }

    public void changeFragments(Fragment fragment, int indexItemSelected) {

        Resources r = getResources();
        ArrayList<String> listado = new ArrayList<>(Arrays.asList(r.getStringArray(R.array.itemMenu)));
        getSupportActionBar().setTitle(listado.get(indexItemSelected));
        navigationView.getMenu().getItem(indexItemSelected).setChecked(true);
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null).replace(R.id.nav_host_fragment, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
