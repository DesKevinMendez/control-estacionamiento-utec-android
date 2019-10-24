package com.example.control_estacionamiento_utec_electiva_i.Admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedTeacher;
import com.example.control_estacionamiento_utec_electiva_i.R;

import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.control_estacionamiento_utec_electiva_i.ui.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;


public class MainActivityAdmin extends AppCompatActivity implements
        InicioAdmin.OnFragmentInteractionListener,
        ProfileUser.OnFragmentInteractionListener,
        AssignParking.OnFragmentInteractionListener,
        ReserveEvents.OnFragmentInteractionListener,
        SelectedBuilding.OnFragmentInteractionListener,
        SelectedSchedule.OnFragmentInteractionListener,
        SelectedTeacher.OnFragmentInteractionListener,
        AssignWatchman.OnFragmentInteractionListener,
        RecerveParking.OnFragmentInteractionListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegation_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_admin, R.id.nav_profile_admin, R.id.nav_assign_parking,
                R.id.nav_reserve_events, R.id.nav_assing_watchman, R.id.nav_reserve_parking)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Fragment frag = null;
        switch (id){
            case R.id.home:
                frag = new InicioAdmin();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, frag).commit();

                return true;
            case R.id.seeProfile:
                frag = new ProfileUser();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, frag).commit();

                return true;

            case R.id.assignParking:
                frag = new AssignParking();

                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, frag).commit();
                return true;

            case R.id.reserveParking:
                frag = new RecerveParking();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, frag).commit();
                return true;

            case R.id.assingWatchMan:
                frag = new AssignWatchman();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, frag).commit();
                return  true;

            case R.id.reserveEvents:

                frag = new ReserveEvents();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, frag).commit();
                return  true;

            case R.id.logout:
                Toast.makeText(this, "Cerrar sesion", Toast.LENGTH_SHORT).show();

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
