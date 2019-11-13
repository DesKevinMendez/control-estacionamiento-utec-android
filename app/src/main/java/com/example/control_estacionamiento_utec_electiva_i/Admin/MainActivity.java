package com.example.control_estacionamiento_utec_electiva_i.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedTeacher;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements
        InicioAdmin.OnFragmentInteractionListener,
        ProfileUser.OnFragmentInteractionListener,
        AssignParking.OnFragmentInteractionListener,
        ReserveParking.OnFragmentInteractionListener,
        SelectedBuilding.OnFragmentInteractionListener,
        SelectedSchedule.OnFragmentInteractionListener,
        SelectedTeacher.OnFragmentInteractionListener,
        AssignWatchman.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pantalla de inicio");

        InicioAdmin inicioAdmin = new InicioAdmin();
        getSupportFragmentManager().beginTransaction().add(R.id.contentLayaout, inicioAdmin).commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
                setTitle("Pantalla de inicio");

                frag = new InicioAdmin();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.contentLayaout, frag).commit();

                return true;
            case R.id.seeProfile:
                setTitle("Perfil de usuario");
                frag = new ProfileUser();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.contentLayaout, frag).commit();

                return true;

            case R.id.assignParking:
                setTitle("Asignar estacionamientos");
                frag = new AssignParking();

                getSupportFragmentManager().beginTransaction().replace(R.id.contentLayaout, frag).commit();
                return true;

            case R.id.reserveParking:
                setTitle("Reservar estacionamientos");
                frag = new ReserveParking();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.contentLayaout, frag).commit();
                return true;

            case R.id.assingWatchMan:
                setTitle("Asignar vigilante");
                frag = new AssignWatchman();

                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.contentLayaout, frag).commit();
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
    public void onFragmentInteraction(Uri uri) {

    }
}
