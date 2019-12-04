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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.DisponiblesVigilante;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.NotificacionesVigilante;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.ReservadosVigilante;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.eventosVigilante;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PeticionesVigilantes extends AppCompatActivity implements Globals {
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
                        int num_ocupados = mJsonObject.getInt("num_ocupado");
                        int num_reservados = mJsonObject.getInt("num_reservados");
                        DatosVigilante.setInfoEdificio(nombreEdificio, totalEstacionamiento,
                                num_disponible, idEdificio, num_ocupados, num_reservados);
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
                String Nombre = "No hay reservaciones", Apellidos ="", Placa="", Edificio="", HoraEntrada="", HoraSalida="";
                try {
                    Log.i("TEST", "DENTRO DE TRY");
                    JSONArray mJsonArray = response.getJSONArray("reservas");
                    Log.i("TEST", "ARRAY CREADO");

                    DatosVigilante.getNombreR().clear();
                    DatosVigilante.getApellidoR().clear();
                    DatosVigilante.getPlacaR().clear();
                    DatosVigilante.getEdificioR().clear();
                    DatosVigilante.getEntradaR().clear();
                    DatosVigilante.getSalidaR().clear();

                    for (int i = 0; i <= mJsonArray.length() ; i++) {
                        Log.i("TEST", "DENTRO DEL FOR");

                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        JSONArray Usuario1 = mJsonObject.getJSONArray("users");
                        Log.i("TEST", "HASTA AQUI TODO BIEN");

                        String Fecha = mJsonObject.getString("fecha");
                        JSONObject Usuario = Usuario1.getJSONObject(0);

                         Nombre = Usuario.getString("nombres");
                         Apellidos = Usuario.getString("apellidos");
                         Placa = Usuario.getString("num_placa");

                        JSONArray Edificios = mJsonObject.getJSONArray("edificios");
                        JSONObject Edificio1 = Edificios.getJSONObject(0);
                         Edificio = Edificio1.getString("nombre");


                        JSONArray Horarios1 = mJsonObject.getJSONArray("horarios");
                        JSONObject Horarios = Horarios1.getJSONObject(0);
                         HoraEntrada = Horarios.getString("hora_entrada");
                         HoraSalida = Horarios.getString("hora_salida");


                        DatosVigilante.setInfoReservas(Nombre,Apellidos,Placa,Edificio,HoraEntrada,HoraSalida,Fecha);
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
        final AppCompatActivity mcontext = (AppCompatActivity) context;
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

                    JSONObject mJsonObject = response.getJSONObject("usuario");
                    String nombre = mJsonObject.getString("nombres");
                    String apellido = mJsonObject.getString("apellidos");
                    String placa = mJsonObject.getString("num_placa");
                    Integer estado = mJsonObject.getInt("estado");

                    Log.i("MENS","SIGUE BIEN ");
                    JSONArray Reservas = mJsonObject.getJSONArray("reservas");
                    JSONObject Reserva = Reservas.getJSONObject(0);
                    Log.i("MENS","PASO PRIMER ARRAY");

                    JSONArray Edificios1 = Reserva.getJSONArray("edificios");
                    JSONObject Edificios = Edificios1.getJSONObject(0);
                    Log.i("MENS","PASO SEGUNDO ARRAY");

                    JSONArray Horario = Reserva.getJSONArray("horarios");
                    JSONObject Horario1 = Horario.getJSONObject(0);
                    // JSONObject Horario2 = Horario.getJSONObject(1);
                    Log.i("MENS","PASO TERCER ARRAY");

                    String edificioAsignado = Edificios.getString("nombre");
                    String horaEntrada = Horario1.getString("hora_entrada");
                    String horaSalida = Horario1.getString("hora_salida");
                    int idUser = mJsonObject.getInt("id");
                    int idEdificio = Edificios.getInt("id");

                    DatosVigilante.setUsuarioPlaca(nombre,apellido,placa,estado,edificioAsignado,horaEntrada,horaSalida,idUser,idEdificio);
                    Log.i("MENS","PETICION EXITOSA");

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




    public void ValidarEntrada(final Context context, int userId, int edificioId){
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Cargando Datos...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Log.i("LOGI","SE CREO EL METODO VALIDAR ENTRADA");

        String url = BASE_URL+"users/validar-entrada?api_token="+user.getApi_token()+"&user_id="+userId+"&edificio_id="+edificioId;
        Map<String, Integer> params = new HashMap();
        params.put("user_id", userId);
        params.put("edificio_id", edificioId);
        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(context);

        Log.i("LOGI","SE REALIZO LA PETICION");

        // A partir de aqui nose ejecuta   //MARIO
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i("LOGI", "dentro de try");
                    String data = response.getString("message");
                    Toast.makeText(context, data, Toast.LENGTH_LONG).show();
                    Log.i("LOGI", "Se valido la Entrada");

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
        Log.i("LOGI","SE CREO EL METODO VALIDAR SALIDA");

        String url = BASE_URL+"users/validar-salida?api_token="+user.getApi_token()+"&user_id="+userId;
        Map<String, Integer> params = new HashMap();
        params.put("user_id", userId);
        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(context);

        Log.i("LOGI","SE REALIZO LA PETICION");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String data = response.getString("message");
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



    public void EdificiosId(Context context, int id){

        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Cargando datos");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = BASE_URL+"edificios/{}?api_token="+user.getApi_token()+"&id="+id;
        Map<String, Integer> params = new HashMap();
        params.put("id", id);
        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(context);
        Log.i("TEST", "SE CONECTO A LA API");

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Log.i("TEST", "DENTRO DE TRY");

                    JSONObject edificios= response.getJSONObject("edificio");
                    String nombreEdificio = edificios.getString("nombre");
                    int num_disponible = edificios.getInt("num_disponible");
                    int num_ocupados = edificios.getInt("num_ocupado");
                    DatosVigilante.llenarEdificio(nombreEdificio, num_ocupados, num_disponible);
                    Log.i("TEST", "LLEGA EL CLICK");


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








    public void ObtenerHistorial(Context context){
        final AppCompatActivity mcontext = (AppCompatActivity) context;
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Cargando datos");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        String url = BASE_URL+"historial?api_token="+user.getApi_token();
        RequestQueue queue = Volley.newRequestQueue(context);
        Log.i("TEST", "PETICION API");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Log.i("TEST", "DENTRO DE TRY");
                    JSONArray mJsonArray = response.getJSONArray("historial");
                    DatosVigilante.getNombreH().clear();
                    DatosVigilante.getApellidoH().clear();
                    DatosVigilante.getPlacaH().clear();
                    DatosVigilante.getComentarioH().clear();
                    for (int i = 0; i <= mJsonArray.length() ; i++) {
                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        String Comentario = mJsonObject.getString("comentario");

                        JSONObject Reserva = mJsonObject.getJSONObject("reserva");
                        JSONArray Usuario1 = Reserva.getJSONArray("users");
                        JSONObject Usuario = Usuario1.getJSONObject(0);

                        String Nombre = Usuario.getString("nombres");
                        String Apellido = Usuario.getString("apellidos");
                        String Num_placa = Usuario.getString("num_placa");

                        DatosVigilante.llenarHistorial(Nombre,Apellido,Num_placa,Comentario);
                        Log.i("TEST", "EJECUCION EXITOSA");

                        mcontext.getSupportFragmentManager().beginTransaction()
                                .addToBackStack(null).replace(R.id.nav_host_fragment,
                                new NotificacionesVigilante()).commit();


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


    public void ObtenerEventos(Context context){
        final AppCompatActivity mcontext = (AppCompatActivity) context;
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Cargando datos");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        String url = BASE_URL+"eventos?api_token="+user.getApi_token();
        RequestQueue queue = Volley.newRequestQueue(context);
        Log.i("TEST", "PETICION API");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Log.i("TEST", "DENTRO DE TRY");
                    JSONArray mJsonArray = response.getJSONArray("eventos");
                    DatosVigilante.getEdificioE().clear();
                    DatosVigilante.getCantidadE().clear();
                    DatosVigilante.getEntradaE().clear();
                    DatosVigilante.getSalidaE().clear();
                    DatosVigilante.getFechaE().clear();
                    DatosVigilante.getComentarioE().clear();
                    for (int i = 0; i <= mJsonArray.length() ; i++) {
                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        int cantidad = mJsonObject.getInt("cantidad");
                        String fecha = mJsonObject.getString("fecha");
                        String comentario = mJsonObject.getString("comentario");

                        JSONObject Edificios = mJsonObject.getJSONObject("edificio");
                        String edificio = Edificios.getString("nombre");

                        JSONObject Horario = mJsonObject.getJSONObject("horario");
                        String horaEntrada=Horario.getString("hora_entrada");
                        String horaSalida=Horario.getString("hora_salida");


                        DatosVigilante.setEventos(edificio,cantidad,horaEntrada,horaSalida,fecha,comentario);
                        Log.i("TEST", "EJECUCION EXITOSA");


                        mcontext.getSupportFragmentManager().beginTransaction()
                                .addToBackStack(null).replace(R.id.nav_host_fragment,
                                new eventosVigilante()).commit();


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






    public void AgregarComentario(final Context context, String userId, String Comentario){
        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Enviando...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Log.i("LOGI","SE CREO EL METODO AGREGAR COMENTARIOS");

        String url = BASE_URL+"historial/agregar-comentario?api_token="+user.getApi_token()+"&user_id="+userId+"&comentario="+Comentario;
        Map<String, String> params = new HashMap();
        params.put("user_id", userId);
        params.put("comentario", Comentario);
        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(context);

        Log.i("LOGI","SE REALIZO LA PETICION");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i("LOGI", "dentro de try");
                    JSONObject respuesta = response.getJSONObject("historial");
                    Log.i("LOGI", "Se Guardo el comentario");

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


}
