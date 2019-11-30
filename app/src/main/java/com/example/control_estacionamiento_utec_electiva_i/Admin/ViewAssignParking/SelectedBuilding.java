package com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.control_estacionamiento_utec_electiva_i.Admin.AssignParking;
import com.example.control_estacionamiento_utec_electiva_i.Admin.AssignWatchman;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.Adapters.BuildingAdapter;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosStudents;
import com.example.control_estacionamiento_utec_electiva_i.Admin.RecerveParking;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ReserveEvents;
import com.example.control_estacionamiento_utec_electiva_i.R;


public class SelectedBuilding extends Fragment {

    private OnFragmentInteractionListener mListener;

    public SelectedBuilding() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ListView ListBuilding;
    DatosBuilding datosBuilding;
    DatosStudents datosStudents;
    EditText tvFindBuildingSB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_selected_building_admin, container, false);
        ListBuilding = view.findViewById(R.id.listViewBuilding_SB);

        ListBuilding.setAdapter(new
                BuildingAdapter(getActivity(),
                datosBuilding.dataBuilding(),
                datosBuilding.dataEstaDispo(),
                datosBuilding.dataTotalEsta()));


        ListBuilding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                datosBuilding.setBuildingSelected(i);
                datosBuilding.setBuildingIdSelected(i);

              Bundle datosRecuperados = getArguments();
                if (datosRecuperados != null) {

                    if (datosRecuperados.getString("actionOfReserverEvents") != null) {
                        changeFragments(new ReserveEvents());

                    } else if (datosRecuperados.getString("actionOfAssignParking") != null) {
                        changeFragments(new AssignParking());

                    } else if (datosRecuperados.getString("actionOfReserverParking") != null){
                        changeFragments(new RecerveParking());

                    } else if (datosRecuperados.getString("actionOfAssignWatchman") != null){
                        changeFragments(new AssignWatchman());

                    }
                    return;

                }

            }
        });

        tvFindBuildingSB = view.findViewById(R.id.tvFindBuildingSB);
        tvFindBuildingSB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.toString().equals("")) {
                    DatosBuilding.setClearFilter();
                }

                ListBuilding.setAdapter(new
                        BuildingAdapter(getActivity(),
                        datosBuilding.getFilterBuilding(editable.toString()),
                        datosBuilding.getFilterEstacionamientoDisponible(),
                        datosBuilding.getFilterTotalEstacionamiento()));
            }
        });

        return view;
    }

    public void changeFragments(Fragment fragment) {

        // Pasar datos de un fragment a otro
        Bundle datosAEnviar = new Bundle();
        datosAEnviar.putString("edificioSeleccionado", datosBuilding.getBuildingSelected());
        fragment.setArguments(datosAEnviar);

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).
                replace(R.id.nav_host_fragment, fragment).commit();

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
