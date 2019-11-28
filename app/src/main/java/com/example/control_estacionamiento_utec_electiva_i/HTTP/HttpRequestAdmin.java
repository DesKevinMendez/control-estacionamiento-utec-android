package com.example.control_estacionamiento_utec_electiva_i.HTTP;

import android.app.Activity;
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
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.example.control_estacionamiento_utec_electiva_i.Admin.AssignParking;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosVigilante;
import com.example.control_estacionamiento_utec_electiva_i.Admin.InicioAdmin;
import com.example.control_estacionamiento_utec_electiva_i.Admin.MainActivityAdmin;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals;
import com.example.control_estacionamiento_utec_electiva_i.Login.Login;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class HttpRequestAdmin extends AppCompatActivity implements Globals {

    ProgressDialog progressDialog;
    User user;
    public void HTTPrequestBuilding(final Context context, final String putStringName, final String putStringDescription) {
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
                    if (mJsonArray.length()== 0 ){
                        Toast.makeText(context, "Sin datos por mostrar", Toast.LENGTH_SHORT).show();

                    }
                    for (int i = 0; i < mJsonArray.length() ; i++) {

                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        String nombreEdificio = mJsonObject.getString("nombre");
                        int totalEstacionamiento = mJsonObject.getInt("num_parqueos");
                        int num_disponible = mJsonObject.getInt("num_disponible");
                        int idEdificio = mJsonObject.getInt("id");

                        DatosBuilding.setInfoEdificio(nombreEdificio, totalEstacionamiento,
                                num_disponible, idEdificio);

                        changeFragments(context, new SelectedBuilding(),
                                putStringName, putStringDescription);

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

    public void HTTPrequestWatchMan(final Context context, final String putStringName, final String putStringDescription) {
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Obteniendo vigilantes...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        progressDialog.show();

        String url = BASE_URL+"users-por-rol/vigilante?api_token="+user.getApi_token();
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray mJsonArray = response.getJSONArray("usuarios");
                    if (mJsonArray.length()== 0 ){
                        Toast.makeText(context, "Sin datos por mostrar", Toast.LENGTH_SHORT).show();

                    }
                    for (int i = 0; i < mJsonArray.length() ; i++) {

                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        String nombre = mJsonObject.getString("nombres");
                        String apellido = mJsonObject.getString("apellidos");
                        String placa = mJsonObject.getString("num_placa");
                        int idVigilante = mJsonObject.getInt("id");

                        DatosVigilante.setDatavigilante(nombre+" "+apellido, placa, idVigilante);

                        changeFragments(context, new SelectedTeacher(),
                                putStringName, putStringDescription);


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
                Toast.makeText(context, "Error al obtener los vigilantes :(", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

        queue.add(request);

    }

    public void HTTPrequestTeachers(final Context context, final String putStringName, final String putStringDescription) {
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Obteniendo maestros...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        progressDialog.show();

        String url = BASE_URL+"users-por-rol/docente?api_token="+user.getApi_token();
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray mJsonArray = response.getJSONArray("usuarios");
                    if (mJsonArray.length()== 0 ){
                        Toast.makeText(context, "Sin datos por mostrar", Toast.LENGTH_SHORT).show();

                    }
                    for (int i = 0; i < mJsonArray.length() ; i++) {

                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        String nombre = mJsonObject.getString("nombres");
                        String apellido = mJsonObject.getString("apellidos");
                        String placa = mJsonObject.getString("num_placa");
                        int idEdificio = mJsonObject.getInt("id");

                        DatosTeacher.setDataTeacher(nombre+" "+apellido, placa, idEdificio);


                        changeFragments(context, new SelectedTeacher(),
                                putStringName, putStringDescription);


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

                Toast.makeText(context, "Error al obtener los maestros :(", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();

            }
        });

        queue.add(request);

    }

    public void HTTPrequestReserverParking(final Context context, String user_id, String edificio_id,
                                           String fecha, String entrada,
                                    String salida, String cantidad, String comentario) {
        progressDialog = new ProgressDialog(this, R.style.AlertDialogStyle);
        progressDialog.setMessage("Reservando parqueo...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        progressDialog.show();

        String url = BASE_URL+"reservar-parqueo";
        Map<String, String> params = new HashMap();
        params.put("user_id", user_id);
        params.put("edificio_id", edificio_id);
        params.put("fecha", fecha);
        params.put("hora_entrada", entrada);
        params.put("hora_salida", salida);
        params.put("cantidad", cantidad);
        params.put("comentario", comentario);
        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONObject mJsonObject = response.getJSONObject("data");

                    Toast.makeText(context, "Parque reservado", Toast.LENGTH_SHORT).show();

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

    public void HTTPrequesteChangePassword(final Context context, String passActual, String newPass,
                                           String confiNewPass){
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Espere...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        progressDialog.show();

        String url = BASE_URL+"perfil/actualizar-contrasena?api_token="+user.getApi_token();
        Map<String, String> params = new HashMap();
        params.put("password", passActual);
        params.put("new_password", newPass);
        params.put("new_password_confirmation", confiNewPass);
        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Log.i("VOLLEY", "Se cambio contraseña");
                    /*
                    // Establece la sesion de usuario con falso, y limpia la data del usuario
                    user.setLoggedUser(false);

                    user.setDataUser(0, null, null, null, null,
                            null, 0, 0, null);


                    Intent login = new Intent(context, Login.class);
                    context.startActivity(login);
                    */
                    String data = response.getString("success");
                    Toast.makeText(context, data, Toast.LENGTH_SHORT).show();

                } catch (JSONException e){

                    Log.e("VOLLEY","Error de parcing en Login - method: LoginRequest "+ e.toString());
                    e.printStackTrace();

                }

                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Error! Intentelo de nuevo", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        });

        queue.add(request);
    }

    public void changeFragments(Context context, Fragment fragment, String putStringName, String putStringDescription) {

        AppCompatActivity activity = (AppCompatActivity) context;
        // Pasar datos de un fragment a otro
        Bundle datosAEnviar = new Bundle();
        datosAEnviar.putString(putStringName, putStringDescription);
        fragment.setArguments(datosAEnviar);

        activity.getSupportFragmentManager().beginTransaction().addToBackStack(null).
                replace(R.id.nav_host_fragment, fragment).commit();

    }

    public void changeFragments(Context context, Fragment fragment){
        // Establece teacherSelected y a buildingSelected como ""
        AppCompatActivity activity = (AppCompatActivity) context;
        DatosTeacher.setTeacherSelected(-1);
        DatosBuilding.setBuildingSelected(-1);

        activity.getSupportFragmentManager().beginTransaction().addToBackStack(null).
                replace(R.id.nav_host_fragment, fragment).commit();

    }
}
