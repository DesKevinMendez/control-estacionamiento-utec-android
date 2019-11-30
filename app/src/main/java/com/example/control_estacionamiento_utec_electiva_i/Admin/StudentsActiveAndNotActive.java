package com.example.control_estacionamiento_utec_electiva_i.Admin;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.Adapters.BuildingAdapter;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.Adapters.StudentsAdapter;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosStudents;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentsActiveAndNotActive extends Fragment implements RadioGroup.OnCheckedChangeListener {


    public StudentsActiveAndNotActive() {
        // Required empty public constructor
    }



    ListView ListStudents;
    DatosStudents datosStudents;
    RadioButton rdbActivo, rdbInactivo;
    RadioGroup grdbTipoUsuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_students_active_and_not_active_admin, container, false);
        ListStudents = view.findViewById(R.id.listViewStudents);


        grdbTipoUsuario = view.findViewById(R.id.rgrdTipoUsuario);
        rdbActivo = view.findViewById(R.id.rdbStudentesActive);
        rdbInactivo = view.findViewById(R.id.rdbStudentsInactive);

        grdbTipoUsuario.setOnCheckedChangeListener(this);

        ListStudents.setAdapter(new
                StudentsAdapter(getActivity(),
                datosStudents.getStudentname(),
                datosStudents.getStudentPlaca(),
                datosStudents.getStudentCarnet(),
                datosStudents.getStudentBuilding()));
        return view;
    }

    ProgressDialog progressDialog;
    public void HTTPrequestStudentsActive(String tipoEstudiante) {
        progressDialog = new ProgressDialog(getActivity(), R.style.AlertDialogStyle);
        progressDialog.setMessage("Obteniendo estudiantes...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        progressDialog.show();

        String url = BASE_URL+"users-por-rol/alumno-"+tipoEstudiante+"?api_token="+ User.getApi_token();
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    DatosStudents.setClearAllStudentsData();
                    JSONArray mJsonArray = response.getJSONArray("usuarios");
                    if (mJsonArray.length()== 0 ){
                        Toast.makeText(getActivity(), "Sin datos por mostrar", Toast.LENGTH_SHORT).show();

                    }
                    for (int i = 0; i < mJsonArray.length() ; i++) {

                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        String nombre = mJsonObject.getString("nombres");
                        String apellido = mJsonObject.getString("apellidos");
                        String carnet = mJsonObject.getString("carnet");
                        String placa = mJsonObject.getString("num_placa");
                        int idStudent = mJsonObject.getInt("id");

                        DatosStudents.setDataStudents(nombre+" "+ apellido, carnet, placa,
                                "no disponible", idStudent);


                    }
                    ListStudents.setAdapter(new
                            StudentsAdapter(getActivity(),
                            datosStudents.getStudentname(),
                            datosStudents.getStudentPlaca(),
                            datosStudents.getStudentCarnet(),
                            datosStudents.getStudentBuilding()));

                } catch (JSONException e){

                    Log.i("VOLLEY", e.toString());
                    e.printStackTrace();

                }
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error al obtener los vigilantes :(", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

        queue.add(request);

    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        switch (checkedId){
            case R.id.rdbStudentesActive:
                HTTPrequestStudentsActive("activo");
                break;
            case R.id.rdbStudentsInactive:
                HTTPrequestStudentsActive("inactivo");
                break;
                default:
                    break;

        }
    }
}
