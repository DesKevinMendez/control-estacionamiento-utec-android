package com.example.control_estacionamiento_utec_electiva_i.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pantalla de inicio");

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

        switch (id){
            case R.id.home:

                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            case R.id.seeProfile:
                Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show();

            case R.id.assignParking:
                Toast.makeText(this, "Asignar parqueo", Toast.LENGTH_SHORT).show();

            case R.id.reserveParking:
                Toast.makeText(this, "Reservar parqueo", Toast.LENGTH_SHORT).show();
            case R.id.logout:
                Toast.makeText(this, "Cerrar sesion", Toast.LENGTH_SHORT).show();

            default:

                    return super.onOptionsItemSelected(item);
        }

    }
}
