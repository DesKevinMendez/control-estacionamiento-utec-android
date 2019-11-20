package com.example.control_estacionamiento_utec_electiva_i.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.Admin.MainActivityAdmin;
import com.example.control_estacionamiento_utec_electiva_i.Estudiante.EstudiantesDrawer;
import com.example.control_estacionamiento_utec_electiva_i.HTTP.HttpLoginRequest;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.vigilanteNavigationDrawer;
import com.example.control_estacionamiento_utec_electiva_i.docentes.DocenteHome;

public class Login extends AppCompatActivity {

    Button btnLogin;
    EditText tvCorreoCarnetLG, tvPasswordLG;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        
        btnLogin = findViewById(R.id.btnLogin);
        tvCorreoCarnetLG = findViewById(R.id.tvCorreoCarnetLG);
        tvPasswordLG = findViewById(R.id.tvPasswordLG);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo_carnet = tvCorreoCarnetLG.getText().toString().trim();
                String pass = tvPasswordLG.getText().toString().trim();

                if (correo_carnet.isEmpty()){
                    tvCorreoCarnetLG.setError("Campo requerido");
                    tvCorreoCarnetLG.requestFocus();
                } else if (pass.isEmpty()){
                    tvPasswordLG.setError("Contraseña requerida");
                    tvPasswordLG.requestFocus();

                } else {
                    HttpLoginRequest httpLoginRequest = new HttpLoginRequest();
                    httpLoginRequest.LoginRequest(Login.this, correo_carnet, pass);

                    render();

                }

            }
        });

    }

    public void render(){
        if (user.getLoggedUser()){

            // Administrador 1
            if (user.getName_role().equals("Administrador")){
                Intent i = new Intent(getApplicationContext(), MainActivityAdmin.class);
                startActivity(i);
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();

                // Docente
            } else if (user.getName_role().equals("Docente")) {
                Intent i = new Intent(getApplicationContext(), DocenteHome.class);
                i.putExtra("usuario", user.getEmail());
                startActivity(i);
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();

                // Alumno
            }else if (user.getName_role().equals("Alumno")){


                Intent i = new Intent(getApplicationContext(), EstudiantesDrawer.class);
                startActivity(i);
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();

                // Vigilante
            } else if (user.getName_role().equals("Vigilante")){

                Intent i = new Intent(getApplicationContext(),  vigilanteNavigationDrawer.class);
                startActivity(i);
                setResult(Activity.RESULT_OK);

            }
        } else {
            Toast.makeText(Login.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
