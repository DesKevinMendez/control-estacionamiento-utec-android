package com.example.control_estacionamiento_utec_electiva_i.Admin;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosEvents;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Helpers.DatePickerFragment;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReserveEvents.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReserveEvents#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReserveEvents extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
    Spinner spCantidad, SpHorariosRP;
    EditText etPlannedDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reserve_events, container, false);

        btnSelectedBuilding = view.findViewById(R.id.btnSelectedBuilding);

        Bundle recibeDatos = getArguments();
        if (recibeDatos != null) {
            btnSelectedBuilding.setText(DatosBuilding.getBuildingSelected());
        }

        btnAceptar = view.findViewById(R.id.btnAceparRP);
        btnDenegar = view.findViewById(R.id.btnDenegarRP);


        spCantidad = view.findViewById(R.id.SpCantidadEstacionamiento);
        SpHorariosRP = view.findViewById(R.id.SpHorariosRP);

        ArrayAdapter<CharSequence> ad = ArrayAdapter.
                createFromResource(getActivity(), R.array.cantidad_parqueo, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCantidad.setAdapter(ad);


        ArrayAdapter<CharSequence> hr = ArrayAdapter.
                createFromResource(getActivity(), R.array.horarios, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpHorariosRP.setAdapter(hr);


        etPlannedDate = view.findViewById(R.id.etPlannedDate);

        if (!DatosEvents.getFecha().isEmpty()) {
            etPlannedDate.setText(DatosEvents.getFecha());
        }
        etPlannedDate.setOnClickListener(this);

        btnSelectedBuilding.setOnClickListener(this);
        btnAceptar.setOnClickListener(this);
        btnDenegar.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSelectedBuilding:

                // Pasar datos de un fragment a otro
                Bundle datosAEnviar = new Bundle();
                datosAEnviar.putString("actionOfReserverEvents", "ReserveEvents");

                SelectedBuilding selectedBuilding = new SelectedBuilding();
                selectedBuilding.setArguments(datosAEnviar);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.nav_host_fragment, selectedBuilding).commit();
                break;

            case R.id.btnAceparRP:
                String fecha = etPlannedDate.getText().toString().trim();
                if (btnSelectedBuilding.getText().toString().equals("Seleccionar edificio")) {
                    Toast.makeText(getContext(), "Selecciona un edificio", Toast.LENGTH_SHORT).show();

                } else if (fecha.isEmpty()){
                    Toast.makeText(getContext(), "Selecciona fecha", Toast.LENGTH_SHORT).show();

                } else {
                    InicioAdmin inicioAdmin = new InicioAdmin();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.nav_host_fragment, inicioAdmin).commit();
                }
                break;
            case R.id.btnDenegarRP:

                InicioAdmin inicioAdmin2 = new InicioAdmin();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.nav_host_fragment, inicioAdmin2).commit();
                break;


                case R.id.etPlannedDate:
                showDatePickerDialog();
                break;
            default:
                break;
        }

    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                TextView PlannedDate = getActivity().findViewById(R.id.etPlannedDate);
                String selectedDate = day + " / " + (month+1) + " / " + year;
                PlannedDate.setText(selectedDate);
                DatosEvents.setFecha(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
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



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
