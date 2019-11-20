package com.example.control_estacionamiento_utec_electiva_i.HTTP;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
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

public class HttpLoginRequest implements Globals {

    ProgressDialog progressDialog;
    User user;
    public void LoginRequest(Context context, String mail, String pass) {
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Obteniendo maestros...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        progressDialog.show();

        String url = BASE_URL+" login";
        Map<String, String> params = new HashMap();
        params.put("email", mail);
        params.put("password", pass);
        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(context);

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


                    user.setLoggedUser(true);

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
                Log.e("VOLLEY", error.getMessage());
            }
        });

        queue.add(request);

    }
}
