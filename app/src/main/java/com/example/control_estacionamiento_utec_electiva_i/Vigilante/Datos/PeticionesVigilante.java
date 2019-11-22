package com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.DisponiblesVigilante;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PeticionesVigilante extends AppCompatActivity implements Globals {
    User user;
    ProgressDialog progressDialog;

    public void ObtenerEdificios(Context context){
        final AppCompatActivity mcontext = (AppCompatActivity) context;
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Cargando datos");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = BASE_URL+"edificios?api_token="+user.getApi_token();
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray mJsonArray = response.getJSONArray("edificios");
                    for (int i = 0; i < mJsonArray.length() ; i++) {

                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        String nombreEdificio = mJsonObject.getString("nombre");
                        int totalEstacionamiento = mJsonObject.getInt("num_parqueos");
                        int num_disponible = mJsonObject.getInt("num_disponible");
                        int idEdificio = mJsonObject.getInt("id");

                        DatosVigilante.setInfoEdificio(nombreEdificio, totalEstacionamiento,
                                num_disponible, idEdificio);
                        Log.i("TEST", "LLEGA");

                        mcontext.getSupportFragmentManager().beginTransaction()
                                .addToBackStack(null).replace(R.id.nav_host_fragment,
                                new DisponiblesVigilante()).commit();

                    }
                } catch (JSONException e){

                    Log.i("VOLLEY","Error de parcing en AssignParking - method: HTTPrequestTeacher "+ e.toString());
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




}
