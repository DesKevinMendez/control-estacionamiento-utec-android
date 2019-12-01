package com.example.control_estacionamiento_utec_electiva_i.Admin;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.HTTP.HttpRequestAdmin;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    EditText tvFindStudents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_students_active_and_not_active_admin, container, false);
        ListStudents = view.findViewById(R.id.listViewStudents);


        grdbTipoUsuario = view.findViewById(R.id.rgrdTipoUsuario);
        rdbActivo = view.findViewById(R.id.rdbStudentesActive);
        rdbInactivo = view.findViewById(R.id.rdbStudentsInactive);

        tvFindStudents = view.findViewById(R.id.tvFindStudents);

        grdbTipoUsuario.setOnCheckedChangeListener(this);

        ListStudents.setAdapter(new
                StudentsAdapter(getActivity(),
                datosStudents.getStudentname(),
                datosStudents.getStudentPlaca(),
                datosStudents.getStudentCarnet(),
                datosStudents.getStudentBuilding()));

        /*if (DatosBuilding.getTotalEdificios() == 0 ){
            HttpRequestAdmin httpRequestAdmin = new HttpRequestAdmin();
            httpRequestAdmin.HTTPrequestBuilding(getActivity(),
                    "AlertDialog", "ShowSppinerInAlertDiaog");
        }*/

        tvFindStudents.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.toString().equals("")) {
                    datosStudents.setClearAllFilterStudentsData();
                }

                ListStudents.setAdapter(new
                        StudentsAdapter(getActivity(),
                        datosStudents.getFilterStudents(editable.toString()),
                        datosStudents.getFilterStudentPlaca(),
                        datosStudents.getFilterStudentCarnet(),
                        datosStudents.getFilterStudentBuilding()));
            }
        });



        ListStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (rdbInactivo.isChecked()){


                    DatosStudents.setInfoSelectedStudents(i);



                    final CharSequence[] dataStudentsSelected = {
                            "Nombre: " + datosStudents.getName(),
                            "Placa: " + datosStudents.getPlaca(),
                            "Carnet: " + datosStudents.getCarnet(),
                            "Building: " + datosStudents.getBuilding(),
                    };



                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setTitle("¿Autorizar entrada?");
                    View mView = getLayoutInflater().inflate(R.layout.dialog_spinner_students, null);
                    final Spinner mSpinner = mView.findViewById(R.id.spinnerDialog);


                    ArrayAdapter<String> adapterSpinnerDialog =
                            new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_spinner_item,
                                    DatosBuilding.dataBuilding());


                    adapterSpinnerDialog.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner.setAdapter(adapterSpinnerDialog);

                    alert.setItems(dataStudentsSelected, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            // Do something with the selection
                        }
                    });
                    alert.setPositiveButton("Otorgar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DatosBuilding.setBuildingIdSelected(mSpinner.getSelectedItemPosition());

                            HttpRequestAdmin httpRequestAdmin = new HttpRequestAdmin();
                            httpRequestAdmin.HTTPrequestAssignParkingToStudents(getActivity(), String.valueOf(DatosStudents.getID()), String.valueOf(DatosBuilding.getBuildingIdSelected()));
                        }
                    });
                    alert.setNegativeButton("Negar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.setView(mView);
                    alert.create().show();
                }
            }
        });
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
