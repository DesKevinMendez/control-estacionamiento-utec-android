package com.example.control_estacionamiento_utec_electiva_i.Admin;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosEvents;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosVigilante;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.HTTP.HttpRequestAdmin;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals.BASE_URL;


public class ReserveEvents extends Fragment implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener
    {
    private OnFragmentInteractionListener mListener;

    public ReserveEvents() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(3).setChecked(true);
    }

    Button btnSelectedBuilding, btnAceptar, btnDenegar;
    Spinner spCantidad, SpHorarioEntradaRP, SpHorarioSalidaRP;
    EditText etPlannedDate, edtHorariosEntrada, edtHorariosSalida, edtMotivoReservaRE;
    RadioGroup grdbHorarios;
    RadioButton rdbSelccionable, rdbEditable;

    String cantidadReserva, HorarioEntrada, HorariosSalida;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reserve_events_admin, container, false);

        btnSelectedBuilding = view.findViewById(R.id.btnSelectedBuilding);

        Bundle recibeDatos = getArguments();
        if (recibeDatos != null) {
            btnSelectedBuilding.setText(DatosBuilding.getBuildingSelected());
        }

        grdbHorarios = view.findViewById(R.id.grdbHorarios);
        rdbSelccionable = view.findViewById(R.id.rdbSelccionable);
        rdbEditable = view.findViewById(R.id.rdbEditable);

        btnAceptar = view.findViewById(R.id.btnAceparRP);
        btnDenegar = view.findViewById(R.id.btnDenegarRP);


        spCantidad = view.findViewById(R.id.SpCantidadEstacionamiento);
        SpHorarioEntradaRP = view.findViewById(R.id.SpHorarioEntradaRP);
        SpHorarioSalidaRP = view.findViewById(R.id.spHorarioSalidaRP);

        ArrayAdapter<CharSequence> ad = ArrayAdapter.
                createFromResource(getActivity(), R.array.cantidad_parqueo, R.layout.spinner_item_design);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCantidad.setAdapter(ad);


        ArrayAdapter<CharSequence> hr = ArrayAdapter.
                createFromResource(getActivity(), R.array.horarios, R.layout.spinner_item_design);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpHorarioEntradaRP.setAdapter(hr);
        SpHorarioSalidaRP.setAdapter(hr);


        etPlannedDate = view.findViewById(R.id.etPlannedDate);
        edtHorariosEntrada = view.findViewById(R.id.edtHorariosEntrada);
        edtHorariosSalida = view.findViewById(R.id.edtHorariosSalida);
        edtMotivoReservaRE = view.findViewById(R.id.edtMotivoReservaRE);

        if (!DatosEvents.getFecha().isEmpty()) {
            etPlannedDate.setText(DatosEvents.getFecha());
        }
        etPlannedDate.setOnClickListener(this);
        edtHorariosEntrada.setOnClickListener(this);
        edtHorariosSalida.setOnClickListener(this);


        btnSelectedBuilding.setOnClickListener(this);
        btnAceptar.setOnClickListener(this);
        btnDenegar.setOnClickListener(this);

        grdbHorarios.setOnCheckedChangeListener(this);

        Spinners(spCantidad, R.id.SpCantidadEstacionamiento);
        Spinners(SpHorarioEntradaRP, R.id.SpHorarioEntradaRP);
        Spinners(SpHorarioSalidaRP, R.id.spHorarioSalidaRP);


        return view;
    }


    public void Spinners(final Spinner spinner, final int SpinnerID){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                switch (SpinnerID){
                    case R.id.SpHorarioEntradaRP:
                        HorarioEntrada = spinner.getItemAtPosition(position).toString();

                        break;
                    case R.id.spHorarioSalidaRP:

                        HorariosSalida = spinner.getItemAtPosition(position).toString();
                       
                        break;
                    case R.id.SpCantidadEstacionamiento:

                        cantidadReserva = spinner.getItemAtPosition(position).toString();

                        break;
                        default:
                            break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                HorarioEntrada = "";
                HorariosSalida = "";
                cantidadReserva = "";
            }

        });
    }

    @Override
    public void onClick(View view) {

        HttpRequestAdmin httpRequestAdmin = new HttpRequestAdmin();
        switch (view.getId()){
            case R.id.btnSelectedBuilding:

                if (DatosBuilding.getTotalEdificios() == 0){

                    httpRequestAdmin.HTTPrequestBuilding(getActivity(), "actionOfReserverEvents", "ReserveEvents");

                } else {
                    // Pasar datos de un fragment a otro
                    changeFragments(new SelectedBuilding(), "actionOfReserverEvents", "ReserveEvents");

                }


                break;

            case R.id.btnAceparRP:
                String fecha = etPlannedDate.getText().toString().trim();
                String motivoReserva = edtMotivoReservaRE.getText().toString().trim();

                final String[] horaSalidaArray;
                final String[] horaEntradaArray;

                if (rdbEditable.isChecked()){


                    String entrada = edtHorariosEntrada.getText().toString().trim();
                    String salida = edtHorariosSalida.getText().toString().trim();
                    if (entrada.isEmpty()){
                        edtHorariosEntrada.setError("Seleccione horario de entrada");
                        return;
                    } else if (salida.isEmpty()){
                        edtHorariosSalida.setError("Seleccione horario de salida");
                        return;
                    }

                    HorarioEntrada = entrada;
                    HorariosSalida = salida;

                }

                horaSalidaArray = HorariosSalida.split(":");
                horaEntradaArray = HorarioEntrada.split(":");


                if (Integer.valueOf(horaSalidaArray[0]) <10 ){
                    if (horaSalidaArray[0].length() < 2){

                        HorariosSalida = "0"+horaSalidaArray[0]+":"+horaSalidaArray[1];
                    }
                }


                if (Integer.valueOf(horaEntradaArray[0]) <10 ){
                    if (horaEntradaArray[0].length() < 2){

                        HorarioEntrada = "0"+horaEntradaArray[0]+":"+horaEntradaArray[1];
                    }
                }


                if (btnSelectedBuilding.getText().toString().equals(getString(R.string.selectedBuilding))) {
                    Toast.makeText(getContext(), "Seleccione parqueo", Toast.LENGTH_SHORT).show();

                } else if (HorarioEntrada.equals(HorariosSalida)) {
                    Toast.makeText(getActivity(), "Seleccione horario de salida diferente", Toast.LENGTH_SHORT).show();

                } else if(Integer.valueOf(horaEntradaArray[0]) > Integer.valueOf(horaSalidaArray[0])){
                    Toast.makeText(getActivity(), "Hora entrada debe de ser menor que la de salida", Toast.LENGTH_SHORT).show();

                } else if (fecha.isEmpty()){
                    Toast.makeText(getContext(), "Selecciona fecha", Toast.LENGTH_SHORT).show();

                } else if (motivoReserva.isEmpty()){
                    edtMotivoReservaRE.setError("Especifique el motivo");
                    edtMotivoReservaRE.requestFocus();

                } else {
                    Log.i("Parqueo", DatosBuilding.getBuildingIdSelected());
                    Log.i("Cantidad estacionamiento", cantidadReserva);
                    Log.i("Horario entrada", HorarioEntrada);
                    Log.i("Horario salida", HorariosSalida);
                    Log.i("Fecha", fecha);
                    Log.i("Motivo reserva", motivoReserva);
                    HTTPrequestAssignWathMan(getActivity(), DatosBuilding.getBuildingIdSelected(),
                            fecha,
                            HorarioEntrada, HorariosSalida, cantidadReserva, motivoReserva);


                    //changeFragments(new InicioAdmin());
                }
                break;
            case R.id.btnDenegarRP:
                changeFragments(new InicioAdmin());

                break;


            case R.id.etPlannedDate:

                showDatePickerDialog();
                break;

            case R.id.edtHorariosEntrada:
                showClockPickerDialog(R.id.edtHorariosEntrada);

                break;

            case R.id.edtHorariosSalida:
                showClockPickerDialog(R.id.edtHorariosSalida);

                break;
            default:
                break;
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId){
            case R.id.rdbEditable:
                SpHorarioSalidaRP.setVisibility(View.GONE);
                SpHorarioEntradaRP.setVisibility(View.GONE);

                edtHorariosSalida.setVisibility(View.VISIBLE);
                edtHorariosEntrada.setVisibility(View.VISIBLE);

                if (!HorarioEntrada.isEmpty()){
                    edtHorariosEntrada.setText(HorarioEntrada);
                }

                if (!HorariosSalida.isEmpty()){
                    edtHorariosSalida.setText(HorariosSalida);
                }

                break;
            case R.id.rdbSelccionable:
                SpHorarioSalidaRP.setVisibility(View.VISIBLE);
                SpHorarioEntradaRP.setVisibility(View.VISIBLE);

                edtHorariosSalida.setVisibility(View.GONE);
                edtHorariosEntrada.setVisibility(View.GONE);

                break;
                default:
                    break;
        }
    }

        ProgressDialog progressDialog;
        public void HTTPrequestAssignWathMan(final Context context,
                                             final String edificio,
                                             final String fecha, final String entrada, final String salida, final String canti,
                                             final String comentario) {

            progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
            progressDialog.setMessage("Asignando vigilante...");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);

            progressDialog.show();

            String url = BASE_URL+"crear-evento?api_token="+User.getApi_token();
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(context, "El evento ha sido guardado", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            changeFragments(new InicioAdmin());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Log.i("ERROR", error.toString());
                            progressDialog.dismiss();
                            NetworkResponse networkResponse = error.networkResponse;
                            if (networkResponse != null && networkResponse.statusCode == 422) {
                                Toast.makeText(getActivity(), "Fecha incorrecta", Toast.LENGTH_SHORT).show();
                            }
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(getActivity(),
                                        "Tiempo de conexión excedido",
                                        Toast.LENGTH_LONG).show();
                            } else if (error instanceof ServerError) {
                                // TO DO
                            } else if (error instanceof NetworkError) {

                                Toast.makeText(getActivity(), "Sin conexión a internet", Toast.LENGTH_LONG).show();
                            } else if (error instanceof ParseError) {
                                Toast.makeText(getActivity(), "Error de parcing", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<>();
                    // the POST parameters:
                    params.put("edificio_id", edificio);
                    params.put("fecha", fecha);
                    params.put("hora_entrada", entrada);
                    params.put("hora_salida", salida);
                    params.put("cantidad", canti);
                    params.put("comentario", comentario);
                    return params;
                }
            };
            Volley.newRequestQueue(context).add(postRequest);
        }



    public void changeFragments(Fragment fragment, String actionOfReserverEvents, String ReserveEvents){
        Bundle datosAEnviar = new Bundle();
        datosAEnviar.putString(actionOfReserverEvents, ReserveEvents);

        fragment.setArguments(datosAEnviar);

        getActivity().getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.nav_host_fragment, fragment).commit();

    }

    public void changeFragments(Fragment fragment){
        // Establece teacherSelected y a buildingSelected como ""
        DatosBuilding.setBuildingSelected(-1);
        DatosSchedule.setHoraSalida("");
        DatosSchedule.setHoraEntrada("");

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).
                replace(R.id.nav_host_fragment, fragment).commit();

    }

    private void showDatePickerDialog() {
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker;
        mDatePicker = new DatePickerDialog(getActivity(), R.style.CustomDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                TextView PlannedDate = getActivity().findViewById(R.id.etPlannedDate);
                String dia = "";
                if (selectedday < 9){
                    dia = "0"+selectedday;
                } else {
                    dia = String.valueOf(selectedday);
                }
                String selectedDate = selectedyear + "-" + (selectedmonth+1) + "-" + dia;
                PlannedDate.setText(selectedDate);
                DatosEvents.setFecha(selectedDate);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.show();

    }

    public void showClockPickerDialog(final int idInput) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), R.style.CustomDatePickerDialogTheme,
                new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                switch (idInput){
                    case R.id.edtHorariosEntrada:
                        edtHorariosEntrada.setText(hourOfDay + ":" + minutes);

                        break;

                    case R.id.edtHorariosSalida:

                        edtHorariosSalida.setText(hourOfDay + ":" + minutes);

                        break;
                }
            }
        }, 0, 0, false);
        timePickerDialog.show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
