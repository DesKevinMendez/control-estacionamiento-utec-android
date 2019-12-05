package com.example.control_estacionamiento_utec_electiva_i.Admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DataSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosVigilante;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedScheduleForAssignParking;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedTeacher;
import com.example.control_estacionamiento_utec_electiva_i.HTTP.HttpRequestAdmin;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals.BASE_URL;


public class AssignParking extends Fragment implements OnClickListener {


    public AssignParking() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(2).setChecked(true);

    }

    Button btnAceptar, btnDenegar, btnAssingSchedule, btnAsssingTeacher, btnSelctedBuilding;
    DataSchedule dataSchedule;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assign_parking_admin, container, false);

        btnAceptar = view.findViewById(R.id.btnAceptarAP);
        btnDenegar = view.findViewById(R.id.btnCancelarAP);
        btnAssingSchedule = view.findViewById(R.id.btnSelectedSchedule);
        btnAsssingTeacher = view.findViewById(R.id.btnSelectedTeacher);
        btnSelctedBuilding = view.findViewById(R.id.btnSelectedBuildingAP);

        btnAceptar.setOnClickListener(this);
        btnDenegar.setOnClickListener(this);
        btnAssingSchedule.setOnClickListener(this);
        btnAsssingTeacher.setOnClickListener(this);
        btnSelctedBuilding.setOnClickListener(this);

        // Determina si mostrar el nombre del edificio o no.

        Bundle datosRecuperados = getArguments();
        if (datosRecuperados == null && DatosTeacher.getTeacherSelected() != "" || DatosBuilding.getBuildingSelected() != ""
            || DatosSchedule.getHoraEntrada() != "" || DatosSchedule.getHoraSalida() != "") {


            if (DatosTeacher.getTeacherSelected() != "") {
                btnAsssingTeacher.
                        setText(DatosTeacher.getTeacherSelected());
            }
            if (DatosBuilding.getBuildingSelected() != "") {
                btnSelctedBuilding.
                        setText(DatosBuilding.getBuildingSelected());
            }

            if (DatosSchedule.getHoraSalida() != "" && DatosSchedule.getHoraEntrada() != "") {
                btnAssingSchedule.setText("De " + DatosSchedule.getHoraEntrada() + " hasta " +DatosSchedule.getHoraSalida());

            }

        } else if (datosRecuperados != null) {

            if (datosRecuperados.getString("maestroSeleccionado") != "" && DatosTeacher.getTeacherSelected() != "") {
                btnAsssingTeacher.
                        setText(DatosTeacher.getTeacherSelected());
            }
            if (datosRecuperados.getString("edificioSeleccionado") != "" && DatosBuilding.getBuildingSelected() != "") {
                btnSelctedBuilding.
                        setText(DatosBuilding.getBuildingSelected());
            }
            if (datosRecuperados.getString("horaEntrada") != "" && DatosSchedule.getHoraEntrada() != ""
                && datosRecuperados.getString("horaSalida") != "" && DatosSchedule.getHoraSalida() != "") {
                btnAssingSchedule.setText("De " + DatosSchedule.getHoraEntrada() + " hasta " +DatosSchedule.getHoraSalida());
            }
        }

        return view;
    }

    @Override
    public void onClick(View view) {

        HttpRequestAdmin httpRequestAdmin = new HttpRequestAdmin();
        switch(view.getId()){
            case R.id.btnAceptarAP:
                if (!dataSchedule.getHorariosSeleccionados().isEmpty()){

                    Toast.makeText(getActivity(), dataSchedule.getHorariosSeleccionados(), Toast.LENGTH_SHORT).show();

                } else if(btnAsssingTeacher.getText().equals(getString(R.string.selectedTeacher))) {

                    Toast.makeText(getActivity(), "Debe de seleccionar usuario", Toast.LENGTH_SHORT).show();

                } else if (btnSelctedBuilding.getText().equals(getString(R.string.selectedBuilding))) {

                    Toast.makeText(getActivity(), "Debe de seleccionar edificio", Toast.LENGTH_SHORT).show();

                } else {

                    Log.i("DATOS", DatosBuilding.getBuildingIdSelected());
                    Log.i("DATOS", DatosTeacher.getTeacherIdSelected());

                    HTTPrequestAssignParking(DatosBuilding.getBuildingIdSelected(),  DatosTeacher.getTeacherIdSelected(),
                            dataSchedule.getHora_entrada_lunes(), dataSchedule.getHora_salida_lunes(),
                        dataSchedule.getHora_entrada_martes(), dataSchedule.getHora_salida_martes(),
                        dataSchedule.getHora_entrada_miercoles(), dataSchedule.getHora_salida_miercoles(),
                        dataSchedule.getHora_entrada_jueves(), dataSchedule.getHora_salida_jueves(),
                        dataSchedule.getHora_entrada_viernes(), dataSchedule.getHora_salida_viernes(),
                        dataSchedule.getHora_entrada_sabado(), dataSchedule.getHora_salida_sabado(),
                        dataSchedule.getHora_entrada_domingo(), dataSchedule.getHora_salida_domingo());
                }

                break;
            case R.id.btnCancelarAP:

                changeFragments(new InicioAdmin());

                break;
            case R.id.btnSelectedBuildingAP:

                if (DatosBuilding.getTotalEdificios() == 0){

                    httpRequestAdmin.HTTPrequestBuilding(getActivity(), "actionOfAssignParking",
                            "ReserveParking");

                } else {
                    changeFragments(new SelectedBuilding(),
                            "actionOfAssignParking", "ReserveParking");
                }


                break;
            case R.id.btnSelectedSchedule:

                    changeFragments(new SelectedScheduleForAssignParking(),
                            "actionOfAssignParking", "SelectedSchedule");

                break;
            case R.id.btnSelectedTeacher:

                if (DatosTeacher.getTotalTeacher() == 0 ){

                    httpRequestAdmin.HTTPrequestTeachers(getActivity(), "actionOfAssignParking",
                            "SelectedTeacher");

                } else {
                    changeFragments(new SelectedTeacher(),
                            "actionOfAssignParking", "SelectedTeacher");
                }
                break;
        }
    }

    public void changeFragments(Fragment fragment, String putStringName, String putStringDescription) {
        // Pasar datos de un fragment a otro
        Bundle datosAEnviar = new Bundle();
        datosAEnviar.putString(putStringName, putStringDescription);
        fragment.setArguments(datosAEnviar);

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).
                replace(R.id.nav_host_fragment, fragment).commit();

    }


    public void changeFragments(Fragment fragment){
        // Establece teacherSelected y a buildingSelected como ""
        DatosTeacher.setTeacherSelected(-1);
        DatosBuilding.setBuildingSelected(-1);
        DatosSchedule.setHoraSalida("");
        DatosSchedule.setHoraEntrada("");

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).
                replace(R.id.nav_host_fragment, fragment).commit();

    }

    ProgressDialog progressDialog;
    public void HTTPrequestAssignParking(String vigilante_id, String edificio_id,
                                         String[] entrada_lunes, String[] salida_lunes,
                                         String[] entrada_martes, String[] salida_martes,
                                         String[] entrada_miercoles, String[] salida_miercoles,
                                         String[] entrada_jueves, String[] salida_jueves,
                                         String[] entrada_viernes, String[] salida_viernes,
                                         String[] entrada_sabado, String[] salida_sabado,
                                         String[] entrada_domingo, String[] salida_domingo
                                         ){


        progressDialog = new ProgressDialog(getActivity(), R.style.AlertDialogStyle);
        progressDialog.setMessage("Asignando vigilante...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        progressDialog.show();

        String url = BASE_URL+"asignar-parqueo?user_id="+vigilante_id+"&edificio_id="+edificio_id+"" +
                "&api_token="+User.getApi_token();
        Map<String, String[]> params = new HashMap();
        params.put("hora_entrada_lunes", entrada_lunes);
        params.put("hora_salida_lunes", salida_lunes);


        params.put("hora_entrada_martes", entrada_martes);
        params.put("hora_salida_martes", salida_martes);


        params.put("hora_entrada_miercoles", entrada_miercoles);
        params.put("hora_salida_miercoles", salida_miercoles);


        params.put("hora_entrada_jueves", entrada_jueves);
        params.put("hora_salida_jueves", salida_jueves);


        params.put("hora_entrada_viernes", entrada_viernes);
        params.put("hora_salida_viernes", salida_viernes);


        params.put("hora_entrada_sabado", entrada_sabado);
        params.put("hora_salida_sabado", salida_sabado);


        params.put("hora_entrada_domingo", entrada_domingo);
        params.put("hora_salida_domingo", salida_domingo);

        JSONObject parameters = new JSONObject(params);

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                    DatosVigilante.clearDatavigilante();
                    DatosVigilante.setClearFilter();
                    dataSchedule.setClearAllSchedules();
                    dataSchedule.setHorariosSeleccionados("Seleccione al menos un horario");
                    Toast.makeText(getActivity(), "Parqueo asignado", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    changeFragments(new InicioAdmin());


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
