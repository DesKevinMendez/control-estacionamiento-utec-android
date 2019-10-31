package com.example.control_estacionamiento_utec_electiva_i.Admin;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileUser.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileUser extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfileUser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileUser.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileUser newInstance(String param1, String param2) {
        ProfileUser fragment = new ProfileUser();
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

    Button btnCancelar, btnAceptar;
    TextView tvActualyPassword, tvNewPassword, tvPasswordConfirmed;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile_user,
                container, false);
        tvActualyPassword = view.findViewById(R.id.tvActualyPassword);
        tvNewPassword = view.findViewById(R.id.tvNewPassword);
        tvPasswordConfirmed = view.findViewById(R.id.tvPasswordConfirmed);

        btnCancelar = view.findViewById(R.id.btnCancelarP);
        btnAceptar = view.findViewById(R.id.btnAceptarP);


        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passActual = tvActualyPassword.getText().toString().trim();
                String nuevaPass = tvNewPassword.getText().toString().trim();
                String confiNuevaPass = tvPasswordConfirmed.getText().toString().trim();
                if (!nuevaPass.equals(confiNuevaPass)) {
                    Toast.makeText(getActivity(), "Las nuevas constraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    tvNewPassword.requestFocus();
                    tvNewPassword.setText("");
                    tvPasswordConfirmed.setText("");

                } else if (passActual.isEmpty()){
                    tvActualyPassword.setError("La contraseña es requerida");
                    tvActualyPassword.requestFocus();

                } else if (nuevaPass.isEmpty()){
                    tvNewPassword.setError("La nueva contraseña es requerida");
                    tvNewPassword.requestFocus();

                } else if (confiNuevaPass.isEmpty()){
                    tvPasswordConfirmed.setError("Llena este campo");
                    tvPasswordConfirmed.requestFocus();

                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setTitle("Confirmar cambio");
                    alert.setMessage("Está acción no se puede deshacer");
                    alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            tvNewPassword.setText("");
                            tvPasswordConfirmed.setText("");
                            tvActualyPassword.setText("");

                            Toast.makeText(getActivity(), "La contraseña ha sido actualizada", Toast.LENGTH_LONG).show();
                        }
                    });
                    alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Toast.makeText(getActivity(), "Cancelar", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alert.create().show();
                }


            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                InicioAdmin home = new InicioAdmin();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, home).commit();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAceptarP:

                Toast.makeText(getActivity(), "Hola mundo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCancelarP:

                Toast.makeText(getActivity(), "Hola mundo", Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
        }
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
