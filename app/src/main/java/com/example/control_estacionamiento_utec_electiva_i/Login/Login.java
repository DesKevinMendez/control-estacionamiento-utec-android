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
import android.widget.TextView;
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
import com.example.control_estacionamiento_utec_electiva_i.Estudiante.StudentRegisterActivity;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.vigilanteNavigationDrawer;
import com.example.control_estacionamiento_utec_electiva_i.docentes.DocenteHome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals.BASE_URL;

public class Login extends AppCompatActivity {

    Button btnLogin;
    EditText tvCorreoCarnetLG, tvPasswordLG;
    TextView studentRegister;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        
        btnLogin = findViewById(R.id.btnLogin);
        tvCorreoCarnetLG = findViewById(R.id.tvCorreoCarnetLG);
        tvPasswordLG = findViewById(R.id.tvPasswordLG);
        studentRegister = findViewById(R.id.tvLinkRegister);

        studentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(Login.this, StudentRegisterActivity.class);
                startActivity(register);
            }
        });

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
                            mJsonObject.getInt("rol_id"),
                            mJsonObject.getInt("estado"),
                            mJsonRole.getString("nombre"));

                    if (mJsonObject.getString("num_placa").length() > 4){
                        user.setNum_placa(mJsonObject.getString("num_placa"));
                    } else {
                        user.setNum_placa("No disponible");
                    }

                    if (mJsonObject.getString("carnet").length() > 4){
                        user.setCarnet(mJsonObject.getString("carnet"));
                    } else {
                        user.setCarnet("No disponible");
                    }

                    if (mJsonObject.getInt("rol_id") == 5) {
                        if (mJsonObject.getJSONArray("edificios").length() != 0){

                            JSONArray edificios = mJsonObject.getJSONArray("edificios");

                            for (int j = 0; j < edificios.length(); j++){
                                JSONObject data = edificios.getJSONObject(j);
                                User.setDataEdificioAsignadoVigilante(data.getString("nombre"),
                                        data.getString("alias"));
                            }

                        } else {
                            User.setDataEdificioAsignadoVigilante("No asignado",
                                    "No");
                        }

                    } else {
                        if (mJsonObject.getJSONArray("reservas").length() != 0){
                            JSONArray reservasArray = mJsonObject.getJSONArray("reservas");

                            for (int r=0; r<reservasArray.length(); r++){
                                JSONObject data = reservasArray.getJSONObject(r);
                                JSONArray edi = data.getJSONArray("edificios");
                                for (int e=0; e<edi.length(); e++){
                                    JSONObject infoFinal = edi.getJSONObject(e);
                                    user.setDataParqueoAsignado(infoFinal.getString("nombre"),
                                            infoFinal.getString("alias"),
                                            infoFinal.getInt("num_reservados"));
                                }
                            }


                        } else {
                            user.setDataParqueoAsignado("No asignado", "desconocido", 0);
                        }
                    }

                    user.setLoggedUser(true);
                    render();


                } catch (JSONException e){

                    Log.e("VOLLEY","Error de parcing en Login - method: LoginRequest "+ e.toString());
                    e.printStackTrace();

                }

                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Error! Verifica tus credenciales", Toast.LENGTH_SHORT).show();
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
