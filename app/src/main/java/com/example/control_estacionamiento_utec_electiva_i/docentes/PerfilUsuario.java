package com.example.control_estacionamiento_utec_electiva_i.docentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.ui.login.LoginActivity;

public class PerfilUsuario extends AppCompatActivity {

    Button btnConfirmar, btnCancelar;
    EditText edClave, edClaveConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        this.setTitle(R.string.perfil_usuario);

        btnConfirmar = findViewById(R.id.btnConfirmarPerfil);
        btnCancelar = findViewById(R.id.btnCancelarPerfil);

        edClave = findViewById(R.id.edClave);
        edClaveConfirmar = findViewById(R.id.edConfirmar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), PantallaInicio.class);
                i.putExtra("usuario", "Cancelado");
                startActivity(i);
                finish();
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String clave = edClave.getText().toString().trim();
                String confirmar = edClaveConfirmar.getText().toString().trim();

                if (clave.isEmpty()){
                    edClave.setError("Campo Requerido");
                    edClave.requestFocus();
                } else if (confirmar.isEmpty()){
                    edClaveConfirmar.setError("Campo Requerido");
                    edClaveConfirmar.requestFocus();
                } else if (!clave.equals(confirmar)){
                    edClave.setText("");
                    edClaveConfirmar.setText("");
                    Toast.makeText(PerfilUsuario.this, "Las Claves no son Iguales", Toast.LENGTH_SHORT).show();
                    edClave.requestFocus();
                } else{
                    // Inicia el popup
                    Intent i = new Intent(getApplicationContext(), PopConfirm.class);
                    startActivity(i);
                }
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
            i.putExtra("estado", "inicio");
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
