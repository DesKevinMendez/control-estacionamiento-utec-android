package com.example.control_estacionamiento_utec_electiva_i.Admin;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.HTTP.HttpRequestAdmin;
import com.example.control_estacionamiento_utec_electiva_i.Helpers.DatePickerFragment;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.google.android.material.navigation.NavigationView;


public class ReserveEvents extends Fragment implements View.OnClickListener {
    private OnFragmentInteractionListener mListener;

    public ReserveEvents() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpRequestAdmin httpRequestAdmin = new HttpRequestAdmin();

        if (DatosBuilding.getTotalEdificios() == 0){
            httpRequestAdmin.HTTPrequestBuilding(getActivity());
        }

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
        View view = inflater.inflate(R.layout.fragment_reserve_events_admin, container, false);

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
                createFromResource(getActivity(), R.array.cantidad_parqueo, R.layout.spinner_item_design);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCantidad.setAdapter(ad);


        ArrayAdapter<CharSequence> hr = ArrayAdapter.
                createFromResource(getActivity(), R.array.horarios, R.layout.spinner_item_design);
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
                if (btnSelectedBuilding.getText().toString().equals(getString(R.string.selectedBuilding))) {
                    Toast.makeText(getContext(), "Seleccione parqueo", Toast.LENGTH_SHORT).show();

                } else if (fecha.isEmpty()){
                    Toast.makeText(getContext(), "Selecciona fecha", Toast.LENGTH_SHORT).show();

                } else {
                    changeFragments(new InicioAdmin());
                }
                break;
            case R.id.btnDenegarRP:
                changeFragments(new InicioAdmin());

                break;


                case R.id.etPlannedDate:
                showDatePickerDialog();
                break;
            default:
                break;
        }

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
                String selectedDate = selectedday + " / " + (selectedmonth+1) + " / " + selectedyear;
                PlannedDate.setText(selectedDate);
                DatosEvents.setFecha(selectedDate);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.show();

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
