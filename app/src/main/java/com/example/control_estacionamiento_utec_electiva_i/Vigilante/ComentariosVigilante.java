package com.example.control_estacionamiento_utec_electiva_i.Vigilante;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos.DatosVigilante;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ComentariosVigilante.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComentariosVigilante#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComentariosVigilante extends Fragment {
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

    public ComentariosVigilante() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComentariosVigilante.
     */
    // TODO: Rename and change types and number of parameters
    public static ComentariosVigilante newInstance(String param1, String param2) {
        ComentariosVigilante fragment = new ComentariosVigilante();
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
        View view = inflater.inflate(R.layout.fragment_comentarios_vigilante, container, false);

         Button btnDetalle =view.findViewById(R.id.btnComentar);
         Button btnRegresar =view.findViewById(R.id.btnRegresar);
         Button btnBuscar = view.findViewById(R.id.btnBuscar);
         final EditText edtPlaca = view.findViewById(R.id.edtClaveActual);

        final TextView tvNombre = view.findViewById(R.id.tvNombre);
        final TextView tvPlaca = view.findViewById(R.id.tvPlaca);
        final TextView tvEdificio = view.findViewById(R.id.tvEdificio);
        final TextView tvHorario = view.findViewById(R.id.tvHorario);
        final TextView tvEstado = view.findViewById(R.id.tvEstado);


        /*
         btnBuscar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String Placa = edtPlaca.getText().toString().trim();

                 String consultaDisponible = "select * from usuarios where numero_placa = '"+Placa+"'";
                 base= objDatos.getWritableDatabase();
                 Cursor cUsuarios = base.rawQuery(consultaDisponible,null);

                 String horario = "";

                 if(cUsuarios.moveToNext()) {
                     tvNombre.setText(cUsuarios.getString(3));
                     tvPlaca.setText(cUsuarios.getString(2));
                     tvEdificio.setText(cUsuarios.getString(4));
                     horario= (cUsuarios.getString(6));
                     horario+= "  -  ";
                     horario+=(cUsuarios.getString(7));
                     tvHorario.setText(horario);

                 }

             }
         });*/


         btnRegresar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent main = new Intent(getActivity(),vigilanteNavigationDrawer.class);
                 startActivity(main);
             }
         });

        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detalle = new Intent(getActivity(),Detalle.class);
                startActivity(detalle);
            }
        });



        return view;

    }

    public void OnClickListener(){

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
