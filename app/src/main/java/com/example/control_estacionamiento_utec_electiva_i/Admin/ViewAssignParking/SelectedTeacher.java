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
import android.widget.ListView;
import android.widget.TextView;

import com.example.control_estacionamiento_utec_electiva_i.Admin.AssignParking;
import com.example.control_estacionamiento_utec_electiva_i.Admin.AssignWatchman;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.TeacherAdapter;
import com.example.control_estacionamiento_utec_electiva_i.Admin.RecerveParking;
import com.example.control_estacionamiento_utec_electiva_i.R;


public class SelectedTeacher extends Fragment {

    private OnFragmentInteractionListener mListener;

    public SelectedTeacher() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ListView teacherList;
    DatosTeacher datosTeacher;
    TextView tvFindUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_selected_teacher_admin, container, false);


        teacherList = view.findViewById(R.id.lvTeachers);
        teacherList.setAdapter(new TeacherAdapter(getActivity(), datosTeacher.getAllTeacher(),
                datosTeacher.getAllCarnetsTeacher()));

        teacherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                DatosTeacher.setTeacherSelected(i);

                Bundle datosRecuperados = getArguments();
                if (datosRecuperados != null) {
                    if (datosRecuperados.getString("actionOfAssignParking") != null) {

                        changeFragments(new AssignParking(),
                                "maestroSeleccionado", DatosTeacher.getTeacherSelected());

                    } else if (datosRecuperados.getString("actionOfReserverParking") != null) {

                        changeFragments(new RecerveParking(),
                                "maestroSeleccionado", DatosTeacher.getTeacherSelected());

                    } else if (datosRecuperados.getString("actionOfAssignWatchman") != null) {

                        changeFragments(new AssignWatchman(),
                                "maestroSeleccionado", DatosTeacher.getTeacherSelected());
                    }
                }

            }
        });

        tvFindUser = view.findViewById(R.id.tvFindUser);
        tvFindUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")) {
                    DatosTeacher.setClearFilter();
                }
                DatosTeacher.getFilterTeachers(editable.toString());

                teacherList.setAdapter(new TeacherAdapter(getActivity(), datosTeacher.getFilterTeachers(editable.toString()),
                        datosTeacher.getFilterCarnetTeacher()));

            }
        });


        return view;
    }

    public void changeFragments(Fragment fragment, String putStringName, String putStringDescription) {
        // Pasar datos de un fragment a otro
        Bundle datosAEnviar = new Bundle();
        datosAEnviar.putString(putStringName, putStringDescription);
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
