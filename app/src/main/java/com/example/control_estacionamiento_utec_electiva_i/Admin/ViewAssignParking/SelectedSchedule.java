package com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking;

import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.Admin.AssignParking;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosStudents;
import com.example.control_estacionamiento_utec_electiva_i.Admin.RecerveParking;
import com.example.control_estacionamiento_utec_electiva_i.R;

public class SelectedSchedule extends Fragment implements AdapterView.OnItemSelectedListener,
        RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    public SelectedSchedule() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    RadioGroup grdbHorariosAdmin;
    RadioButton rdbSelccionable, rdbEditable;

    EditText edtHorariosEntrada, edtHorariosSalida;
    Spinner horarioInicio, horarioSalida;
    Button btnAceptarSS;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selected_schedule_admin, container, false);

        horarioInicio = view.findViewById(R.id.SpHoraInicio);
        horarioSalida = view.findViewById(R.id.SpHoraSalida);

        btnAceptarSS = view.findViewById(R.id.btnAceptarSS);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.
                createFromResource(getActivity(), R.array.horarios, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        horarioInicio.setAdapter(ad);
        horarioSalida.setAdapter(ad);

        grdbHorariosAdmin = view.findViewById(R.id.grdbHorariosAdmin);
        rdbEditable = view.findViewById(R.id.rdbEditable);
        rdbSelccionable = view.findViewById(R.id.rdbSelccionable);

        grdbHorariosAdmin.setOnCheckedChangeListener(this);

        edtHorariosEntrada = view.findViewById(R.id.edtSeleccionarHoraEntrada);
        edtHorariosSalida = view.findViewById(R.id.edtSeleccionarHoraSalida);

        horarioInicio.setOnItemSelectedListener(this);
        horarioSalida.setOnItemSelectedListener(this);


        edtHorariosEntrada.setOnClickListener(this);
        edtHorariosSalida.setOnClickListener(this);


        Spinners(horarioInicio, R.id.SpHoraInicio);
        Spinners(horarioSalida, R.id.SpHoraSalida);

        btnAceptarSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] horaSalidaArray = DatosSchedule.getHoraSalida().split(":");
                String[] horaEntradaArray = DatosSchedule.getHoraEntrada().split(":");

                if (DatosSchedule.getHoraSalida().equals(DatosSchedule.getHoraEntrada())) {
                    Toast.makeText(getActivity(), "Seleccione horario de salida diferente", Toast.LENGTH_SHORT).show();
                } else if(Integer.valueOf(horaEntradaArray[0]) > Integer.valueOf(horaSalidaArray[0])){
                    Toast.makeText(getActivity(), "Hora entrada debe de ser menor que la de salida", Toast.LENGTH_SHORT).show();

                } else {

                    Log.i("ENTRADA", DatosSchedule.getHoraEntrada());
                    Log.i("SALIDA", DatosSchedule.getHoraSalida());

                    Bundle datosRecuperados = getArguments();
                    if (datosRecuperados != null) {
                        if (datosRecuperados.getString("actionOfAssignParking") != null) {
                            changeFragments(new AssignParking());

                        }

                        if (datosRecuperados.getString("actionOfReserverParking") != null) {
                            changeFragments(new RecerveParking());
                        }
                    }

                }
            }
        });

        return view;
    }



    public void Spinners(final Spinner spinner, final int SpinnerID){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                switch (SpinnerID){
                    case R.id.SpHoraInicio:

                        DatosSchedule.setHoraEntrada(spinner.getItemAtPosition(position).toString());


                        break;
                    case R.id.SpHoraSalida:

                        DatosSchedule.setHoraSalida(spinner.getItemAtPosition(position).toString());

                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }

    public void changeFragments(Fragment fragment) {

        // Pasar datos de un fragment a otro
        Bundle datosAEnviar = new Bundle();
        datosAEnviar.putString("scheduleSelected", DatosSchedule.getHoraEntrada() + " " + DatosSchedule.getHoraSalida());
        fragment.setArguments(datosAEnviar);

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).
                replace(R.id.nav_host_fragment, fragment).commit();

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {
            case R.id.SpHoraInicio:

                DatosSchedule.setHoraEntrada(adapterView.getItemAtPosition(i).toString());
                break;

            case R.id.SpHoraSalida:

                DatosSchedule.setHoraSalida(adapterView.getItemAtPosition(i).toString());

                break;

            default:
                return;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rdbEditable:
                horarioSalida.setVisibility(View.GONE);
                horarioInicio.setVisibility(View.GONE);

                edtHorariosSalida.setVisibility(View.VISIBLE);
                edtHorariosEntrada.setVisibility(View.VISIBLE);

                edtHorariosEntrada.setText(DatosSchedule.getHoraEntrada());
                edtHorariosSalida.setText(DatosSchedule.getHoraSalida());

                break;
            case R.id.rdbSelccionable:
                horarioSalida.setVisibility(View.VISIBLE);
                horarioInicio.setVisibility(View.VISIBLE);

                edtHorariosSalida.setVisibility(View.GONE);
                edtHorariosEntrada.setVisibility(View.GONE);

                break;

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edtSeleccionarHoraEntrada:
                showClockPickerDialog(R.id.edtSeleccionarHoraEntrada);

                break;
            case R.id.edtSeleccionarHoraSalida:
                showClockPickerDialog(R.id.edtSeleccionarHoraSalida);

                break;
        }
    }


    public void showClockPickerDialog(final int idInput) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), R.style.CustomDatePickerDialogTheme,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {

                        switch (idInput){
                            case R.id.edtSeleccionarHoraEntrada:
                                DatosSchedule.setHoraEntrada(hourOfDay + ":" + minutes);
                                edtHorariosEntrada.setText(hourOfDay + ":" + minutes);

                                break;

                            case R.id.edtSeleccionarHoraSalida:
                                DatosSchedule.setHoraSalida(hourOfDay + ":" + minutes);
                                edtHorariosSalida.setText(hourOfDay + ":" + minutes);

                                break;
                        }
                    }
                }, 0, 0, false);
        timePickerDialog.show();
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
