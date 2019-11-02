package com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.Admin.AssignParking;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.TeacherAdapter;
import com.example.control_estacionamiento_utec_electiva_i.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectedTeacher.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectedTeacher#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectedTeacher extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SelectedTeacher() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectedTeacher.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectedTeacher newInstance(String param1, String param2) {
        SelectedTeacher fragment = new SelectedTeacher();
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

    ListView teacherList;
    DatosTeacher datosTeacher;
    TextView tvFindUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_selected_teacher, container, false);


        teacherList = view.findViewById(R.id.lvTeachers);
        teacherList.setAdapter(new TeacherAdapter(getActivity(), datosTeacher.getAllTeacher(),
                datosTeacher.getAllCarnetsTeacher()));

        teacherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle datosRecuperados = getArguments();
                if (datosRecuperados != null) {
                    if (datosRecuperados.getString("actionOfAssignParking") != null) {
                        DatosTeacher.setTeacherSelected(i);

                        changeFragments(new AssignParking(),
                                "maestroSeleccionado", DatosTeacher.getTeacherSelected());

                    } else if (datosRecuperados.getString("actionOfReserverParking") != null) {
                        Toast.makeText(getActivity(), "Hola", Toast.LENGTH_SHORT).show();
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
