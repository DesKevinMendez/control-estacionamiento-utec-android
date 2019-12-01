package com.example.control_estacionamiento_utec_electiva_i.Estudiante.Datos;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestRegister extends AppCompatActivity implements Globals {
    User user;
    ProgressDialog progressDialog;

    // PROPOSED METHOD
    public void registerStudent(final Context context, String name, String surname, String carnet,
                                String mail, String placa, String pass, String confirmPass, int rolId){
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Cargando Datos...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = BASE_URL+"register?api_token=" + user.getApi_token()
                + "&nombres" + name
                + "&apellidos" + surname
                + "&email" + mail
                + "&num_placa" + placa
                + "password" + pass
                + "password_confirmation" + confirmPass
                + "rol_id" + rolId;

        Map params = new HashMap();
        params.put("nombres", name);
        params.put("apellidos", surname);
        params.put("email", mail);
        params.put("num_placa", placa);
        params.put("password", pass);
        params.put("password_confirmation", confirmPass);
        params.put("rol_id", rolId);

        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(context);
        Log.i("LOGI","SE REALIZO LA PETICION");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i("LOGI", "dentro de try");
                    String data = response.getString("message");
                    Toast.makeText(context, data, Toast.LENGTH_LONG).show();
                    Log.i("LOGI", "Se registro el estudiante");

                } catch (JSONException e){

                    Log.i("VOLLEY","Error "+ e.toString());
                    e.printStackTrace();
                }
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Error al registrar", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        });

        queue.add(request);

    }

}
