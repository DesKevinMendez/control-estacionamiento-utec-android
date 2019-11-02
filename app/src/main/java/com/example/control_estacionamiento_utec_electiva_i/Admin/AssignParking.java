package com.example.control_estacionamiento_utec_electiva_i.Admin;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedTeacher;
import com.example.control_estacionamiento_utec_electiva_i.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AssignParking.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AssignParking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssignParking extends Fragment implements OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AssignParking() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AssignParking.
     */
    // TODO: Rename and change types and number of parameters
    public static AssignParking newInstance(String param1, String param2) {
        AssignParking fragment = new AssignParking();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Button btnAceptar, btnDenegar, btnAssingSchedule, btnAsssingTeacher, btnSelctedBuilding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assign_parking, container, false);

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

        switch(view.getId()){
            case R.id.btnAceptarAP:
                // Establece teacherSelected y a buildingSelected como ""
                DatosTeacher.setTeacherSelected(-1);
                DatosBuilding.setBuildingSelected(-1);
                DatosSchedule.setHoraSalida("");
                DatosSchedule.setHoraEntrada("");

                InicioAdmin inicio = new InicioAdmin();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, inicio).commit();

                Toast.makeText(getActivity(), "Datos almacenados", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnCancelarAP:

                // Establece teacherSelected y a buildingSelected como ""
                DatosTeacher.setTeacherSelected(-1);
                DatosBuilding.setBuildingSelected(-1);
                DatosSchedule.setHoraSalida("");
                DatosSchedule.setHoraEntrada("");

                InicioAdmin denegado = new InicioAdmin();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).
                        replace(R.id.nav_host_fragment, denegado).commit();

                break;
            case R.id.btnSelectedBuildingAP:

                changeFragments(new SelectedBuilding(),
                        "actionOfAssignParking", "ReserveParking");


                break;
            case R.id.btnSelectedSchedule:

                changeFragments(new SelectedSchedule(),
                        "actionOfAssignParking", "SelectedSchedule");

                break;
            case R.id.btnSelectedTeacher:
                changeFragments(new SelectedTeacher(),
                        "actionOfAssignParking", "SelectedTeacher");
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
