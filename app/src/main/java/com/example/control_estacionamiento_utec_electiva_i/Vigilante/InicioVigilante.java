package com.example.control_estacionamiento_utec_electiva_i.Vigilante;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos.DatosVigilante;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos.PeticionesVigilante;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InicioVigilante.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InicioVigilante#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioVigilante extends Fragment {

    SQLiteDatabase base;
    DatosVigilante objDatos;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public InicioVigilante() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioVigilante.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioVigilante newInstance(String param1, String param2) {
        InicioVigilante fragment = new InicioVigilante();
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

    //DatosVigilante datosVigilante;
    public static int idUsuario=0;
    public static int idEdificio=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio_vigilante, container, false);
        Button btnBuscar = view.findViewById(R.id.btnBuscar);
        Button btnValidar = view.findViewById(R.id.btnValidar);
        Button btnValidarSalida = view.findViewById(R.id.btnValidarSalida);

        final EditText edtPlaca = view.findViewById(R.id.edtPlaca);

        final TextView tvNombre = view.findViewById(R.id.tvNombre);
        final TextView tvPlaca = view.findViewById(R.id.tvPlaca);
        final TextView tvEdificio = view.findViewById(R.id.tvEdificio);
        //final TextView tvTipo = view.findViewById(R.id.tvTipo);
        final TextView tvEntrada = view.findViewById(R.id.tvEntrada);
        final TextView tvSalida = view.findViewById(R.id.tvSalida);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Placa;

                Placa = edtPlaca.getText().toString().trim();
                PeticionesVigilante peticionesVigilante = new PeticionesVigilante();
                peticionesVigilante.UsuariosPlaca(getActivity(),Placa);
                DatosVigilante datosVigilante = new DatosVigilante();

                tvNombre.setText(datosVigilante.getNombreUser() +" "+datosVigilante.getApellidoUser());
                tvPlaca.setText(datosVigilante.getPlacaUser());
                tvEdificio.setText(datosVigilante.getEdificioAsignadoUser());
                //tvTipo.setText("Usuario");
                tvEntrada.setText(datosVigilante.getHoraEntradaUser());
                tvSalida.setText(datosVigilante.getHoraSalidaUser());

                idUsuario = datosVigilante.getIdUserUser();
                idEdificio= datosVigilante.getIdEdificioUser();

            }
        });

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeticionesVigilante peticionesVigilante = new PeticionesVigilante();
                peticionesVigilante.ValidarEntrada(getActivity(),idUsuario,idEdificio);
            }
        });

        btnValidarSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeticionesVigilante peticionesVigilante = new PeticionesVigilante();
                peticionesVigilante.ValidarSalida(getActivity(),idUsuario);
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
