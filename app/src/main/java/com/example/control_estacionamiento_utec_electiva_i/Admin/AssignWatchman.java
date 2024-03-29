package com.example.control_estacionamiento_utec_electiva_i.Admin;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedTeacher;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.google.android.material.navigation.NavigationView;


public class AssignWatchman extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    public AssignWatchman() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(5).setChecked(true);
    }

    Button btnAsignarVigilante, btnSeleccionarEdificio, btnDenegarAW, btnAceptarAW;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assign_watchman_admin, container, false);

        btnAsignarVigilante = view.findViewById(R.id.btnAsignarVigilante);
        btnSeleccionarEdificio = view.findViewById(R.id.btnSeleccionarEdificio);
        btnDenegarAW = view.findViewById(R.id.btnDenegarAW);
        btnAceptarAW = view.findViewById(R.id.btnAceptarAW);

        Bundle datosRecuperados = getArguments();
        if (datosRecuperados != null ) {
            if (!DatosBuilding.getBuildingSelected().isEmpty()) {
                btnSeleccionarEdificio.
                        setText(DatosBuilding.getBuildingSelected());
            }
            if (!DatosTeacher.getTeacherSelected().isEmpty()) {
                btnAsignarVigilante.
                        setText(DatosTeacher.getTeacherSelected());
            }

        }


        btnAsignarVigilante.setOnClickListener(this);
        btnSeleccionarEdificio.setOnClickListener(this);
        btnAceptarAW.setOnClickListener(this);
        btnDenegarAW.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAsignarVigilante:
                changeFragments(new SelectedTeacher(),
                        "actionOfAssignWatchman", "AssignWatchman");

                break;

            case R.id.btnAceptarAW:

                if (btnAsignarVigilante.getText()
                        .toString().equals(getString(R.string.selectedWatchman))) {

                    Toast.makeText(getActivity(), "Debes de seleccionar un vigilante", Toast.LENGTH_SHORT).show();

                } else if (btnSeleccionarEdificio.getText()
                        .toString().equals(getString(R.string.selectedBuilding))) {

                    Toast.makeText(getActivity(), "Debes de seleccionar un parqueo", Toast.LENGTH_SHORT).show();

                } else {

                    changeFragments(new InicioAdmin());
                }
                break;
            case R.id.btnSeleccionarEdificio:

                changeFragments(new SelectedBuilding(),
                        "actionOfAssignWatchman", "AssignWatchman");

                break;

            case R.id.btnDenegarAW:

                changeFragments(new InicioAdmin());

                break;
        }

    }

    public void changeFragments(Fragment fragment){
        // Establece teacherSelected y a buildingSelected como ""
        DatosTeacher.setTeacherSelected(-1);
        DatosBuilding.setBuildingSelected(-1);

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
