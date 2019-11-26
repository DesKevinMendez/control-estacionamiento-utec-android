package com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos;


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
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.DisponiblesVigilante;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.ReservadosVigilante;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
                    DatosVigilante.dataBuilding().clear();
                    DatosVigilante.dataTotalEsta().clear();
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



    public void ObtenerReservados(Context context){
        final AppCompatActivity mcontext = (AppCompatActivity) context;
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Cargando datos");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        String url = BASE_URL+"reservas?api_token="+user.getApi_token();
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Log.i("TEST", "Se entro al try");


                    JSONArray mnJsonArray = response.getJSONArray("reservas"); // ESTA LINEA NO SE EJECUTA
                    Log.i("TEST", "se creo el json reservas");
                    DatosVigilante.dataBuilding().clear();
                    DatosVigilante.dataTotalEsta().clear();


                    for (int i = 0; i < mnJsonArray.length() ; i++) {
                        JSONObject mnJsonObject = mnJsonArray.getJSONObject(i);
                        JSONObject Edificio = mnJsonObject.getJSONObject("edificio");

                        String nombreEdificio = Edificio.getString("nombre");
                        int totalEstacionamiento = Edificio.getInt("num_parqueos");
                        int num_disponible = Edificio.getInt("num_disponible");
                        int idEdificio = Edificio.getInt("id");
                        DatosVigilante.setInfoEdificio(nombreEdificio, totalEstacionamiento,
                                num_disponible, idEdificio);
                        Log.i("TEST", "LLEGA");

                        mcontext.getSupportFragmentManager().beginTransaction()
                                .addToBackStack(null).replace(R.id.nav_host_fragment,
                                new ReservadosVigilante()).commit();


                    }
                } catch (JSONException e){

                    Log.i("VOLLEY","Error "+ e.toString());
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







    public void UsuariosPlaca(final Context context, String numPlaca){
    progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
    progressDialog.setMessage("Cargando Datos...");
    progressDialog.setIndeterminate(true);
    progressDialog.setCancelable(false);
    progressDialog.show();
    Log.i("MENS","SE CREO EL METODO");

    String url = BASE_URL+"users-por-placa?api_token="+user.getApi_token()+"&num_placa="+numPlaca;
    Map<String, String> params = new HashMap();
    JSONObject parameters = new JSONObject(params);
    RequestQueue queue = Volley.newRequestQueue(context);

    Log.i("MENS","SE REALIZO LA PETICION");

    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, parameters, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                Log.i("MENS","DENTRO DE TRY");
                //JSONArray mJsonArray = response.getJSONArray("usuario"); //ESTA LINEA NO SE EJECUTA
                Log.i("MENS","TODO BIEN");
                //for (int i = 0; i < mJsonArray.length() ; i++) {

                    //JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                    JSONObject mJsonObject = response.getJSONObject("usuario");
                    String nombre = mJsonObject.getString("nombres");
                    String apellido = mJsonObject.getString("apellidos");
                    String placa = mJsonObject.getString("num_placa");

                    JSONObject Reserva = mJsonObject.getJSONObject("reserva");
                    JSONObject Edificio = Reserva.getJSONObject("edificio");
                    JSONArray Horario = mJsonObject.getJSONArray("horarios");
                    JSONObject Horario1 = Horario.getJSONObject(0);
                    JSONObject Horario2 = Horario.getJSONObject(1);

                    String edificioAsignado = Edificio.getString("nombre");
                    String horaEntrada = Horario1.getString("hora_entrada");
                    String horaSalida = Horario1.getString("hora_salida");
                    int idUser = mJsonObject.getInt("id");
                    int idEdificio = Edificio.getInt("id");

                    DatosVigilante.setUsuarioPlaca(nombre,apellido,placa,edificioAsignado,horaEntrada,horaSalida,idUser,idEdificio);
                //}
            } catch (JSONException e){

                Log.i("VOLLEY","Error "+ e.toString());
                e.printStackTrace();

            }
            progressDialog.dismiss();

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            Toast.makeText(context, "Error al obtener el usuario ", Toast.LENGTH_SHORT).show();

            progressDialog.dismiss();

        }
    });

    queue.add(request);

}





    public void ValidarEntrada(final Context context, int edificioId, int userId){
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Cargando Datos...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Log.i("LOGI","SE CREO EL METODO VALIDAR ENTRADA");

        String url = BASE_URL+"users/validar-entrada?api_token="+user.getApi_token()+"&edificio_id"+edificioId+"&user_id"+userId;
        Map<String, Integer> params = new HashMap();
        params.put("edificio_id", edificioId);
        params.put("user_id", userId);
        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(context);

        Log.i("LOGI","SE REALIZO LA PETICION");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String data = response.getString("");
                    Toast.makeText(context, data, Toast.LENGTH_LONG).show();
                    Log.i("LOGI", "Se valido la entrada");
                } catch (JSONException e){

                    Log.i("VOLLEY","Error "+ e.toString());
                    e.printStackTrace();
                }
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Error al validar entrada", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        });

        queue.add(request);

    }



    public void ValidarSalida(final Context context, int userId){
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Cargando Datos...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Log.i("LOGI","SE CREO EL METODO VALIDAR");

        String url = BASE_URL+"users/validar-salida?api_token="+user.getApi_token();
        Map<String, Integer> params = new HashMap();
        params.put("user_id", userId);
        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(context);

        Log.i("MENS","SE REALIZO LA PETICION");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String data = response.getString("");
                    Toast.makeText(context, data, Toast.LENGTH_LONG).show();
                    Log.i("LOGI", "Se valido la Salida");
                } catch (JSONException e){

                    Log.i("VOLLEY","Error "+ e.toString());
                    e.printStackTrace();
                }
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Error al validar salida ", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        });

        queue.add(request);

    }




}
