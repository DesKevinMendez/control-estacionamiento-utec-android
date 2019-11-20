package com.example.control_estacionamiento_utec_electiva_i.Admin;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.google.android.material.navigation.NavigationView;


public class InicioAdmin extends Fragment {
    private OnFragmentInteractionListener mListener;

    public InicioAdmin() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    TextView tvInfoPlaca, tvNombreIA, tvEstacionamientoReservadoA,
            tvScheduleAdmin, tvStateEstacionamientoAdmin, tvCantidadReservadoAdmin;
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_inicio_admin_admin,
                container, false);

        tvInfoPlaca = view.findViewById(R.id.tvInfoPlaca);
        tvNombreIA = view.findViewById(R.id.tvNombreIA);
        tvEstacionamientoReservadoA = view.findViewById(R.id.tvEstacionamientoReservadoA);

        tvScheduleAdmin = view.findViewById(R.id.tvScheduleAdmin);
        tvStateEstacionamientoAdmin = view.findViewById(R.id.tvStateEstacionamientoAdmin);
        tvCantidadReservadoAdmin = view.findViewById(R.id.tvCantidadReservadoAdmin);

        tvNombreIA.setText(user.getNombres()+ " "+ user.getApellidos());

        // Muestra info de estacionamiento
        tvEstacionamientoReservadoA.setText(user.getNombre_edificio_parqueo_asignado() +" - " +
                user.getAlias_edificio_parqueo_asignado());

        tvCantidadReservadoAdmin.setText(String.valueOf(user.getNum_reservados_edificio_parqueo_asignado()));


        Resources r = getResources();

        if (!user.getNum_placa().equals("")){
            tvInfoPlaca.setText("Placa no disponible");
            tvInfoPlaca.setTextColor(r.getColor(R.color.red));
        } else {
            tvInfoPlaca.setText(user.getNum_placa());
        }

        return view;
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
