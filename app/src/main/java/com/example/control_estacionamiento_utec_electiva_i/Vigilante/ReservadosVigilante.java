package com.example.control_estacionamiento_utec_electiva_i.Vigilante;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.Adaptadores.AdaptadorReservados;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos.DatosVigilante;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos.PeticionesVigilantes;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReservadosVigilante.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReservadosVigilante#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReservadosVigilante extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ReservadosVigilante() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReservadosVigilante.
     */
    // TODO: Rename and change types and number of parameters
    public static ReservadosVigilante newInstance(String param1, String param2) {
        ReservadosVigilante fragment = new ReservadosVigilante();
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
        View view = inflater.inflate(R.layout.fragment_reservados_vigilante, container, false);

        Button btnRegresar = view.findViewById(R.id.btnRegresar);
        ListView lvEdificios = view.findViewById(R.id.lvEdificios);
        final TextView tvDisponibles = view.findViewById(R.id.tvEstacionamientoDisponibles);
        final TextView tvOcupados = view.findViewById(R.id.tvEstacionamientosOcupados);
        final TextView tvEstado = view.findViewById(R.id.tvEstado);

        lvEdificios.setAdapter(new
                AdaptadorReservados(getActivity(),
                datosVigilante.getNombreR(),datosVigilante.getApellidoR(),datosVigilante.getPlacaR(),
                datosVigilante.getEdificioR(),datosVigilante.getEntradaR(),datosVigilante.getSalidaR()
                ));

        lvEdificios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int idEdificio = i+1;
                PeticionesVigilantes peticionesVigilante = new PeticionesVigilantes();
                peticionesVigilante.EdificiosId(getActivity(),idEdificio);


                DatosVigilante datosVigilante = new DatosVigilante();

                int estado = DatosVigilante.getNumDispo();
                String dis = String.valueOf(datosVigilante.getNumDispo()) ;
                String ocup = String.valueOf(datosVigilante.getNumOcupado());

                tvDisponibles.setText(dis);
                tvOcupados.setText(ocup);

                if (estado >= 1 ){
                    tvEstado.setText("Estacionamientos disponibles");
                }else
                {tvEstado.setText("Estacionamiento lleno");}

            }
        });


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
