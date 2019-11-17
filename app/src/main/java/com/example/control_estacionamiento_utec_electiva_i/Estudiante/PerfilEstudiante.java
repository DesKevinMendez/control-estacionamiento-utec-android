package com.example.control_estacionamiento_utec_electiva_i.Estudiante;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.ui.login.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerfilEstudiante.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilEstudiante#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilEstudiante extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PerfilEstudiante() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilEstudiante.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilEstudiante newInstance(String param1, String param2) {
        PerfilEstudiante fragment = new PerfilEstudiante();
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
        View view = inflater.inflate(R.layout.fragment_perfil_estudiante, container, false);

        Button btnCancelar = view.findViewById(R.id.btnCancelar);
        Button btnConfirmar = view.findViewById(R.id.btnConfirmar);
        final EditText edtActual = view.findViewById(R.id.edtPlaca);
        final EditText edtClave = view.findViewById(R.id.edtClave);
        final EditText edtConfirmar = view.findViewById(R.id.edtConfirmar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtActual.setText("");
                edtClave.setText("");
                edtConfirmar.setText("");

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Accion cancelada");

                alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.create().show();
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clave = edtClave.getText().toString().trim();
                String confirmar = edtConfirmar.getText().toString().trim();
                String actual = edtActual.getText().toString().trim();

                if (actual.isEmpty()){
                    edtClave.setError("Campo requerido");
                } else if (clave.isEmpty()){
                    edtClave.setError("Campo requerido");
                } else if (confirmar.isEmpty()){
                    edtConfirmar.setError("Campo requerido");
                } else if(clave.equals(confirmar)){
                    Intent login = new Intent(getActivity(), LoginActivity.class);
                    startActivity(login);
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setTitle("ERROR");
                    alert.setMessage("Las claves no son iguales");
                    alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert.show();
                    edtClave.setText("");
                    edtConfirmar.setText("");
                }
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
