package com.example.control_estacionamiento_utec_electiva_i.Admin;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedTeacher;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecerveParking.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecerveParking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecerveParking extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RecerveParking() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecerveParking.
     */
    // TODO: Rename and change types and number of parameters
    public static RecerveParking newInstance(String param1, String param2) {
        RecerveParking fragment = new RecerveParking();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(4).setChecked(true);
    }

    Button btnSeleccionarDocenteRP, btnSeleccionarEstacionamientoRP, btnSeleccionarHorarioRP,
            btnAceptarRP, btnDenegarRP;
    Spinner spSeleccionarDias, spCantidadHorariosRP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recerve_parking, container, false);


        spSeleccionarDias = view.findViewById(R.id.spSeleccionarDias);
        spCantidadHorariosRP = view.findViewById(R.id.spCantidadHorariosRP);

        ArrayAdapter<CharSequence> ad = ArrayAdapter.
                createFromResource(getActivity(), R.array.cantidad_parqueo, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCantidadHorariosRP.setAdapter(ad);

        ArrayAdapter<CharSequence> dias = ArrayAdapter.
                createFromResource(getActivity(), R.array.dias, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSeleccionarDias.setAdapter(dias);

        btnSeleccionarDocenteRP = view.findViewById(R.id.btnSeleccionarDocenteRP);
        btnSeleccionarEstacionamientoRP = view.findViewById(R.id.btnSeleccionarEstacionamientoRP);
        btnSeleccionarHorarioRP = view.findViewById(R.id.btnSeleccionarHorarioRP);
        btnAceptarRP = view.findViewById(R.id.btnAceptarRP);
        btnDenegarRP = view.findViewById(R.id.btnDenegarRP);


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

        btnSeleccionarDocenteRP.setOnClickListener(this);
        btnSeleccionarEstacionamientoRP.setOnClickListener(this);
        btnSeleccionarHorarioRP.setOnClickListener(this);
        btnAceptarRP.setOnClickListener(this);
        btnDenegarRP.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSeleccionarDocenteRP:

                changeFragments(new SelectedTeacher(),
                        "actionOfReserverParking", "ReserveParking");

                break;
            case R.id.btnSeleccionarEstacionamientoRP:

                changeFragments(new SelectedBuilding(),
                        "actionOfReserverParking", "ReserveParking");

                break;
            case R.id.btnSeleccionarHorarioRP:
                changeFragments(new SelectedSchedule(),
                        "actionOfReserverParking", "ReserveParking");

                break;
            case R.id.btnAceptarRP:
                Toast.makeText(getActivity(), "btnAceptarRP", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnDenegarRP:

                Toast.makeText(getActivity(), "btnDenegarRP", Toast.LENGTH_SHORT).show();
                break;
                default:
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
