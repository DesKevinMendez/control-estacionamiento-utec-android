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

import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedTeacher;
import com.example.control_estacionamiento_utec_electiva_i.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AssignWatchman.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AssignWatchman#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssignWatchman extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AssignWatchman() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AssignWatchman.
     */
    // TODO: Rename and change types and number of parameters
    public static AssignWatchman newInstance(String param1, String param2) {
        AssignWatchman fragment = new AssignWatchman();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assign_watchman, container, false);

        Button btnAsignarVigilante = view.findViewById(R.id.btnAsignarVigilante);
        Button btnSeleccionarEdificio = view.findViewById(R.id.btnSeleccionarEdificio);
        Button btnDenegarAW = view.findViewById(R.id.btnDenegarAW);
        Button btnAceptarAW = view.findViewById(R.id.btnAceptarAW);

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
                SelectedTeacher selectedTeacher = new SelectedTeacher();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, selectedTeacher).commit();
                Toast.makeText(getActivity(), "Hla desde assignWathman", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnAceptarAW:
                InicioAdmin inicioAdmin = new InicioAdmin();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, inicioAdmin).commit();
                break;
            case R.id.btnSeleccionarEdificio:

                SelectedBuilding selectedBuilding = new SelectedBuilding();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, selectedBuilding).commit();

                break;

            case R.id.btnDenegarAW:

                InicioAdmin inicioAdmin2 = new InicioAdmin();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, inicioAdmin2).commit();

                break;
        }

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
