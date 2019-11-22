package com.example.control_estacionamiento_utec_electiva_i.HTTP;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
import com.example.control_estacionamiento_utec_electiva_i.docentes.Estacionamiento;
import com.example.control_estacionamiento_utec_electiva_i.docentes.Estacionamientos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class HttpRequestDocente extends AppCompatActivity implements Globals {

    ProgressDialog progressDialog;
    User user;
    public void HTTPrequestBuilding(final Context context) {
        final AppCompatActivity activity = (AppCompatActivity) context;
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Obteniendo edificios...");
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

                        Estacionamientos.setInfoEdificios(nombreEdificio, totalEstacionamiento,
                                num_disponible);

                        Log.i("volley", nombreEdificio);
                        Intent list = new Intent(context, Estacionamiento.class);
                        activity.startActivity(list);


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
