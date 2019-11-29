package com.example.control_estacionamiento_utec_electiva_i.Admin;

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

import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedScheduleForAssignParking;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedTeacher;
import com.example.control_estacionamiento_utec_electiva_i.HTTP.HttpRequestAdmin;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.google.android.material.navigation.NavigationView;


public class AssignParking extends Fragment implements OnClickListener {

    private OnFragmentInteractionListener mListener;

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
    User user;
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

        /*
        if (DatosTeacher.getTotalTeacher() == 0){
            httpRequestAdmin.HTTPrequestUsers(getActivity());
        }*/

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
                if (btnAssingSchedule.getText().toString().equals(getString(R.string.selectedScedule))){

                    Toast.makeText(getActivity(), "Debe de seleccionar horarios", Toast.LENGTH_SHORT).show();

                } else if(btnAsssingTeacher.getText().equals(getString(R.string.selectedTeacher))) {

                    Toast.makeText(getActivity(), "Debe de seleccionar usuario", Toast.LENGTH_SHORT).show();

                } else if (btnSelctedBuilding.getText().equals(getString(R.string.selectedBuilding))) {

                    Toast.makeText(getActivity(), "Debe de seleccionar edificio", Toast.LENGTH_SHORT).show();

                } else {

                    Log.i("DATOS", DatosBuilding.getBuildingIdSelected());
                    Log.i("DATOS", DatosTeacher.getTeacherIdSelected());
                    Log.i("DATOS", DatosSchedule.getHoraEntrada());
                    Log.i("DATOS", DatosSchedule.getHoraSalida());

                    changeFragments(new InicioAdmin());
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
