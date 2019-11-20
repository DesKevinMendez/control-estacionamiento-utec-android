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
import com.example.control_estacionamiento_utec_electiva_i.Admin.RecerveParking;
import com.example.control_estacionamiento_utec_electiva_i.R;

public class SelectedSchedule extends Fragment implements AdapterView.OnItemSelectedListener {

    private OnFragmentInteractionListener mListener;

    public SelectedSchedule() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selected_schedule_admin, container, false);

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

                    Bundle datosRecuperados = getArguments();
                    if (datosRecuperados != null) {
                        if (datosRecuperados.getString("actionOfAssignParking") != null) {
                            changeFragments(new AssignParking());

                        }

                        if (datosRecuperados.getString("actionOfReserverParking") != null) {
                            changeFragments(new RecerveParking());
                        }
                    }

                }
            }
        });

        return view;
    }
    public void changeFragments(Fragment fragment) {

        // Pasar datos de un fragment a otro
        Bundle datosAEnviar = new Bundle();
        datosAEnviar.putString("scheduleSelected", DatosSchedule.getHoraEntrada() + " " + DatosSchedule.getHoraSalida());
        fragment.setArguments(datosAEnviar);

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).
                replace(R.id.nav_host_fragment, fragment).commit();

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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
