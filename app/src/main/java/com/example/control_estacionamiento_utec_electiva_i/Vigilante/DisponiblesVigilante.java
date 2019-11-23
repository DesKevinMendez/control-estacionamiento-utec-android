package com.example.control_estacionamiento_utec_electiva_i.Vigilante;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.toolbox.ClearCacheRequest;
import com.example.control_estacionamiento_utec_electiva_i.Estudiante.InicioEstudiante;
import com.example.control_estacionamiento_utec_electiva_i.HTTP.HttpRequestDocente;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos.Adaptador;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos.DatosVigilante;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos.PeticionesVigilante;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DisponiblesVigilante.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DisponiblesVigilante#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisponiblesVigilante extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DisponiblesVigilante() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisponiblesVigilante.
     */
    // TODO: Rename and change types and number of parameters
    public static DisponiblesVigilante newInstance(String param1, String param2) {
        DisponiblesVigilante fragment = new DisponiblesVigilante();
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

    ListView lvEdificios;
    DatosVigilante datosVigilante;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_disponibles_vigilante, container, false);

        Button btnRegresar = (Button)view.findViewById(R.id.btnRegresar);
        lvEdificios = view.findViewById(R.id.lvEdificios);
        lvEdificios.setAdapter(new
                Adaptador(getActivity(),
                datosVigilante.dataBuilding(),
                datosVigilante.dataEstaDispo()));




        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio = new Intent(getActivity(), vigilanteNavigationDrawer.class);
                startActivity(inicio);
            }
        });


        return view;
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
