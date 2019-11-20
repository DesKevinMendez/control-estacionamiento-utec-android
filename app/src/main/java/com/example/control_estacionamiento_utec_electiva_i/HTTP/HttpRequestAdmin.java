package com.example.control_estacionamiento_utec_electiva_i.HTTP;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals;
import com.example.control_estacionamiento_utec_electiva_i.Login.Login;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestAdmin implements Globals {

    ProgressDialog progressDialog;
    User user;
    public void HTTPrequestBuilding(Context context) {
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Obteniendo maestros...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        progressDialog.show();

        String url = BASE_URL +"edificios'";
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray mJsonArray = response.getJSONArray("contacts");
                    for (int i = 0; i < mJsonArray.length() ; i++) {

                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        String name = mJsonObject.getString("name");

                        JSONObject infoPhone = mJsonObject.getJSONObject("phone");
                        String phone = infoPhone.getString("mobile");

                        DatosTeacher.setDataTeacher(name, phone);

                    }
                } catch (JSONException e){

                    Log.e("VOLLEY","Error de parcing en AssignParking - method: HTTPrequestTeacher "+ e.toString());
                    e.printStackTrace();

                }
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                Log.e("VOLLEY", error.getMessage());
            }
        });

        queue.add(request);

    }

    public void HTTPrequesteChangePassword(final Context context, String passActual, String newPass,
                                           String confiNewPass){
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Espere...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        progressDialog.show();

        String url = BASE_URL+"login";
        Map<String, String> params = new HashMap();
        params.put("email", passActual);
        params.put("password", newPass);
        params.put("password", confiNewPass);
        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    // Establece la sesion de usuario con falso, y limpia la data del usuario
                    user.setLoggedUser(false);

                    user.setDataUser(0, null, null, null, null,
                            null, 0, 0, null);
                    JSONObject mJsonObject = response.getJSONObject("data");
                    Intent login = new Intent(context, Login.class);
                    context.startActivity(login);

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
                Log.e("VOLLEY", error.getMessage());
            }
        });

        queue.add(request);
    }
}
