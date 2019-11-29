package com.example.control_estacionamiento_utec_electiva_i.Admin;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosEvents;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosVigilante;
import com.example.control_estacionamiento_utec_electiva_i.Admin.InfoSetViews.ReserveParkingDataSend;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedTeacher;
import com.example.control_estacionamiento_utec_electiva_i.HTTP.HttpRequestAdmin;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals.BASE_URL;

public class RecerveParking extends Fragment implements View.OnClickListener {
    private OnFragmentInteractionListener mListener;

    public RecerveParking() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(4).setChecked(true);
    }

    Button btnSeleccionarDocenteRP, btnSeleccionarEstacionamientoRP, btnSeleccionarHorarioRP,
            btnAceptarRP, btnDenegarRP;
    Spinner  spCantidadHorariosRP;
    EditText edtComentario;
    EditText edtFecha;

    String cantidadReservaEstacionamiento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recerve_parking_admin, container, false);

        spCantidadHorariosRP = view.findViewById(R.id.spCantidadHorariosRP);

        ArrayAdapter<CharSequence> ad = ArrayAdapter.
                createFromResource(getActivity(), R.array.cantidad_parqueo, R.layout.spinner_item_design);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCantidadHorariosRP.setAdapter(ad);

        edtFecha = view.findViewById(R.id.edtFecha);

        btnSeleccionarDocenteRP = view.findViewById(R.id.btnSeleccionarDocenteRP);
        btnSeleccionarEstacionamientoRP = view.findViewById(R.id.btnSeleccionarEstacionamientoRP);
        btnSeleccionarHorarioRP = view.findViewById(R.id.btnSeleccionarHorarioRP);
        btnAceptarRP = view.findViewById(R.id.btnAceptarRP);
        btnDenegarRP = view.findViewById(R.id.btnDenegarRP);

        edtComentario = view.findViewById(R.id.edtComentario);


        Bundle datosRecuperados = getArguments();
        if (datosRecuperados != null){
            if (DatosTeacher.getTeacherSelected() != "") {
                btnSeleccionarDocenteRP.
                        setText(DatosTeacher.getTeacherSelected());
            }
            if (DatosBuilding.getBuildingSelected() != "") {
                btnSeleccionarEstacionamientoRP.
                        setText(DatosBuilding.getBuildingSelected());
            }

            if (!DatosSchedule.getHoraSalida().isEmpty() && !DatosSchedule.getHoraEntrada().isEmpty()){
                btnSeleccionarHorarioRP.setText("Desde "+ DatosSchedule.getHoraEntrada()
                        + " hasta "+ DatosSchedule.getHoraSalida());
            }
        }

        if (!ReserveParkingDataSend.getFechaReserva().equals("")){
            edtFecha.setText(ReserveParkingDataSend.getFechaReserva());
        }
        btnSeleccionarDocenteRP.setOnClickListener(this);
        btnSeleccionarEstacionamientoRP.setOnClickListener(this);
        btnSeleccionarHorarioRP.setOnClickListener(this);
        btnAceptarRP.setOnClickListener(this);
        btnDenegarRP.setOnClickListener(this);

        edtFecha.setOnClickListener(this);

        spCantidadHorariosRP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                cantidadReservaEstacionamiento = spCantidadHorariosRP.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
        return view;
    }

    @Override
    public void onClick(View view) {

        HttpRequestAdmin httpRequestAdmin = new HttpRequestAdmin();
        switch (view.getId()) {
            case R.id.btnSeleccionarDocenteRP:

                if (DatosBuilding.getTotalEdificios() == 0){

                    httpRequestAdmin.HTTPrequestTeachers(getActivity(), "actionOfReserverParking",
                            "ReserveParking");

                } else {
                    changeFragments(new SelectedTeacher(),
                            "actionOfReserverParking", "ReserveParking");
                }

                break;
            case R.id.btnSeleccionarEstacionamientoRP:


                if (DatosBuilding.getTotalEdificios() == 0){
                    httpRequestAdmin.HTTPrequestBuilding(getActivity(),
                            "actionOfReserverParking", "ReserveParking");
                } else {

                    changeFragments(new SelectedBuilding(),
                            "actionOfReserverParking", "ReserveParking");
                }


                break;
            case R.id.btnSeleccionarHorarioRP:
                changeFragments(new SelectedSchedule(),
                        "actionOfReserverParking", "ReserveParking");

                break;
            case R.id.btnAceptarRP:
                String comentario = edtComentario.getText().toString().trim();
                String fecha = edtFecha.getText().toString().trim();

                if (fecha.isEmpty()){
                    edtFecha.setError("Seleccione una fecha");

                } else if (btnSeleccionarDocenteRP.getText().equals(getString(R.string.selectedTeacher))){

                    Toast.makeText(getActivity(), "Debe de seleccionar un maestro", Toast.LENGTH_SHORT).show();

                } else if (btnSeleccionarEstacionamientoRP.getText().equals(getString(R.string.selectedBuilding))){

                    Toast.makeText(getActivity(), "Debe de seleccionar estacionamiento", Toast.LENGTH_SHORT).show();

                } else if (btnSeleccionarHorarioRP.getText().equals(getString(R.string.selectedScedule))){

                    Toast.makeText(getActivity(), "Debe de seleccionar horarios", Toast.LENGTH_SHORT).show();

                } else if (comentario.isEmpty()){

                    edtComentario.setError("Comentario requerido");
                    edtComentario.setCursorVisible(true);
                    edtComentario.requestFocus();

                } else {

                    Log.i("Fecha", fecha);
                    Log.i("Maestro seleccionado", DatosTeacher.getTeacherIdSelected());
                    Log.i("Edificio seleccionado", DatosBuilding.getBuildingIdSelected());
                    Log.i("Cantida reservador", cantidadReservaEstacionamiento);
                    Log.i("Horarios", DatosSchedule.getHoraEntrada() + " " + DatosSchedule.getHoraSalida());
                    Log.i("Motivo de reserva", comentario);

                    HTTPrequestResereParking(DatosTeacher.getTeacherIdSelected(), DatosBuilding.getBuildingIdSelected(),
                            fecha, DatosSchedule.getHoraEntrada(), DatosSchedule.getHoraSalida(),
                            cantidadReservaEstacionamiento, comentario);

                }


                break;
            case R.id.edtFecha:

                showDatePickerDialog();

                break;
            case R.id.btnDenegarRP:
                changeFragments(new InicioAdmin());

                break;
                default:
                    break;
        }
    }
    ProgressDialog progressDialog;
    public void HTTPrequestResereParking(String vigilante_id,
                                         String edificio_id, String fecha, String entrada,
                                         String salida, String cantidad, String motivo) {


        progressDialog = new ProgressDialog(getContext(), R.style.AlertDialogStyle);
        progressDialog.setMessage("Asignando vigilante...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        progressDialog.show();

        String url = BASE_URL+"reservar-parqueo?api_token="+ User.getApi_token();
        Map<String, String> params = new HashMap();
        params.put("user_id", String.valueOf(vigilante_id));
        params.put("edificio_id", String.valueOf(edificio_id));
        params.put("fecha", fecha);
        params.put("hora_entrada", entrada);
        params.put("hora_salida", salida);
        params.put("cantidad", String.valueOf(cantidad));
        params.put("comentario", motivo);

        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(getContext(), "Reserva de parqueo asignada", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

                changeFragments(new InicioAdmin());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

            }
        }){
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                return headers;
            }
        };

        queue.add(request);

    }

    private void showDatePickerDialog() {
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker;
        mDatePicker = new DatePickerDialog(getActivity(), R.style.CustomDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                TextView PlannedDate = getActivity().findViewById(R.id.edtFecha);
                String selectedDate = selectedyear+ "-" + (selectedmonth+1) + "-" + selectedday ;
                PlannedDate.setText(selectedDate);
                ReserveParkingDataSend.setFechaReserva(selectedDate);

            }
        }, mYear, mMonth, mDay);
        mDatePicker.show();

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

    public void changeFragments(Fragment fragment, String putStringName, String putStringDescription) {
        // Pasar datos de un fragment a otro
        Bundle datosAEnviar = new Bundle();
        datosAEnviar.putString(putStringName, putStringDescription);
        fragment.setArguments(datosAEnviar);

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).
                replace(R.id.nav_host_fragment, fragment).commit();

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
