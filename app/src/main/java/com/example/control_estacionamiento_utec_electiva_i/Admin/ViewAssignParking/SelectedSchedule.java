package com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.Admin.AssignParking;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosSchedule;
import com.example.control_estacionamiento_utec_electiva_i.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectedSchedule.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectedSchedule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectedSchedule extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SelectedSchedule() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectedSchedule.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectedSchedule newInstance(String param1, String param2) {
        SelectedSchedule fragment = new SelectedSchedule();
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
        View view = inflater.inflate(R.layout.fragment_selected_schedule, container, false);

        Spinner horarioInicio = view.findViewById(R.id.SpHoraInicio);
        Spinner horarioSalida = view.findViewById(R.id.SpHoraSalida);
        Button btnAceptarSS = view.findViewById(R.id.btnAceptarSS);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.
                createFromResource(getActivity(), R.array.horarios, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        horarioInicio.setAdapter(ad);
        horarioSalida.setAdapter(ad);

        horarioInicio.setOnItemSelectedListener(this);
        horarioSalida.setOnItemSelectedListener(this);

        btnAceptarSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] horaSalidaArray = DatosSchedule.getHoraSalida().split(":");
                String[] horaEntradaArray = DatosSchedule.getHoraEntrada().split(":");

                if (DatosSchedule.getHoraSalida().equals(DatosSchedule.getHoraEntrada())) {
                    Toast.makeText(getActivity(), "Seleccione horario de salida diferente", Toast.LENGTH_SHORT).show();
                } else if(Integer.valueOf(horaEntradaArray[0]) > Integer.valueOf(horaSalidaArray[0])){
                    Toast.makeText(getActivity(), "Hora entrada debe de ser menor que la de salida", Toast.LENGTH_SHORT).show();

                } else {

                    AssignParking assignParking = new AssignParking();
                    Bundle datosAEnviar = new Bundle();
                    datosAEnviar.putString("horaEntrada", DatosSchedule.getHoraEntrada());
                    datosAEnviar.putString("horaSalida", DatosSchedule.getHoraSalida());

                    assignParking.setArguments(datosAEnviar);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment, assignParking).commit();
                }
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView tvHoraInicioSS = getActivity().findViewById(R.id.tvHoraInicioSS);
        TextView tvHoraSalidaSS = getActivity().findViewById(R.id.tvHoraSalidaSS);

        switch (adapterView.getId()) {
            case R.id.SpHoraInicio:
                tvHoraInicioSS.setText(adapterView.getItemAtPosition(i).toString());
                DatosSchedule.setHoraEntrada(adapterView.getItemAtPosition(i).toString());
                break;

            case R.id.SpHoraSalida:
                tvHoraSalidaSS.setText(adapterView.getItemAtPosition(i).toString());
                DatosSchedule.setHoraSalida(adapterView.getItemAtPosition(i).toString());
                break;

            default:
                return;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
