package com.example.control_estacionamiento_utec_electiva_i.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.control_estacionamiento_utec_electiva_i.Admin.MainActivityAdmin;
import com.example.control_estacionamiento_utec_electiva_i.Estudiante.EstudiantesDrawer;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.vigilanteNavigationDrawer;
import com.example.control_estacionamiento_utec_electiva_i.docentes.DocenteHome;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals.BASE_URL;

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
                    LoginRequest(correo_carnet, pass);
                }

            }
        });

    }

    ProgressDialog progressDialog;
    public void LoginRequest(String mail, String pass) {
        progressDialog = new ProgressDialog(this, R.style.AlertDialogStyle);
        progressDialog.setMessage("Iniciando sesión...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        progressDialog.show();

        String url = BASE_URL+"login";
        Map<String, String> params = new HashMap();
        params.put("email", mail);
        params.put("password", pass);
        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(this);

        Log.i("VOLLEY", url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONObject mJsonObject = response.getJSONObject("data");
                    JSONObject mJsonRole = mJsonObject.getJSONObject("rol");
                    user.setDataUser(mJsonObject.getInt("id"),
                            mJsonObject.getString("email"),
                            mJsonObject.getString("num_placa"),
                            mJsonObject.getString("nombres"),
                            mJsonObject.getString("apellidos"),
                            mJsonObject.getString("api_token"),
                            mJsonObject.getInt("estado"),
                            mJsonObject.getInt("rol_id"),
                            mJsonRole.getString("nombre"));


                    JSONObject mJsonObjectReserva = mJsonObject.getJSONObject("reserva");
                    JSONObject mJsonObjectEdificioReserva = mJsonObjectReserva.getJSONObject("edificio");

                    user.setDataParqueoAsignado(mJsonObjectEdificioReserva.getString("nombre"),
                            mJsonObjectEdificioReserva.getString("alias"),
                            mJsonObjectEdificioReserva.getInt("num_reservados"));

                    user.setLoggedUser(true);
                    render();

                    Log.i("VOLLEY", mJsonRole.getString("nombre"));

                } catch (JSONException e){

                    Log.e("VOLLEY","Error de parcing en Login - method: LoginRequest "+ e.toString());
                    e.printStackTrace();

                }

                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

            }
        });

        queue.add(request);

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
