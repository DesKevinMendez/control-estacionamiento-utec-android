package com.example.control_estacionamiento_utec_electiva_i.Vigilante;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.HTTP.HttpRequestAdmin;
import com.example.control_estacionamiento_utec_electiva_i.Login.Login;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos.DatosVigilante;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerfilVigilante.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilVigilante#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilVigilante extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    SQLiteDatabase base;
    DatosVigilante objDatos;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PerfilVigilante() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilVigilante.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilVigilante newInstance(String param1, String param2) {
        PerfilVigilante fragment = new PerfilVigilante();
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

    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_vigilante, container, false);

        final EditText edtActual = view.findViewById(R.id.edtClaveActual);
        final EditText edtClave = view.findViewById(R.id.edtClaveNueva);
        final EditText edtConfirmar = view.findViewById(R.id.edtConfirmarClave);
        Button btnCancelar = view.findViewById(R.id.btnCancelar2);
        Button btnConfirmar = view.findViewById(R.id.btnConfirmar2);
        final TextView tvNombre=view.findViewById(R.id.tvNombre);
        final TextView tvCarnet=view.findViewById(R.id.tvCarnetDocente);
        final TextView tvClave=view.findViewById(R.id.tvClave);
        final TextView tvEdificio=view.findViewById(R.id.tvEdificio);
        final TextView tvDisponibilidad=view.findViewById(R.id.tvDisponibilidad);


        tvNombre.setText(user.getNombres()+" "+user.getApellidos());
        tvCarnet.setText("Falta Agregarlo");
        tvEdificio.setText(user.getNombre_edificio_parqueo_asignado());
        tvClave.setText("falta agregarlo");
        //tvDisponibilidad.setText(user.getEstado());

        int estado = user.getEstado();
        String estadoS = String.valueOf(estado);

        tvDisponibilidad.setText(estadoS);



        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                final String clave = edtClave.getText().toString().trim();
                final String confirmar = edtConfirmar.getText().toString().trim();
                final String actual = edtActual.getText().toString().trim();

                if (actual.equals("")){
                    edtClave.setError("Campo requerido");
                } else if (clave.equals("")){
                    edtClave.setError("Campo requerido");
                } else if (confirmar.equals("")){
                    edtConfirmar.setError("Campo requerido");
                } else if(clave.equals(confirmar)){

                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setTitle("Confirmar cambio");
                    alert.setMessage("Está acción no se puede deshacer");
                    alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            HttpRequestAdmin httpRequestAdmin = new HttpRequestAdmin();
                            httpRequestAdmin.HTTPrequesteChangePassword(getActivity(), actual,
                                    clave, confirmar);

                            edtActual.setText("");
                            edtClave.setText("");
                            edtConfirmar.setText("");
                            edtActual.requestFocus();
                            Intent login = new Intent(getActivity(), Login.class);
                            startActivity(login);
                        }
                    });

                    alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            edtActual.setText("");
                            edtClave.setText("");
                            edtConfirmar.setText("");
                            edtActual.requestFocus();
                            Toast.makeText(getActivity(), "Cancelar", Toast.LENGTH_SHORT).show();
                        }
                    });


                    alert.create().show();


                    // Establece la sesion de usuario con falso, y limpia la data del usuario
                    //user.setLoggedUser(false);
                    
                    //user.setDataUser(0, null, null, null, null,
                           // null, 0, 0, null);

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
