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
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.BuildingAdapter;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.RecerveParking;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ReserveEvents;
import com.example.control_estacionamiento_utec_electiva_i.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectedBuilding.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectedBuilding#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectedBuilding extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SelectedBuilding() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectedBuilding.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectedBuilding newInstance(String param1, String param2) {
        SelectedBuilding fragment = new SelectedBuilding();
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

    ListView ListBuilding;
    DatosBuilding datosBuilding;
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
